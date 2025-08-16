import java.awt.*;
import java.util.HashMap;
import java.util.prefs.Preferences;
import javax.swing.*;

public class LoginSignupPage extends JFrame {
    private static final HashMap<String, String> userCredentials = new HashMap<>();
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox rememberMeCheckBox;
    private final JPanel mainPanel;
    private CardLayout cardLayout;

    // Preferences for storing user settings
    private static final Preferences prefs = Preferences.userRoot().node(LoginSignupPage.class.getName());

    public LoginSignupPage() {
        setTitle("BYTE BRIDGE - Computer Hardware Buying System");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(contentWithImagePanel(loginPanel()), "Login");
        mainPanel.add(contentWithImagePanel(signUpPanel()), "SignUp");

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");

        loadRememberedUsername(); // Load saved username if "Remember Me" was checked
        setVisible(true); // Ensure the frame is visible
    }

    // Helper method to wrap a given panel with the image panel on the left
    private JPanel contentWithImagePanel(JPanel centralPanel) {
        JPanel contentPanel = new JPanel(new BorderLayout());

        JPanel imagePanel = new JPanel(new BorderLayout());
        try {
            // Load and scale the image
            ImageIcon originalIcon = new ImageIcon("C://Users//rajaa//Pictures//Log In page.jpg");
            Image scaledImage = originalIcon.getImage().getScaledInstance(700, 850, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            imagePanel.add(imageLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
            imagePanel.setBackground(Color.GRAY);
        }

        contentPanel.add(imagePanel, BorderLayout.WEST);
        contentPanel.add(centralPanel, BorderLayout.CENTER);

        return contentPanel;
    }

    // Method to create login panel
    private JPanel loginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();

        JLabel welcomeLabel = new JLabel("Welcome to ByteBridge!");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 52));
        welcomeLabel.setForeground(new Color(70, 130, 180));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(20, 10, 20, 10);
        panel.add(welcomeLabel, c);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("SansSerif", Font.PLAIN, 32));
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 20, 15);
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(usernameLabel, c);

        usernameField = new JTextField(30);
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(usernameField, c);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 32));
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(5, 10, 20, 15);
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(passwordLabel, c);

        passwordField = new JPasswordField(30);
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(passwordField, c);

        rememberMeCheckBox = new JCheckBox("Remember Me");
        rememberMeCheckBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.insets = new Insets(-10, 35, 10, 10 );
        c.anchor = GridBagConstraints.CENTER;
        panel.add(rememberMeCheckBox, c);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("SansSerif", Font.PLAIN, 32));
        loginButton.addActionListener(e -> {
            if (logIn(usernameField.getText(), new String(passwordField.getPassword()))) {
                String username = usernameField.getText(); 
                if (rememberMeCheckBox.isSelected()) {
                    prefs.put("username", usernameField.getText());
                } else {
                    prefs.remove("username");
                }

                // Call the MainMenuApp
                SwingUtilities.invokeLater(() -> {
                    MainMenuApp mainMenu = new MainMenuApp(username); // Adjust to pass username
                    mainMenu.setVisible(true);
                    dispose(); //
                });
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
        signUpButton.setFont(new Font("SansSerif", Font.PLAIN, 32));
        signUpButton.addActionListener(e -> cardLayout.show(mainPanel, "SignUp"));
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(signUpButton, c);

        return panel;
    }

    // Method to create Sign-Up Panel with added validation
    private JPanel signUpPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();

        JLabel signUpLabel = new JLabel("Create an Account");
        signUpLabel.setFont(new Font("Serif", Font.BOLD, 26));
        signUpLabel.setForeground(new Color(70, 130, 180));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(20, 10, 20, 10);
        panel.add(signUpLabel, c);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 20, 15);
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(firstNameLabel, c);

        JTextField firstNameField = new JTextField(15);
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(firstNameField, c);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(5, 10, 20, 15);
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(lastNameLabel, c);

        JTextField lastNameField = new JTextField(15);
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(lastNameField, c);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(5, 10, 20, 15);
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(phoneNumberLabel, c);

        JTextField phoneNumberField = new JTextField(15);
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(phoneNumberField, c);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(5, 10, 20, 15);
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(usernameLabel, c);

        JTextField usernameField = new JTextField(15);
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(usernameField, c);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(5, 10 , 20, 15);
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(passwordLabel, c);

        JPasswordField passwordField = new JPasswordField(15);
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(passwordField, c);

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String phoneNumber = phoneNumberField.getText();
        
            // Validate first name and last name (alphabet only)
            if (!firstName.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(this, "First name must contain only letters!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!lastName.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(this, "Last name must contain only letters!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // Validate username (alphabet + number, but not number only)
            if (!username.matches("[a-zA-Z0-9]+") || username.matches("^[0-9]+$")) {
                JOptionPane.showMessageDialog(this, "Username must contain only letters or numbers, but cannot be only numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // Check if username already exists
            if (userCredentials.containsKey(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists! Please choose a different one.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // Validate phone number format (basic validation for now)
            if (!phoneNumber.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "Phone number must be 10 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // Validate password format
            if (!isValidPassword(password)) {
                JOptionPane.showMessageDialog(this, "Password must contain at least one uppercase letter, one lowercase letter, one special character, and be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // If all validations pass, create account
            userCredentials.put(username, password);
            JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        c.insets = new Insets(20, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        panel.add(createAccountButton, c);

        JButton backToLoginButton = new JButton("Back to Login");
        backToLoginButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        panel.add(backToLoginButton, c);

        return panel;
    }

    // Mock method for password validation
    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,}$");
    }

    // Mock log in method
    private boolean logIn(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }

    // Load remembered username from preferences
    private void loadRememberedUsername() {
        String savedUsername = prefs.get("username", "");
        if (!savedUsername.isEmpty()) {
            usernameField.setText(savedUsername);
            rememberMeCheckBox.setSelected(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginSignupPage());
    }
}