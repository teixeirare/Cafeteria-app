package cafe.controller;

import cafe.model.Produto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PedidoController {

    private final List<Produto> carrinho = new ArrayList<>();
    private String cliente;

    
    public void adicionarProduto(Produto produto) {
        carrinho.add(produto);
    }

    public void removerProduto(Produto produto) {
        carrinho.remove(produto); // remove a primeira ocorrência
    }

    public List<Produto> getCarrinho() {
        return new ArrayList<>(carrinho); // retorno defensivo
    }

    public float calcularTotal() {
        float total = 0f;
        for (Produto p : carrinho) {
            total += p.getPreco();
        }
        return total;
    }

    public void limparCarrinho() {
        carrinho.clear();
    }

    public void setCliente(String nome) {
        this.cliente = nome;
    }

    public String getCliente() {
        return cliente;
    }

    public int getQuantidade(Produto produto) {
    int count = 0;
    for (Produto p : carrinho) {
        if (p.equals(produto)) {
            count++;
        }
    }
    return count;
}

    public float getTotal() {
    float total = 0f;
    for (Produto p : carrinho) {
        total += p.getPreco();
    }
    return total;
}


    public String gerarReciboHTML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body style='font-family: sans-serif;'>");
        sb.append("<h2 style='text-align:center; font-size: 22px;'>Recibo</h2>");
        sb.append("<p><strong>Cliente:</strong> ").append(cliente).append("</p>");

        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dataHoraFormatada = agora.format(formatter);

        sb.append("<p><strong>Data:</strong> ").append(dataHoraFormatada).append("</p><br>");


        Map<Produto, Integer> contador = new LinkedHashMap<>();
        for (Produto p : carrinho) {
            contador.put(p, contador.getOrDefault(p, 0) + 1);
        }

        sb.append("<table style='width:100%; border-spacing: 0 8px;'>");
        for (Map.Entry<Produto, Integer> entry : contador.entrySet()) {
            Produto produto = entry.getKey();
            int qtd = entry.getValue();
            float subtotal = qtd * produto.getPreco();

            sb.append("<tr>")
              .append("<td style='padding: 2px 5px;'>")
              .append(qtd).append("x")
              .append("&nbsp;&nbsp;")
              .append(produto.getNome())
              .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
              .append("</td>")
              .append("<td style='text-align:left; font-size:10px;'>")
              .append("<td style='text-align:right; font-size:10px;'>R$ ")
              .append(String.format("%.2f", subtotal))
              //.append("</td>")
              .append("</tr>");
              
        }
            sb.append("</table>");
            sb.append("<p>&nbsp;</p>");
            sb.append("<hr>");
            sb.append("<table style='width:100%;'>");
            sb.append("<tr>")
              .append("<td style='text-align:left; font-size:15px;'><strong>Total:</strong></td>")
              .append("<td style='text-align:right; font-size:15px;'>R$ ")
              .append(String.format("%.2f", calcularTotal())).append("</td>")
              .append("</tr>");
            sb.append("</table>");

        return sb.toString();
    }
}

