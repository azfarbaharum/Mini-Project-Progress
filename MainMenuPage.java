import java.awt.*;
import javax.swing.*;

class MainMenuPage extends JPanel {
    public MainMenuPage(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());

        // Back Button on the top-left corner
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.decode("#1A1A3D"));
        topPanel.setPreferredSize(new Dimension(1920, 90));

        JButton backButton = new JButton("Logout");
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 32));
        backButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (topFrame != null) {
                topFrame.dispose();
            }
            SwingUtilities.invokeLater(LoginSignupPage::new);
        });

        topPanel.add(backButton, BorderLayout.WEST);

        // Top-right panel for additional buttons
        JPanel topRightPanel = new JPanel();
        topRightPanel.setBackground(Color.decode("#1A1A3D"));
        topRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        // Shopping Cart Button
        JButton cartButton = createTopButton("View Cart", "C://Users//rajaa//Documents//Semester 3//Java Mini Project//JAVA DETAILS//Shopping Cart.png");
        cartButton.addActionListener(e -> {
            ShoppingCartPage shoppingCartPage = (ShoppingCartPage) mainPanel.getComponent(3); // Assuming ShoppingCartPage is the 4th component
            shoppingCartPage.refreshCart(); // Refresh the cart contents
            cardLayout.show(mainPanel, "ShoppingCartPage");
        });

        // Exit Button
        JButton exitButton = createTopButton("Exit", "C://Users//rajaa//Documents//Semester 3//Java Mini Project//JAVA DETAILS//Exit.png");
        exitButton.addActionListener(e -> {
            int confirmed = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to exit?", "Exit Confirmation",
                JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Add buttons to the top-right panel
        topRightPanel.add(cartButton); // Add the cart button here
        topRightPanel.add(exitButton);

        topPanel.add(topRightPanel, BorderLayout.EAST);

        // Main buttons in the center
        JPanel centerPanel = new JPanel(new GridLayout(0, 2, 20, 0));
        centerPanel.setBackground(Color.decode("#FFFFFF"));

        // Buy Computer Button
        JButton buyComputerButton = createMenuButton("Buy Computer", "C://Users//rajaa//Pictures//Monitor.png", 400, 300);
        buyComputerButton.setForeground(Color.BLACK);
        buyComputerButton.addActionListener(e -> cardLayout.show(mainPanel, "BuyComputer"));

        // Buy Hardware Button
        JButton buyHardwareButton = createMenuButton("Buy Hardware", "C://Users//rajaa//Pictures//Hardwaree.png", 400, 300);
        buyHardwareButton.setForeground(Color.BLACK);
        buyHardwareButton.addActionListener(e -> cardLayout.show(mainPanel, "BuyHardware"));

        centerPanel.add(buyComputerButton);
        centerPanel.add(buyHardwareButton);

        // Bottom navy-blue panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.decode("#1A1A3D"));
        bottomPanel.setPreferredSize(new Dimension(1920, 90));

        // Add components to the MainMenuPage
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton createTopButton(String text, String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image image = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);

        JButton button = new JButton(text, scaledIcon);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        return button;
    }

    private static JButton createMenuButton(String text, String iconPath, int width, int height) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(text, scaledIcon);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        return button;
    }
}
