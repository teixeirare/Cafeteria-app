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
    private final JLabel labelTotal;
    private final Map<Product, JLabel> mapLabelsQuantity = new HashMap<>();
    private final Map<Product, JButton> buttonMap = new HashMap<>();
    private Product lastSelected;

    public OrderScreen() {
        this.requestController = new RequestController();
        setTitle("ORDER");
        setSize(800, 820);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 800, 820);
        panel.setBackground(new Color(222, 184, 135));
        add(panel);

        JLabel title = new JLabel("CHOOSE YOUR COFFEE");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setBounds(190, 20, 500, 35);
        title.setForeground(Color.white);
        panel.add(title);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 100, 100, 30);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        nameLabel.setForeground(Color.white);
        panel.add(nameLabel);

        JTextField fieldName = new JTextField();
        fieldName.setBounds(60, 100, 200, 25);
        fieldName.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(fieldName);
        

        List<Product> products = ProductRepository.listProducts();

        int y = 170;
        int index = 1;
        for (Product product : products) {

            JButton button = new RoundedBoot(String.valueOf(index));
            button.setBounds(40, y, 50, 30);
            panel.add(button);

            JLabel labelProduct = new JLabelShadow(product.toString());
            labelProduct.setBounds(100, y, 200, 20);
            labelProduct.setFont(new Font("Arial", Font.PLAIN, 15));
            //labelProduto.setForeground(Color.white);
            panel.add(labelProduct);

            JLabel qtdLabel = new JLabelShadow("0x");
            qtdLabel.setBounds(10, y, 30, 30);
            panel.add(qtdLabel);
            mapLabelsQuantity.put(product, qtdLabel);

            buttonMap.put(product, button);

           button.addActionListener(e -> {
                requestController.addProduct(product);
                updateQuantity(product);
                updateTotal();
                lastSelected = product;
            });

            y += 40;
            index++;
        }

        ImageIcon iconHome = new ImageIcon("C:/cafeteria/imagens/home.png");
        Image img = iconHome.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JButton jbnStart = new JButton(new ImageIcon(img));
        jbnStart.setBounds(730, -5, 60, 60);
        jbnStart.setContentAreaFilled(false); // remove fundo
        jbnStart.setBorderPainted(false);    // remove borda
        jbnStart.setFocusPainted(false);
        jbnStart.setToolTipText("Go back");
        panel.add(jbnStart);
        
        
        JButton jbnAdd = new RoundedBoot("+");
        jbnAdd.setBounds(240, 700, 50, 40);
        jbnAdd.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(jbnAdd);

        JButton jbnRemove = new RoundedBoot("-");
        jbnRemove.setBounds(490, 700, 50, 40);
        jbnRemove.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(jbnRemove);

        JButton jbnPay = new RoundedBoot("PAY");
        jbnPay.setBounds(330, 700, 130, 30);
        jbnPay.setFont(new Font("Arial", Font.PLAIN, 15));
        jbnPay.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(jbnPay);

        JLabel jbnPrice = new JLabelShadow("Price:");
        jbnPrice.setFont(new Font("Arial", Font.BOLD, 16));
        jbnPrice.setBounds(340, 740, 100, 30);
        panel.add(jbnPrice);

        labelTotal = new JLabelShadow("$ 0,00");
        labelTotal.setBounds(390, 740, 200, 30);
        panel.add(labelTotal);

        JLabel image = createImage();
        panel.add(image);

        // ================================ funçoes =======================================

        jbnStart.addActionListener(e -> {
            new StartScreen();
            dispose();;
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
        label.setText(count + "x");
    }

    JButton button = buttonMap.get(product);
    if (button != null) {
        if (count > 0) {
            button.setBackground(Color.DARK_GRAY); 
            button.setForeground(Color.WHITE);
            //botao.setOpaque(true);
            //botao.setBorderPainted(false);
        } else {
            button.setBackground(Color.WHITE); 
            button.setForeground(Color.BLACK);
            //botao.setOpaque(false);
            //botao.setBorderPainted(false);
        }
    }
}




    private void updateTotal() {
        float total = requestController.calculateTotal();
        labelTotal.setText(String.format("$ %.2f", total).replace('.', ','));
    }

     

    private JLabel createImage() {
        String path = "C:/cafeteria/imagens/fundo.jpg";
        ImageIcon icon = new ImageIcon(path);
        Image imageResized = icon.getImage().getScaledInstance(800, 820, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(imageResized));
        label.setBounds(0, 0, 800, 820);
        return label;
    }
}

