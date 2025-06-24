package cafe.view;

import javax.swing.*;

import cafe.model.BotaoArredondado;

import java.awt.*;
//import cafe.view.TelaInicio;

public class TelaFinal extends JFrame {

    public TelaFinal(String cliente) {
        setTitle("FINAL");
        setSize(800, 820);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JPanel painel = new JPanel(null);
        painel.setBounds(0, 0, 800, 820);
        painel.setBackground(new Color(222, 184, 135));
        add(painel);

         
        JLabel aprovado = new JLabel("APPROVED!");
        aprovado.setBounds(170, 340, 500, 70);
        aprovado.setFont(new Font("Arial", Font.BOLD, 70));
        aprovado.setHorizontalAlignment(SwingConstants.CENTER);
        aprovado.setForeground(Color.WHITE);
        painel.add(aprovado);

        JLabel mensagem = new JLabel("ORDER SUCCESSFUL!");
        mensagem.setBounds(250, 450, 320, 30);
        mensagem.setFont(new Font("Arial", Font.BOLD, 20));
        mensagem.setHorizontalAlignment(SwingConstants.CENTER);
        mensagem.setForeground(Color.WHITE);
        painel.add(mensagem);

        JLabel resposta = new JLabel(cliente + " your order is being prepared!");
        resposta.setBounds(160, 480, 500, 50);
        resposta.setFont(new Font("Arial", Font.BOLD, 20));
        resposta.setHorizontalAlignment(SwingConstants.CENTER);
        resposta.setForeground(Color.white);
        painel.add(resposta);

        JButton finalizar = new BotaoArredondado("Finish");
        finalizar.setBounds(270, 550, 280, 50);
        finalizar.setFont(new Font("Arial", Font.PLAIN, 20));
        finalizar.setBackground(Color.DARK_GRAY);
        finalizar.setForeground(Color.white);
        painel.add(finalizar);

        JLabel imagem = criarImagem();
        JLabel xicara = criarImagem2();
        painel.add(imagem);
        painel.add(xicara);
       

        

      
        //===========================================================================================================

        


        finalizar.addActionListener(e -> {
            for (Window w : Window.getWindows()) {
                w.dispose();
            }
            new TelaInicio();
        });

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
    
    private JLabel criarImagem2() {
        String caminho = "C:/dev/JS/treinamento_java/cafeteria/imagens/xicarates.jpg";
        ImageIcon icon = new ImageIcon(caminho);
        //Image imagemRedimensionada = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(icon);
        label.setBounds(250, 250, 400, 400);
        return label;
    }

       
}

