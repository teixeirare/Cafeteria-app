package cafe.view;

import cafe.controller.PedidoController;
//import cafe.model.Pedido;
import cafe.model.BotaoArredondado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PagamentoDinheiro extends JFrame {

    public PagamentoDinheiro(JFrame telaAnterior, PedidoController pedidoController) {
        setTitle("Pagamento em Dinheiro");
        setSize(800, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(222, 184, 135));
        painel.setBounds(0, 0, 800, 150);
        add(painel);

        JLabel labelValor = new JLabel("Valor Recebido:");
        labelValor.setBounds(10, 15, 150, 25);
        labelValor.setFont(new Font("Arial", Font.PLAIN, 18));
        labelValor.setForeground(Color.white);
        painel.add(labelValor);

        JTextField campoValor = new JTextField();
        campoValor.setBounds(150, 15, 100, 25);
        campoValor.setFont(new Font("Arial", Font.PLAIN, 17));
        painel.add(campoValor);

        JButton calcular = new BotaoArredondado("Calcular");
        calcular.setBounds(270, 15, 130, 30);
        calcular.setFont(new Font("Arial", Font.PLAIN, 15));
        calcular.setBackground(Color.white);
        calcular.setForeground(Color.black);
        painel.add(calcular);

        JLabel labelTroco = new JLabel("Troco:");
        labelTroco.setBounds(10, 50, 100, 25);
        labelTroco.setFont(new Font("Arial", Font.PLAIN, 17));
        labelTroco.setForeground(Color.white);
        painel.add(labelTroco);

        JLabel trocoResultado = new JLabel("R$ 0.00");
        trocoResultado.setBounds(150, 50, 100, 25);
        trocoResultado.setFont(new Font("Arial", Font.PLAIN, 17));
        trocoResultado.setForeground(Color.white);
        painel.add(trocoResultado);

        JButton cancelar = new BotaoArredondado("Cancelar");
        cancelar.setBounds(405, 15, 130, 30);
        cancelar.setFont(new Font("Arial", Font.PLAIN, 15));
        cancelar.setBackground(Color.white);
        cancelar.setForeground(Color.black);
        painel.add(cancelar);

        JButton pago = new BotaoArredondado("Pago");
        pago.setBounds(540, 15, 130, 30);
        pago.setFont(new Font("Arial", Font.PLAIN, 15));
        pago.setBackground(Color.white);
        pago.setForeground(Color.black);
        pago.setEnabled(false);
        painel.add(pago);

        JLabel imagem = criarImagem();
        painel.add(imagem);

        calcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    float valorRecebido = Float.parseFloat(campoValor.getText());
                    float troco = valorRecebido - pedidoController.getTotal();

                    if (troco < 0) {
                        JOptionPane.showMessageDialog(null, "Valor insuficiente.", "Erro", JOptionPane.ERROR_MESSAGE);
                        trocoResultado.setText("R$ 0.00");
                        pago.setEnabled(false);
                    } else {
                        trocoResultado.setText(String.format("R$ %.2f", troco));
                        pago.setEnabled(true);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite um valor válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    pago.setEnabled(false);
                }
            }
        });

        cancelar.addActionListener(e -> dispose());
        
        

        pago.addActionListener(e -> {
            new TelaFinal(pedidoController.getCliente());
            telaAnterior.dispose();
            dispose();
        });

        setVisible(true);
    }

    private JLabel criarImagem() {
        String caminho = "C:/dev/JS/treinamento_java/cafeteria/imagens/fundo.jpg";
        ImageIcon icon = new ImageIcon(caminho);
        Image imagemRedimensionada = icon.getImage().getScaledInstance(800, 820, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(imagemRedimensionada));
        label.setBounds(0, 0, 800, 820);
        return label;
    }
}
