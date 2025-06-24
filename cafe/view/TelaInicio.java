package cafe.view;

import javax.swing.*;

import cafe.model.BotaoArredondado;

import java.awt.*;

public class TelaInicio extends JFrame {

    public TelaInicio() {
        inicializar();
    }

    private void inicializar() {
        setTitle("COFFEE");
        setSize(800, 820);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JPanel painel = new JPanel(null);
        painel.setBounds(0, 0, 800, 820);
        painel.setBackground(Color.BLACK);
        add(painel);



        JButton botaoComecar = new BotaoArredondado("Start your order");

        botaoComecar.setBounds(270, 550, 280, 50);
        botaoComecar.setBackground(Color.DARK_GRAY);
        botaoComecar.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoComecar.setForeground(Color.white);
        painel.add(botaoComecar);

        JLabel imagem = criarImagem();
        painel.add(imagem);

        /*JButton telaPagamento = new BotaoArredondado("Finish");
        telaPagamento.setBounds(270, 550, 280, 50);
        telaPagamento.setFont(new Font("Arial", Font.PLAIN, 20));
        telaPagamento.setBackground(Color.DARK_GRAY);
        telaPagamento.setForeground(Color.white);
        telaPagamento.add(telaPagamento);*/

        botaoComecar.addActionListener(e -> {
            new TelaPedido();
            dispose();
        });

        /*telaPagamento.addActionListener(e -> {
            new TelaPagamento(null);
            dispose();
        });*/

        setVisible(true);
    }

    private JLabel criarImagem() {
        String caminho = "C:/dev/JS/treinamento_java/cafeteria/imagens/logo.jpg";
        ImageIcon icon = new ImageIcon(caminho);
        Image imagemRedimensionada = icon.getImage().getScaledInstance(800, 820, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(imagemRedimensionada));
        label.setBounds(0, 0, 800, 820);
        return label;
    }
}
