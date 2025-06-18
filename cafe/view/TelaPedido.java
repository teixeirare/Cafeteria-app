package cafe.view;

import cafe.controller.PedidoController;
import cafe.model.BotaoArredondado;
import cafe.model.JLabelComSombra;
import cafe.model.Produto;
import cafe.model.ProdutoRepository;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TelaPedido extends JFrame {
    private final PedidoController pedidoController;
    private final JLabel labelTotal;
    private final Map<Produto, JLabel> mapaLabelsQuantidade = new HashMap<>();
    private final Map<Produto, JButton> mapaBotoes = new HashMap<>();
    private Produto ultimoSelecionado;

    public TelaPedido() {
        this.pedidoController = new PedidoController();
        setTitle("Pedido");
        setSize(800, 820);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 800, 820);
        panel.setBackground(new Color(222, 184, 135));
        add(panel);

        JLabel titulo = new JLabel("ESCOLHA SEU CAFÉ");
        titulo.setFont(new Font("Arial", Font.BOLD, 35));
        titulo.setBounds(230, 20, 400, 35);
        titulo.setForeground(Color.white);
        panel.add(titulo);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(10, 100, 100, 30);
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        nomeLabel.setForeground(Color.white);
        panel.add(nomeLabel);

        JTextField campoNome = new JTextField();
        campoNome.setBounds(60, 100, 200, 25);
        campoNome.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(campoNome);
        

        List<Produto> produtos = ProdutoRepository.listarProdutos();

        int y = 170;
        int index = 1;
        for (Produto produto : produtos) {

            /*ImageIcon iconHome = new ImageIcon("C:/dev/JS/treinamento_java/cafeteria/imagens/xicara2.png");
            Image img = iconHome.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            JButton botao = new JButton(new ImageIcon(img));*/

            JButton botao = new BotaoArredondado(String.valueOf(index));
            botao.setBounds(40, y, 50, 30);
            panel.add(botao);

            JLabel labelProduto = new JLabelComSombra(produto.toString());
            labelProduto.setBounds(100, y, 200, 20);
            labelProduto.setFont(new Font("Arial", Font.PLAIN, 15));
            //labelProduto.setForeground(Color.white);
            panel.add(labelProduto);

            JLabel qtdLabel = new JLabelComSombra("0x");
            qtdLabel.setBounds(10, y, 30, 30);
            panel.add(qtdLabel);
            mapaLabelsQuantidade.put(produto, qtdLabel);

            mapaBotoes.put(produto, botao);

           botao.addActionListener(e -> {
                pedidoController.adicionarProduto(produto);
                atualizarQuantidade(produto);
                atualizarTotal();
                ultimoSelecionado = produto;
            });

            y += 40;
            index++;
        }

        ImageIcon iconHome = new ImageIcon("C:/dev/JS/treinamento_java/cafeteria/imagens/casa2.png");
        Image img = iconHome.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JButton botaoInicio = new JButton(new ImageIcon(img));
        botaoInicio.setBounds(730, -5, 60, 60);
        botaoInicio.setContentAreaFilled(false); // remove fundo
        botaoInicio.setBorderPainted(false);    // remove borda
        botaoInicio.setFocusPainted(false);
        botaoInicio.setToolTipText("Voltar ao início");
        panel.add(botaoInicio);
        
        
        JButton botaoAdd = new BotaoArredondado("+");
        botaoAdd.setBounds(240, 700, 50, 30);
        panel.add(botaoAdd);

        JButton botaoRemover = new BotaoArredondado("-");
        botaoRemover.setBounds(490, 700, 50, 30);
        panel.add(botaoRemover);

        JButton botaoPagar = new BotaoArredondado("PAGAR");
        botaoPagar.setBounds(330, 700, 130, 30);
        botaoPagar.setFont(new Font("Arial", Font.PLAIN, 15));
        botaoPagar.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(botaoPagar);

        JLabel labelValor = new JLabelComSombra("Valor:");
        labelValor.setFont(new Font("Arial", Font.BOLD, 16));
        labelValor.setBounds(340, 740, 100, 30);
        panel.add(labelValor);

        labelTotal = new JLabelComSombra("R$ 0,00");
        labelTotal.setBounds(390, 740, 200, 30);
        panel.add(labelTotal);

        JLabel imagem = criarImagem();
        panel.add(imagem);

        // ================================ funçoes =======================================

        botaoInicio.addActionListener(e -> {
            new TelaInicio();
            dispose();;
        });
        
        botaoAdd.addActionListener(e -> {
            if (ultimoSelecionado != null) {
                pedidoController.adicionarProduto(ultimoSelecionado);
                atualizarQuantidade(ultimoSelecionado);
                atualizarTotal();
            }
        });

        botaoRemover.addActionListener(e -> {
            if (ultimoSelecionado != null) {
                pedidoController.removerProduto(ultimoSelecionado);
                atualizarQuantidade(ultimoSelecionado);
                atualizarTotal();
            }
        });

        botaoPagar.addActionListener(e -> {
            String nomeCliente = campoNome.getText().trim();
            if (nomeCliente.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite o nome do cliente!");
                return;
            }
            pedidoController.setCliente(nomeCliente);
            new Recibo(pedidoController);
            

            
        });

        setVisible(true);
        SwingUtilities.invokeLater(() -> campoNome.requestFocusInWindow());
    }

    
        private void atualizarQuantidade(Produto produto) {
    int count = pedidoController.getQuantidade(produto);
    JLabel label = mapaLabelsQuantidade.get(produto);
    if (label != null) {
        label.setText(count + "x");
    }

    JButton botao = mapaBotoes.get(produto);
    if (botao != null) {
        if (count > 0) {
            botao.setBackground(Color.DARK_GRAY); 
            botao.setForeground(Color.WHITE);
            //botao.setOpaque(true);
            //botao.setBorderPainted(false);
        } else {
            botao.setBackground(Color.WHITE); 
            botao.setForeground(Color.BLACK);
            //botao.setOpaque(false);
            //botao.setBorderPainted(false);
        }
    }
}




    private void atualizarTotal() {
        float total = pedidoController.calcularTotal();
        labelTotal.setText(String.format("R$ %.2f", total).replace('.', ','));
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

