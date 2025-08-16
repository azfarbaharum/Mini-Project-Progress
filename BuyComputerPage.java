import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

class BuyComputerPage extends JPanel {
    private ShoppingCart cart; // Use a shared ShoppingCart instance

    public BuyComputerPage(CardLayout cardLayout, JPanel mainPanel, ShoppingCart cart) {
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

        // Computer Items
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridLayout(0, 2, 10, 10));
        itemsPanel.setBackground(Color.WHITE);

        // Add computer items with unique specifications
        itemsPanel.add(createComputerItem(
            "Asus TUF F-15", 
            "RM 3799.00", 
            "C://Users//rajaa//Documents//Semester 3//Java Mini Project//JAVA DETAILS//ASUS TUF F 15.png", 
            List.of("Intel® Core™ i5-10300H", "Windows 10 (64bit)", " 15.6 FHD / IPS Panel / 144Hz / 67% sRGB / 170 Wide View / Anti-glare", "Graphics: NVIDIA GTX 1650"),
            "AsusTUFDetails", 
            cardLayout, 
            mainPanel
        ));
        itemsPanel.add(createComputerItem(
            "Acer Nitro V15", 
            "RM 3594.00", 
            "C://Users//rajaa//Documents//Semester 3//Java Mini Project//JAVA DETAILS//ACER NITRO.PNG", 
            List.of("NVIDIA® GeForce® RTX2050 4GB GDDR6", "Windows 11 Home", "Intel® Core™ i5-13420H", "15.6144Hz SlimBezel FHD IPS Display"),
            "AcerNitroDetails", 
            cardLayout, 
            mainPanel
        ));
        itemsPanel.add(createComputerItem(
            "LENOVO LEGION 5 15IRX9", 
            "RM 5799.00", 
            "C://Users//rajaa//Documents//Semester 3//Java Mini Project//JAVA DETAILS//LENOVO LEGION LAPTOP.jpg/", 
            List.of("Intel Core i7-13650HX Processor", "NVIDIA GeForce RTX 4060 8GB GDDR6", "512GB SSD M.2 2242 PCIe 4.0x4 NVMe", "Windows 11 Home"),
            "LenovoLegionDetails", 
            cardLayout, 
            mainPanel
        ));
        itemsPanel.add(createComputerItem(
            "ASUS VIVOBOOK GO", 
            "RM 1599.00", 
            "C://Users//rajaa//Documents//Semester 3//Java Mini Project//JAVA DETAILS//ASUS VIVOBOOK GO.jpg/", 
            List.of("Intel HD Graphics", "RAM: 8GB DDR4 OB RAM", "Storage: 512GB M.2 NVMe PCie SSD", "Graphics: Intel HD Graphics"),
            "AsusVivobookDetails", 
            cardLayout, 
            mainPanel
        ));
        itemsPanel.add(createComputerItem(
            "ACER ASPIRE LITE 15", 
            "RM 1599.00", 
            "C://Users//rajaa//Documents//Semester 3//Java Mini Project//JAVA DETAILS//ACER ASPIRE LITE 15.PNG/", 
            List.of("Intel® Processor N100", "RAM: 8GB DDR5 RAM", "Storage: 512GB PCIe NVMe Gen4 SSD", "Graphics: Intel® UHD Graphics"),
            "AcerAspireDetails", 
            cardLayout, 
            mainPanel
        ));
        itemsPanel.add(createComputerItem(
            "ASUS VIVOBOOK FLIP", 
            "RM 4499.00", 
            "C://Users//rajaa//Documents//Semester 3//Java Mini Project//JAVA DETAILS//ASUS VIVOBOOK FLIP.PNG/", 
            List.of("Processor: Intel® Core™ i5-1135G7 Processor ", "RAM: 8GB LPDDR4X ", "Storage: 512GB M.2 NVMe™ PCIe® 3.0 SSD", "Intel Iris Xᵉ Graphics "),
            "AsusVivobookFlipDetails", 
            cardLayout, 
            mainPanel
        ));
        itemsPanel.add(createComputerItem(
            "Lenovo Thinkpad E16 Gen 1", 
            "RM2790.03", 
            "C://Users//rajaa//Documents//Semester 3//Java Mini Project//JAVA DETAILS//lenovo thinkpad e16 gen 1.png/", 
            List.of("Intel® Core™ i5-13420H Processor", "RAM: 8 GB DDR4-3200MHz ", "Storage: 256 GB SSD M.2 2242 PCIe Gen4 TLC Opal", "Integrated Intel® UHD Graphics"),
            "LenovoThinkpadDetails", 
            cardLayout, 
            mainPanel
        ));
        itemsPanel.add(createComputerItem(
            "Msi Cyborg 15 A12ve 682 Gaming Laptops ", 
            "RM 3399.00", 
            "C://Users//rajaa//Documents//Semester 3//Java Mini Project//JAVA DETAILS//MSI LAPTOP.PNG/", 
            List.of("Intel® Core™ i5-12450H", "RAM: DDR V 8 GB x 2 4800MHz", "Storage: 512 GB NVM PCI-e SSD Gen 4x4", "Graphics: NVIDIA® GeForce RTX™ 4050 Laptop GPU 6 GB GDDR6"),
            "MsiCyborgDetails", 
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

    private JPanel createComputerItem(String name, String price, String imagePath, List<String> specifications, String detailPage, CardLayout cardLayout, JPanel mainPanel) {
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
                ComputerDetailsPage detailsPage = new ComputerDetailsPage(name, price, imagePath, specifications, cardLayout, mainPanel, cart);
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
