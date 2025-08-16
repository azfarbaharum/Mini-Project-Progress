import java.awt.*;
import java.util.List;
import javax.swing.*;

class ComputerDetailsPage extends JPanel {
    private ShoppingCart cart; // Reference to the shared ShoppingCart instance

    public ComputerDetailsPage(String name, String price, String imagePath, List<String> specifications, CardLayout cardLayout, JPanel mainPanel, ShoppingCart cart) {
        this.cart = cart; // Store the passed ShoppingCart instance
        setLayout(new BorderLayout());

        // Top navy bar
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.decode("#1A1A3D"));
        topPanel.setPreferredSize(new Dimension(1920, 80));

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFocusPainted(false);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.decode("#1A1A3D"));
        backButton.setFont(new Font("Arial", Font.PLAIN, 20)); // Increased font size
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "BuyComputer"));

        // Main content section
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        // Left: Enlarged Image of the product
        ImageIcon originalImage = new ImageIcon(imagePath);
        Image scaledImage = originalImage.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(imageLabel, BorderLayout.WEST);

        // Center and Right: Combined product details and placeholders
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some padding

        // Product name
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 28)); // Increased font size
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Product price
        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // Increased font size
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add to Cart button
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setFont(new Font("Arial", Font.BOLD, 20)); // Increased button font size
        addToCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToCartButton.addActionListener(e -> {
            try {
                double itemPrice = Double.parseDouble(price.replace("RM ", "").replace(",", ""));
                CartItem item = new CartItem(name, itemPrice);
                cart.addItem(item); // Add item directly to the cart
                JOptionPane.showMessageDialog(this, name + " has been added to the cart.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error adding item to cart. Please check the price format.");
            }
        });

        // Placeholder specifications
        JLabel specsTitle = new JLabel("Specifications");
        specsTitle.setFont(new Font("Arial", Font.BOLD, 22)); // Increased font size
        specsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Dynamically add specifications
        detailsPanel.add(Box.createVerticalStrut(20)); // Spacer
        detailsPanel.add(nameLabel);
        detailsPanel.add(Box.createVerticalStrut(15)); // Spacer
        detailsPanel.add(priceLabel);
        detailsPanel.add(Box.createVerticalStrut(25)); // Spacer
        detailsPanel.add(addToCartButton);
        detailsPanel.add(Box.createVerticalStrut(40)); // Spacer for separation
        detailsPanel.add(specsTitle);
        detailsPanel.add(Box.createVerticalStrut(20)); // Spacer

        // Add each specification dynamically
        for (String spec : specifications) {
            JLabel specLabel = new JLabel("• " + spec);
            specLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Increased font size
            specLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            detailsPanel.add(specLabel);
        }

        contentPanel.add(detailsPanel, BorderLayout.CENTER);

        // Add components to the main panel
        add(topPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }
}
