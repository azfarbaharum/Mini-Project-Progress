import java.awt.*;
import java.util.List;
import javax.swing.*;


public class ShoppingCartPage extends JPanel {
    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final ShoppingCart cart;
    private final JPanel cartItemsPanel;

    public ShoppingCartPage(CardLayout cardLayout, JPanel mainPanel, ShoppingCart cart) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.cart = cart;

        setLayout(new BorderLayout());
        
        // Title label at the top
        JLabel titleLabel = new JLabel("Shopping Cart");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        // Set background color
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.decode("#1A1A3D")); // You can choose any color you like
        titleLabel.setPreferredSize(new Dimension(1920, 90));
        
        // Add the label to the frame
        add(titleLabel, BorderLayout.NORTH);
        
        // Panel for displaying cart items
        cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(cartItemsPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for the bottom buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.decode("#1A1A3D"));
        bottomPanel.setPreferredSize(new Dimension(1920, 90));

        // Back Button
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MainMenu"));
        bottomPanel.add(backButton);

        // Checkout Button
        JButton checkoutButton = new JButton("Proceed to Checkout");
        checkoutButton.addActionListener(e -> cardLayout.show(mainPanel, "CheckoutPage"));
        bottomPanel.add(checkoutButton);

        add(bottomPanel, BorderLayout.SOUTH);

        refreshCart(); // Initialize with the current cart contents
    }

    public void refreshCart() {
        cartItemsPanel.removeAll(); // Clear previous items
        
        List<CartItem> items = cart.getItems();
        if (items.isEmpty()) {
            JLabel emptyLabel = new JLabel("Your cart is empty.");
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.BOLD, 70));
            cartItemsPanel.add(emptyLabel);
        } else {
            // Display cart items
            for (CartItem item : items) {
                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

                // Label to display item name, quantity, and price
                JLabel itemLabel = new JLabel(item.getName() + " x" + item.getQuantity() + " - RM " + (item.getPrice() * item.getQuantity()));
                itemLabel.setFont(new Font("Arial", Font.BOLD, 24));
                itemPanel.add(itemLabel);

                // Remove button for each item
                JButton removeButton = new JButton("Remove");
                removeButton.addActionListener(e -> {
                    cart.removeItem(item.getName()); // Remove or decrement quantity
                    refreshCart(); // Refresh the cart display
                });
                itemPanel.add(removeButton);

                cartItemsPanel.add(itemPanel);
            }
    
            // Display the total price
            double total = cart.getTotalPrice();
            JLabel totalLabel = new JLabel("Total: RM" + total);
            totalLabel.setFont(new Font("Arial", Font.BOLD, 24));
            totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
            cartItemsPanel.add(totalLabel);
        }
    
        cartItemsPanel.revalidate();
        cartItemsPanel.repaint();
    }
}
