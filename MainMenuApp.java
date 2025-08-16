import java.awt.*;
import javax.swing.*;

public class MainMenuApp extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final ShoppingCart cart;
    private final String username; // Store the username
    private final ShoppingCartPage shoppingCartPage;

    public MainMenuApp(String username) {
        this.username = username; // Store the username passed to the constructor
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        cart = new ShoppingCart();

        // Create the shopping cart page
        shoppingCartPage = new ShoppingCartPage(cardLayout, mainPanel, cart);

        // Create the other pages (e.g., MainMenuPage, BuyComputerPage, etc.)
        MainMenuPage mainMenuPage = new MainMenuPage(cardLayout, mainPanel);
        BuyComputerPage buyComputerPage = new BuyComputerPage(cardLayout, mainPanel, cart);
        BuyHardwarePage buyHardwarePage = new BuyHardwarePage(cardLayout, mainPanel, cart);

        // Adding all the pages to the main panel
        mainPanel.add(mainMenuPage, "MainMenu");
        mainPanel.add(buyComputerPage, "BuyComputer");
        mainPanel.add(buyHardwarePage, "BuyHardware");
        mainPanel.add(shoppingCartPage, "ShoppingCartPage");

        // Checkout Page (pass the username)
        CheckoutPage checkoutPage;
        checkoutPage = new CheckoutPage(cardLayout, mainPanel, cart, shoppingCartPage, username);
        mainPanel.add(checkoutPage, "CheckoutPage");

        // Set the initial view to "MainMenu"
        cardLayout.show(mainPanel, "MainMenu");

        // Set up the frame
        setTitle("Main Menu - Welcome, " + username); // Include the username in the title
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenuApp("Guest").setVisible(true); // Default username for testing
        });
    }
}
