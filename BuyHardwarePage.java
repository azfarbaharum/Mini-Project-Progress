import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

class BuyHardwarePage extends JPanel {
    private ShoppingCart cart; // Use a shared ShoppingCart instance

    public BuyHardwarePage(CardLayout cardLayout, JPanel mainPanel, ShoppingCart cart) {
        this.cart = cart; // Initialize with the shared cart
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFocusPainted(false);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.decode("#1A1A3D"));
        backButton.setFont(new Font("Arial", Font.PLAIN, 32));
        backButton.setPreferredSize(new Dimension(1920, 90));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MainMenu"));

        // Hardware Items
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridLayout(0, 2, 10, 10));
        itemsPanel.setBackground(Color.WHITE);

        // Add hardware items with unique specifications
        itemsPanel.add(createHardwareItem(
            "Asus ROG Strix Z390-F LGA 1151 ATX Gaming Motherboard", 
            "RM 559.99", 
            "C://Users//rajaa//Documents//Semester 3//Java Mini Project//JAVA DETAILS//MOTHERBOARD.png", 
            List.of("Socket: LGA1151", "•\tAura Sync RGB: Synchronize LED lighting with a vast portfolio of compatible PC gear, including addressable RGB strips", "•\tComprehensive cooling: Onboard M.2 heatsink, water-pump header and a fan-extension header", "•\tGaming networking: Intel Gigabit Ethernet, LANGuard and GameFirst"), 
            "MotherboardDetails", 
            cardLayout, 
            mainPanel
        ));
        itemsPanel.add(createHardwareItem(
            "Razer Ornata V3 X", 
            "RM 155.00", 
            "C://Users//rajaa//Documents//Semester 3//Java Mini Project//JAVA DETAILS//KEYBOARD (COMPONENT SUB MENU).png", 
            List.of("Silent Membrane Switches", "Lighting: Single Zone Razer Chroma™ RGB Lighting", "Wrist rest: yes", "Connectivity- wired- attached"), 
            "KeyboardDetails", 
            cardLayout, 
            mainPanel
        ));
        itemsPanel.add(createHardwareItem(
            "JBL Quantum 400 USB over-ear gaming headset", 
            "RM 599.00", 
            "C://Users//rajaa//Documents//Semester 3//Java Mini Project//JAVA DETAILS//JBL HEADSET.PNG", 
            List.of("Driver Size: 50mm", "Connection: USB/3.5mm", "Surround Sound: QuantumSURROUND", "Mic: Boom Mic"), 
            "HeadsetDetails", 
            cardLayout, 
            mainPanel
        ));
        itemsPanel.add(createHardwareItem(
            "Razer Basilisk V3 Pro 35K", 
            "RM 716.78", 
            "C://Users//rajaa//Documents//Semester 3//Java Mini Project//JAVA DETAILS//RAZER MOUSE.jpeg", 
            List.of("Sensor: Razer Focus+ 35K", "Connectivity: Wireless/Wired", "RGB: Yes", "Programmable Buttons: 11"), 
            "MouseDetails", 
            cardLayout, 
            mainPanel
        ));
        itemsPanel.add(createHardwareItem(
            "STEELSERIES Arctis Pro (RGB) - PC Gaming Headset", 
            "RM 889.00", 
            "C://Users//rajaa//Documents//Semester 3//Java Mini Project//JAVA DETAILS//STEELSERIES HEADPHONE.PNG", 
            List.of("GameDAC: High Fidelity digital to analog converter (DAC)", "Audiophile grade sound with superb detail", "Premium Hi-Res speakers", "Luxurious polished steel and aluminum alloy construction"), 
            "HeadsetDetails", 
            cardLayout, 
            mainPanel
        ));
        itemsPanel.add(createHardwareItem(
            "SteelSeries Aerox 3 Wired Ultra Gaming Mouse", 
            "RM 289.00", 
            "C://Users//rajaa//Documents//Semester 3//Java Mini Project//JAVA DETAILS//STEELSERIES MOUSE.PNG", 
            List.of("Sensor: SteelSeries TrueMove Core", "Sensor Type: Optical", "Polling Rate: 1000Hz / 1 ms", "tWeight: 59g"), 
            "MouseDetails", 
            cardLayout, 
            mainPanel
        ));
        itemsPanel.add(createHardwareItem(
            "SSD M.2 980PRO/990PRO WITH HEATSINK", 
            "RM 643.99", 
            "C://Users//rajaa//Documents//Semester 3//Java Mini Project//JAVA DETAILS//SAMSUNG SSD.PNG", 
            List.of("Interface: PCIe Gen 4.0 x4, NVMe 1.3c", "Compatibility: PC & Playstation® 5", "Weight: Max 30.5g", "Read/Write Speeds: Up to 7,000/5,000MB/s"), 
            "SSDDetails", 
            cardLayout, 
            mainPanel
        ));
        itemsPanel.add(createHardwareItem(
            "NZXT RL-KN360-B1 Kraken 360 360mm AIO Liquid Cooler with LCD Display", 
            "RM896.00", 
            "C://Users//rajaa//Documents//Semester 3//Java Mini Project//JAVA DETAILS//LIQUID COOLER HARDWARE.PNG", 
            List.of("Compatible with NZXT H710 Minimalist Gaming PC Case, NZXT N5 Z690 Premium Intel Gaming Motherboard, and NZXT C1000 PSU", "Includes 3 fans with a maximum speed of 1800 rpm and a minimum speed of 500 rpm", "Features a fluid dynamic bearing for reliable fan operation", "Offers a maximum air pressure of 2.7mmH2O for efficient cooling"), 
            "CoolerDetails", 
            cardLayout, 
            mainPanel
        ));
        // Add more items with their respective specifications...

        JScrollPane scrollPane = new JScrollPane(itemsPanel);

        // Slim the scrollbars
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(20, 0)); // Slim vertical scrollbar
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 8)); // Slim horizontal scrollbar (if used)
        
        // Change colors
        scrollPane.getVerticalScrollBar().setBackground(Color.RED); // Track color
        scrollPane.getVerticalScrollBar().setForeground(Color.RED); // Thumb color
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove the border

        add(backButton, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createHardwareItem(String name, String price, String imagePath, List<String> specifications, String detailPage, CardLayout cardLayout, JPanel mainPanel) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel imageLabel = new JLabel(new ImageIcon(imagePath));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Create the details page dynamically with unique specifications
                HardwareDetailsPage detailsPage = new HardwareDetailsPage(name, price, imagePath, specifications, cardLayout, mainPanel, cart);
                mainPanel.add(detailsPage, detailPage); // Ensure detailPage is unique for each item
                cardLayout.show(mainPanel, detailPage); // Show the new details page
            }
        });

        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(nameLabel);
        panel.add(Box.createVerticalStrut(1));
        panel.add(imageLabel);
        panel.add(Box.createVerticalStrut(1));
        panel.add(priceLabel);

        return panel;
    }
}
