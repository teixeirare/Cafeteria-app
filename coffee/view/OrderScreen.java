package coffee.view;

import javax.swing.*;
import coffee.controller.RequestController;
import coffee.model.JLabelShadow;
import coffee.model.Product;
import coffee.model.ProductRepository;
import coffee.model.RoundedBoot;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderScreen extends JFrame {
    private final RequestController requestController;
    private final JLabel lblTotal;
    private final Map<Product, JLabel> mapLabelsQuantity = new HashMap<>();
    private final Map<Product, JButton> buttonMap = new HashMap<>();
    private Product lastSelected;

    public OrderScreen() {
        this.requestController = new RequestController();
        setTitle("ORDER");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int widthSize = screenSize.width;
        int heightSize = screenSize.height;

        // =============================================== Title ===========================================

        int titleWidth = 600;
        JLabel title = new JLabel("CHOOSE YOUR COFFEE", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setForeground(Color.white);
        title.setBounds((widthSize - titleWidth) / 2, (int) (heightSize * 0.06), titleWidth, 55);
        backgroundPanel.add(title);

        // ================================== Central Name field ============================================

        int fieldWidth = 170;
        JLabel lblName = new JLabelShadow("Name:");
        lblName.setFont(new Font("Arial", Font.BOLD, 20));
        lblName.setForeground(Color.white);
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblName.setBounds((widthSize / 2) - fieldWidth - 20, (int) (heightSize * 0.14), 80, 30);
        backgroundPanel.add(lblName);

        JTextField fieldName = new JTextField();
        fieldName.setFont(new Font("Arial", Font.BOLD, 18));
        fieldName.setHorizontalAlignment(SwingConstants.CENTER);
        fieldName.setBounds((widthSize / 2) - fieldWidth + 65, (int) (heightSize * 0.14), fieldWidth, 25);
        backgroundPanel.add(fieldName);

        // ================================= Products: two centered columns =================================

        List<Product> products = ProductRepository.listProducts();

        int n = products.size();
        int rowHeight = 60;
        int btnWidth = 250;
        int btnHeight = 35;
        int columnSpacing = 100;
        int colSize = (int) Math.ceil(n / 2.0);

        int xColEsq = (widthSize / 2) - columnSpacing - btnWidth;
        int xColDir = (widthSize / 2) + columnSpacing;
        int yStart = (int) (heightSize * 0.22);

        for (int i = 0; i < n; i++) {
            Product product = products.get(i);
            int col = (i < colSize) ? 0 : 1;
            int row = (i < colSize) ? i : i - colSize;
            int x = (col == 0) ? xColEsq : xColDir;
            int y = yStart + row * rowHeight;

        // ================================= Create button for each product ================================

            JButton jbnButton = new RoundedBoot(String.valueOf(product));
            jbnButton.setBounds(x, y, btnWidth, btnHeight);
            jbnButton.setHorizontalAlignment(SwingConstants.LEFT);
            jbnButton.setFont(new Font("Arial", Font.ITALIC, 17));
            backgroundPanel.add(jbnButton);

            JLabel qtdLabel = new JLabelShadow("");
            qtdLabel.setBounds(x - 35, y + 7, 34, 24);
            qtdLabel.setFont(new Font("Arial", Font.BOLD, 17));
            qtdLabel.setForeground(Color.WHITE);
            backgroundPanel.add(qtdLabel);
            mapLabelsQuantity.put(product, qtdLabel);

            buttonMap.put(product, jbnButton);

            jbnButton.addActionListener(e -> {
                requestController.addProduct(product);
                updateQuantity(product);
                updateTotal();
                lastSelected = product;
            });
        }

        // ====================================== Button Home ===============================================

        ImageIcon iconHome = new ImageIcon("C:/cafeteria/imagens/home.png");
        Image img = iconHome.getImage().getScaledInstance(38, 38, Image.SCALE_SMOOTH);
        JButton jbnHome = new JButton(new ImageIcon(img));
        jbnHome.setBounds(widthSize - 65, 24, 38, 38);
        jbnHome.setContentAreaFilled(false);
        jbnHome.setBorderPainted(false);
        jbnHome.setFocusPainted(false);
        backgroundPanel.add(jbnHome);

        // ========================================= Price central ===========================================

        int priceY = heightSize - 190;
        JLabel lblPrice = new JLabelShadow("PRICE:");
        lblPrice.setFont(new Font("Arial", Font.BOLD, 15));
        lblPrice.setForeground(Color.white);
        lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPrice.setBounds((widthSize / 2) - 60, priceY, 70, 30);
        backgroundPanel.add(lblPrice);

        lblTotal = new JLabelShadow("$ 0,00");
        lblTotal.setFont(new Font("Arial", Font.ITALIC, 15));
        lblTotal.setForeground(Color.white);
        lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
        lblTotal.setBounds((widthSize / 2) - 0, priceY, 100, 34);
        backgroundPanel.add(lblTotal);

        // ================================== Action buttons centralized =====================================

        int buttonY = heightSize - 150;
        int widthPay = 200;
        int widthAddRemove = 43;
        int spacing = 36;
        int centerX = widthSize / 2;

        int xPay = centerX - (widthPay / 2);
        int xAdd = xPay - widthAddRemove - spacing;
        int xRemove = xPay + widthPay + spacing;

        JButton jbnAdd = new RoundedBoot("+");
        jbnAdd.setBounds(xAdd, buttonY, widthAddRemove, 30);
        jbnAdd.setFont(new Font("Arial", Font.BOLD, 15));
        jbnAdd.setBackground(Color.DARK_GRAY);
        jbnAdd.setForeground(Color.WHITE);
        backgroundPanel.add(jbnAdd);

        JButton jbnPay = new RoundedBoot("PAY");
        jbnPay.setBounds(xPay, buttonY, widthPay, 30);
        jbnPay.setFont(new Font("Arial", Font.PLAIN, 15));
        jbnPay.setHorizontalAlignment(SwingConstants.CENTER);
        jbnPay.setBackground(Color.DARK_GRAY);
        jbnPay.setForeground(Color.WHITE);
        backgroundPanel.add(jbnPay);

        JButton jbnRemove = new RoundedBoot("-");
        jbnRemove.setBounds(xRemove, buttonY, widthAddRemove, 30);
        jbnRemove.setFont(new Font("Arial", Font.BOLD, 15));
        jbnRemove.setBackground(Color.DARK_GRAY);
        jbnRemove.setForeground(Color.WHITE);
        backgroundPanel.add(jbnRemove);

        // =========================================== Acions ===============================================

        jbnHome.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null,
             "Do you want to go back?", "Back", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                new StartScreen();
                dispose();
            }
        });

        jbnAdd.addActionListener(e -> {
            if (lastSelected != null) {
                requestController.addProduct(lastSelected);
                updateQuantity(lastSelected);
                updateTotal();
            }
        });

        jbnRemove.addActionListener(e -> {
            if (lastSelected != null) {
                requestController.removeProduct(lastSelected);
                updateQuantity(lastSelected);
                updateTotal();
            }
        });

        jbnPay.addActionListener(e -> {
            String nameClient = fieldName.getText().trim();
            if (nameClient.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Type in the customer's name!");
                return;
            }
            requestController.setClient(nameClient);
            new ReceiptScreen(requestController);
        });

        setVisible(true);
        SwingUtilities.invokeLater(() -> fieldName.requestFocusInWindow());
    }

    private void updateQuantity(Product product) {
        int count = requestController.getQuantity(product);
        JLabel label = mapLabelsQuantity.get(product);
        if (label != null) {
            label.setText(count > 0 ? count + "x" : "");
        }
        JButton button = buttonMap.get(product);
        if (button != null) {
            if (count > 0) {
                button.setBackground(Color.DARK_GRAY);
                button.setForeground(Color.WHITE);
            } else {
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
            }
        }
    }


    private void updateTotal() {
        float total = requestController.calculateTotal();
        lblTotal.setText(String.format("$ %.2f", total).replace('.', ','));
    }

    // ======================= Customized background panel to fill the entire screen! ========================

    static class BackgroundPanel extends JPanel {
        private final Image backgroundImage;
        public BackgroundPanel() {
            String path = "C:/cafeteria/imagens/back.png";
            ImageIcon icon = new ImageIcon(path);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            backgroundImage = icon.getImage().getScaledInstance(
                    screenSize.width, screenSize.height, Image.SCALE_SMOOTH
            );
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }
    }

    // ============================================== Main Method ============================================
    // This is just for testing purposes, you can remove it later.


        public static void main(String[] args) {
        new OrderScreen(); // or any test name
    }
}





