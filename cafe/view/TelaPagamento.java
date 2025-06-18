package cafe.view;

import cafe.controller.PedidoController;
//import cafe.model.Pedido;
import cafe.model.BotaoArredondado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaPagamento extends JFrame {

    private final PedidoController pedidoController;

    public TelaPagamento(PedidoController pedidoController) {
        this.pedidoController = pedidoController;
        inicializar();
    }

    private void inicializar() {
        setTitle("Pagamento");
        setSize(407, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JPanel painel = new JPanel(null);
        painel.setBounds(0, 0, 407, 550);
        painel.setBackground(new Color(222, 184, 135));
        add(painel);

        JLabel reciboTexto = new JLabel(pedidoController.gerarReciboHTML());
        reciboTexto.setBounds(30, 90, 350, 300);
        reciboTexto.setFont(new Font("Arial", Font.PLAIN, 12));
        reciboTexto.setForeground(Color.black);
        painel.add(reciboTexto);

        JButton botaoCredito = new BotaoArredondado("CRÉDITO");
        botaoCredito.setBounds( 30, 400, 100, 25);
        JButton botaoDebito = criarBotao("DÉBITO", 145, 400);
        JButton botaoDinheiro = criarBotao("DINHEIRO", 260, 400);
        JButton botaoPagar = criarBotao("PAGAR", 60, 450);
        JButton botaoCancelar = criarBotao("CANCELAR", 220, 450);

        botaoPagar.setEnabled(false);

        painel.add(botaoCredito);
        painel.add(botaoDebito);
        painel.add(botaoDinheiro);
        painel.add(botaoPagar);
        painel.add(botaoCancelar);

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
            new PagamentoDinheiro(this, pedidoController);
            destacarSelecionado(botaoDinheiro, botaoCredito, botaoDebito);
            botaoPagar.setEnabled(false);
        });

        botaoPagar.addActionListener((ActionEvent e) -> {
            new TelaFinal(pedidoController.getCliente());
            dispose();
        });

        setVisible(true);
    }

    private JButton criarBotao(String texto, int x, int y) {
        JButton botao = new JButton(texto);
        botao.setBounds(x, y, 100, 30);
        botao.setBackground(Color.white);
        botao.setForeground(Color.black);
        return botao;
    }

    private void destacarSelecionado(JButton selecionado, JButton... outros) {
        selecionado.setBackground(Color.darkGray);
        selecionado.setForeground(Color.white);
        for (JButton outro : outros) {
            outro.setBackground(Color.white);
            outro.setForeground(Color.black);
        }
    }
}
