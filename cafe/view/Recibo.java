package cafe.view;

import cafe.controller.PedidoController;
import cafe.model.BotaoArredondado;

import javax.swing.*;
import java.awt.*;

public class Recibo extends JFrame {

    private final PedidoController pedidoController;

    public Recibo(PedidoController pedidoController) {
        this.pedidoController = pedidoController;
        inicializar();
    }

    private void inicializar() {
        setTitle("Recibo");
        setSize(800, 820);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JPanel painel = new JPanel(null);
        painel.setBounds(0, 0, 800, 820);
        painel.setBackground(new Color(222, 184, 135));
        

        // Imagem
        

        // Texto com recibo

        int largura = 500;
        int altura = 500;
        int x = (getWidth() - largura) / 2;
        int y = (getHeight() - altura) / 2;

        JLabel reciboTexto = new JLabel(pedidoController.gerarReciboHTML(), SwingConstants.CENTER);
        reciboTexto.setBounds(x, y, largura, altura);
        reciboTexto.setFont(new Font("Arial", Font.PLAIN, 15));
        reciboTexto.setForeground(Color.white);
        reciboTexto.setHorizontalAlignment(SwingConstants.CENTER);
        painel.add(reciboTexto);

        // Botões de pagamento
        JButton botaoCredito = new BotaoArredondado("CRÉDITO");
        botaoCredito.setBounds( 200, 680, 130, 30);
        botaoCredito.setBackground(Color.white);
        botaoCredito.setForeground(Color.black);
        botaoCredito.setFont(new Font("Arial", Font.PLAIN, 15));

        JButton botaoDebito = new BotaoArredondado("DÉBITO");
        botaoDebito.setBounds(340, 680,130, 30);
        botaoDebito.setFont(new Font("Arial", Font.PLAIN, 15));
        botaoDebito.setHorizontalAlignment(SwingConstants.CENTER);
        botaoDebito.setBackground(Color.white);
        botaoDebito.setForeground(Color.black);

        JButton botaoDinheiro =new BotaoArredondado("DINHEIRO");
        botaoDinheiro.setBounds(480, 680,130, 30);
        botaoDinheiro.setFont(new Font("Arial", Font.PLAIN, 15));
        botaoDinheiro.setBackground(Color.white);
        botaoDinheiro.setForeground(Color.black);

        JButton botaoPagar = new BotaoArredondado("PAGAR");
        botaoPagar.setBounds(270, 720,130, 30);
        botaoPagar.setFont(new Font("Arial", Font.PLAIN, 15));
        //botaoPagar.setBackground(Color.white);
        //botaoPagar.setForeground(Color.black);

        JButton botaoCancelar = new BotaoArredondado("CANCELAR");
        botaoCancelar.setBounds(430, 720,130, 30);
        botaoCancelar.setFont(new Font("Arial", Font.PLAIN, 15));
        //botaoCancelar.setBackground(Color.white);
        // botaoCancelar.setForeground(Color.black);

        botaoPagar.setEnabled(false);

        add(painel);
        painel.add(botaoCredito);
        painel.add(botaoDebito);
        painel.add(botaoDinheiro);
        painel.add(botaoPagar);
        painel.add(botaoCancelar);

        JLabel imagem = criarImagem();
        painel.add(imagem);

        // ==========================================================================================

        botaoCancelar.addActionListener(e -> dispose());

        botaoCredito.addActionListener(e -> {
            destacarSelecionado(botaoCredito, botaoDebito, botaoDinheiro);
            botaoPagar.setEnabled(true);
        });

        botaoDebito.addActionListener(e -> {
            destacarSelecionado(botaoDebito, botaoCredito, botaoDinheiro);
            botaoPagar.setEnabled(true);
        });

        botaoDinheiro.addActionListener(e -> {
            PagamentoDinheiro janelaDinheiro = new PagamentoDinheiro(this, pedidoController);
            destacarSelecionado(botaoDinheiro, botaoCredito, botaoDebito);
            janelaDinheiro.setLocation(getX(), getY());
            botaoPagar.setEnabled(false);
        });

        botaoPagar.addActionListener(e -> {
            new TelaFinal(pedidoController.getCliente());
            dispose();
        });

        setVisible(true);
    }

    /*private JButton criarBotao(String texto, int x, int y) {
        JButton botao = new JButton(texto);
        botao.setBounds(x, y, 100, 30);
        botao.setBackground(Color.white);
        botao.setForeground(Color.black);
        return botao;
    }*/

    private void destacarSelecionado(JButton selecionado, JButton... outros) {
        selecionado.setBackground(Color.darkGray);
        selecionado.setForeground(Color.white);
       for (JButton outro : outros) {
            outro.setBackground(Color.white);
            outro.setForeground(Color.black);
        }
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


