import java.awt.*;
import java.util.HashMap;
import java.util.prefs.Preferences;
import javax.swing.*;

public class ByteBridgeApp extends JFrame {
    private static final HashMap<String, String> userCredentials = new HashMap<>();
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox rememberMeCheckBox;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    // Preferences for storing user settings
    private static final Preferences prefs = Preferences.userRoot().node(ByteBridgeApp.class.getName());

    public ByteBridgeApp() {
        setTitle("BYTE BRIDGE - Computer Hardware Buying System");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel contentPanel = new JPanel(new BorderLayout());

        JPanel imagePanel = new JPanel(new BorderLayout());
        try {
            // Adjusted image path and removed trailing slash
            ImageIcon originalIcon = new ImageIcon("C://Users//rajaa//Pictures//Log In page.jpg");
            Image scaledImage = originalIcon.getImage().getScaledInstance(500, 600, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            imagePanel.add(imageLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
            imagePanel.setBackground(Color.GRAY);
        }

        contentPanel.add(imagePanel, BorderLayout.WEST);
        contentPanel.add(loginPanel(), BorderLayout.CENTER);

        mainPanel.add(contentPanel, "Login");
        mainPanel.add(signUpPanel(), "SignUp");

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");

        loadRememberedUsername(); // Load saved username if "Remember Me" was checked
        setVisible(true); // Ensure the frame is visible
    }

    // Method to create login panel
    private JPanel loginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();

        JLabel welcomeLabel = new JLabel("Welcome to ByteBridge!");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 26));
        welcomeLabel.setForeground(new Color(70, 130, 180));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(20, 10, 20, 10);
        panel.add(welcomeLabel, c);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 20, 15);
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(usernameLabel, c);

        usernameField = new JTextField(15);
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(usernameField, c);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(5, 10, 20, 15);
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(passwordLabel, c);

        passwordField = new JPasswordField(15);
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(passwordField, c);

        rememberMeCheckBox = new JCheckBox("Remember Me");
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.insets = new Insets(-10, 35, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        panel.add(rememberMeCheckBox, c);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        loginButton.addActionListener(e -> {
            if (logIn(usernameField.getText(), new String(passwordField.getPassword()))) {
                if (rememberMeCheckBox.isSelected()) {
                    prefs.put("username", usernameField.getText());
                } else {
                    prefs.remove("username");
                }
                mainMenu();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.insets = new Insets(20, 50, 10, -40);
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(loginButton, c);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        signUpButton.addActionListener(e -> cardLayout.show(mainPanel, "SignUp"));
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(signUpButton, c);

        return panel;
    }

    // Method to create signup panel
    private JPanel signUpPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        JPanel imagePanel = new JPanel(new BorderLayout());

        try {
            ImageIcon originalIcon = new ImageIcon("C://Users//rajaa//Pictures//Log In page.jpg");
            Image scaledImage = originalIcon.getImage().getScaledInstance(500, 600, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            imagePanel.add(imageLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
            imagePanel.setBackground(Color.GRAY);
        }

        contentPanel.add(imagePanel, BorderLayout.WEST);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();

        JLabel signUpLabel = new JLabel("Create an Account");
        signUpLabel.setFont(new Font("Serif", Font.BOLD, 22));
        signUpLabel.setForeground(new Color(70, 130, 180));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(20, 10, 20, 10);
        panel.add(signUpLabel, c);

        JLabel newUsernameLabel = new JLabel("Username:");
        newUsernameLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 20, 15);
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(newUsernameLabel, c);

        JTextField newUsernameField = new JTextField(15);
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(newUsernameField, c);

        JLabel newPasswordLabel = new JLabel("Password:");
        newPasswordLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(5, 10, 20, 15);
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(newPasswordLabel, c);

        JPasswordField newPasswordField = new JPasswordField(15);
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(newPasswordField, c);

        JButton signUpConfirmButton = new JButton("Sign Up");
        signUpConfirmButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        signUpConfirmButton.addActionListener(e -> {
            if (signUp(newUsernameField.getText(), new String(newPasswordField.getPassword()))) {
                cardLayout.show(mainPanel, "Login");
            }
        });
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.insets = new Insets(20, 50, 10, -40);
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(signUpConfirmButton, c);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(backButton, c);

        contentPanel.add(panel, BorderLayout.CENTER);

        return contentPanel;
    }

    // Load saved username if "Remember Me" was previously checked
    private void loadRememberedUsername() {
        String rememberedUsername = prefs.get("username", "");
        if (!rememberedUsername.isEmpty()) {
            usernameField.setText(rememberedUsername);
            rememberMeCheckBox.setSelected(true);
        }
    }

    private boolean logIn(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }

    private boolean signUp(String username, String password) {
        if (userCredentials.containsKey(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (password.equalsIgnoreCase(username)) {
            JOptionPane.showMessageDialog(this, "Password cannot be the same as the username", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!isValidPassword(password)) {
            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one special character, and cannot be the same as the username", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        userCredentials.put(username, password);
        JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }
        return hasUppercase && hasLowercase && hasSpecialChar;
    }

    private void mainMenu() {
        JOptionPane.showMessageDialog(this, "Welcome to the main menu!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ByteBridgeApp::new);
    }
}
