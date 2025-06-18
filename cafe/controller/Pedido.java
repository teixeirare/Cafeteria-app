package cafe.controller;

import cafe.model.Produto;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String cliente;
    private final List<Produto> itens = new ArrayList<>();

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void adicionarItem(Produto produto) {
        itens.add(produto);
    }

    public List<Produto> getItens() {
        return new ArrayList<>(itens);
    }

    public float calcularTotal() {
        return (float) itens.stream().mapToDouble(Produto::getPreco).sum();
    }

    public String gerarReciboHTML() {
        StringBuilder sb = new StringBuilder("<html><body>");
        sb.append("<h2 style='text-align:center; margin-top: -10;'>Recibo</h2>");
         sb.append("<p><strong>Cliente:</strong> ").append(cliente).append("</p>");
        sb.append("<ul>");
        for (Produto produto : itens) {
            sb.append("<li>").append(produto).append("</li>");
        }
        sb.append("</table>");
            sb.append("<hr>");
        sb.append("<p style='font-size: 14px;'><strong>Total:</strong> R$ ")
          .append(String.format("%.2f", calcularTotal()))
          .append("</p>");
        sb.append("</body></html>");
        return sb.toString();


    }
}


