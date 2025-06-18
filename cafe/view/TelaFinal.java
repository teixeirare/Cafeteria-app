package cafe.view;

import javax.swing.*;

import cafe.model.BotaoArredondado;

import java.awt.*;
//import cafe.view.TelaInicio;

public class TelaFinal extends JFrame {

    public TelaFinal(String cliente) {
        setTitle("Pedido");
        setSize(800, 820);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JPanel painel = new JPanel(null);
        painel.setBounds(0, 0, 800, 820);
        painel.setBackground(new Color(222, 184, 135));
        add(painel);

         
        JLabel aprovado = new JLabel("APROVADO!");
        aprovado.setBounds(170, 120, 500, 60);
        aprovado.setFont(new Font("Arial", Font.BOLD, 60));
        aprovado.setHorizontalAlignment(SwingConstants.CENTER);
        aprovado.setForeground(Color.WHITE);
        painel.add(aprovado);

        JLabel mensagem = new JLabel("PEDIDO FEITO COM SUCESSO!");
        mensagem.setBounds(250, 200, 320, 30);
        mensagem.setFont(new Font("Arial", Font.BOLD, 20));
        mensagem.setHorizontalAlignment(SwingConstants.CENTER);
        mensagem.setForeground(Color.WHITE);
        painel.add(mensagem);

        JLabel resposta = new JLabel(cliente + " seu pedido está sendo preparado!");
        resposta.setBounds(160, 250, 500, 50);
        resposta.setFont(new Font("Arial", Font.BOLD, 20));
        resposta.setHorizontalAlignment(SwingConstants.CENTER);
        resposta.setForeground(Color.black);
        painel.add(resposta);

        JButton voltar = new BotaoArredondado("Fazer outro pedido");
        voltar.setBounds(270, 550, 260, 40);
        voltar.setFont(new Font("Arial", Font.BOLD, 18));
        voltar.setBackground(Color.white);
        voltar.setForeground(Color.black);
        painel.add(voltar);

        JButton finalizar = new BotaoArredondado("Finalizar");
        finalizar.setBounds(270, 650, 260, 40);
        finalizar.setFont(new Font("Arial", Font.BOLD, 18));
        finalizar.setBackground(Color.BLACK);
        finalizar.setForeground(Color.white);
        painel.add(finalizar);

       

        JLabel imagem = criarImagem();
        painel.add(imagem);
       

        // ================================================================================================

        ImageIcon iconHome = new ImageIcon("C:/dev/JS/treinamento_java/cafeteria/imagens/final.png");
        Image img = iconHome.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JButton botaoInicio = new JButton(new ImageIcon(img));
        botaoInicio.setBounds(730, -5, 60, 60);
        botaoInicio.setContentAreaFilled(false); // remove fundo
        botaoInicio.setBorderPainted(false);    // remove borda
        botaoInicio.setFocusPainted(false);
        botaoInicio.setToolTipText("Voltar ao início");
        painel.add(botaoInicio);

        //===========================================================================================================

        voltar.addActionListener(e -> {
            new TelaPedido();
            dispose();
        });

        finalizar.addActionListener(e -> {
            for (Window w : Window.getWindows()) {
                w.dispose();
            }
            new TelaInicio();
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

