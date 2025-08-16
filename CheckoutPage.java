import java.awt.*;
import javax.swing.*;

public class CheckoutPage extends JPanel {
    private ShoppingCart cart;
    private ShoppingCartPage shoppingCartPage;
    private JLabel totalPriceLabel;
    private JTextField addressField;
    private JComboBox<String> paymentMethodCombo;
    private JComboBox<String> bankCombo;
    private String username;
    private String savedAddress = "";
    private JRadioButton saveAddressButton;

    public CheckoutPage(CardLayout cardLayout, JPanel mainPanel, ShoppingCart cart, ShoppingCartPage shoppingCartPage, String username) {
        this.cart = cart;
        this.shoppingCartPage = shoppingCartPage;
        this.username = username;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Top bar with title
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.decode("#1A1A3D"));
        topPanel.setPreferredSize(new Dimension(1920, 80));

        JLabel titleLabel = new JLabel("Checkout", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setFocusPainted(false);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.decode("#1A1A3D"));
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "ShoppingCartPage"));
        topPanel.add(backButton, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);

        // Main content section
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel usernameLabel = new JLabel("Logged in as: " + username);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        contentPanel.add(usernameLabel, gbc);

        gbc.gridy++;
        JLabel paymentMethodLabel = new JLabel("Select Payment Method:");
        paymentMethodLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        contentPanel.add(paymentMethodLabel, gbc);

        gbc.gridx = 1;
        paymentMethodCombo = new JComboBox<>(new String[]{"Credit/Debit Card", "Online Banking", "E-Wallets", "Cash On Delivery"});
        paymentMethodCombo.setFont(new Font("Arial", Font.PLAIN, 18));
        contentPanel.add(paymentMethodCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel bankLabel = new JLabel("Select Bank:");
        bankLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        contentPanel.add(bankLabel, gbc);

        gbc.gridx = 1;
        bankCombo = new JComboBox<>(new String[]{"Maybank2u", "Bank Rakyat", "CIMB Clicks", "Ambank"});
        bankCombo.setFont(new Font("Arial", Font.PLAIN, 18));
        contentPanel.add(bankCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel addressLabel = new JLabel("Enter Address:");
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        contentPanel.add(addressLabel, gbc);

        gbc.gridx = 1;
        addressField = new JTextField(20);
        addressField.setFont(new Font("Arial", Font.PLAIN, 18));
        contentPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        saveAddressButton = new JRadioButton("Save My Address");
        saveAddressButton.setFont(new Font("Arial", Font.PLAIN, 18));
        saveAddressButton.setBackground(Color.WHITE);
        gbc.gridwidth = 2;
        contentPanel.add(saveAddressButton, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        JLabel totalLabel = new JLabel("Total:");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        contentPanel.add(totalLabel, gbc);

        gbc.gridx = 1;
        totalPriceLabel = new JLabel("RM " + String.format("%.2f", cart.getTotalPrice()));
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        contentPanel.add(totalPriceLabel, gbc);

        add(contentPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(Color.decode("#1A1A3D"));

        JButton confirmButton = new JButton("Proceed");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 20));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setBackground(Color.decode("#007BFF"));
        confirmButton.setFocusPainted(false);
        confirmButton.addActionListener(e -> handleOrderConfirmation(cardLayout, mainPanel));
        bottomPanel.add(confirmButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Add action listener for payment method combo box
        paymentMethodCombo.addActionListener(e -> updateBankComboState());
    }

    private void updateBankComboState() {
        // Enable or disable the bank combo based on the payment method
        if (paymentMethodCombo.getSelectedItem().equals("Online Banking")) {
            bankCombo.setEnabled(true);  // Enable the bank combo
        } else {
            bankCombo.setEnabled(false);  // Disable the bank combo
        }
    }

    private void handleOrderConfirmation(CardLayout cardLayout, JPanel mainPanel) {
        if (cart.getTotalPrice() > 0) {
            String paymentMethod = (String) paymentMethodCombo.getSelectedItem();
            String bank = (String) bankCombo.getSelectedItem();
            String address = addressField.getText();

            if (saveAddressButton.isSelected()) {
                savedAddress = address;
            }

            // Custom Success Message Dialog
            JPanel messagePanel = new JPanel();
            messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
            messagePanel.setBackground(Color.WHITE);

            JLabel messageTitle = new JLabel("Order Confirmed!");
            messageTitle.setFont(new Font("Arial", Font.BOLD, 20));
            messageTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel detailsLabel = new JLabel("<html>Username: " + username +
                "<br>Payment Method: " + paymentMethod +
                "<br>Bank: " + bank +
                "<br>Address: " + address +
                "<br>Thank you for your purchase!</html>");
            detailsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            detailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            messagePanel.add(Box.createVerticalStrut(10));
            messagePanel.add(messageTitle);
            messagePanel.add(Box.createVerticalStrut(10));
            messagePanel.add(detailsLabel);

            JOptionPane.showMessageDialog(this, messagePanel, "Order Confirmation", JOptionPane.PLAIN_MESSAGE);

            cart.clear();
            shoppingCartPage.refreshCart();
            cardLayout.show(mainPanel, "MainMenu");
        } else {
            JOptionPane.showMessageDialog(this, "Cart is empty. Add items to proceed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            refreshTotalPrice();
            addressField.setText(savedAddress);
        }
    }

    public void refreshTotalPrice() {
        totalPriceLabel.setText("Total: RM " + String.format("%.2f", cart.getTotalPrice()));
    }
}
