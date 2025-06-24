package cafe.controller;

import cafe.model.Product;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String client;
    private final List<Product> items = new ArrayList<>();

    public void setClient(String client) {
        this.client = client;
    }

    public String getClient() {
        return client;
    }

    public void adicionarItem(Product product) {
        items.add(product);
    }

    public List<Product> getItems() {
        return new ArrayList<>(items);
    }

    public float calculateTotal() {
        return (float) items.stream().mapToDouble(Product::getPrice).sum();
    }

    public String generateReceiptHTML() {
        StringBuilder sb = new StringBuilder("<html><body>");
        sb.append("<h2 style='text-align:center; margin-top: -10;'>Receipt</h2>");
         sb.append("<p><strong>Client:</strong> ").append(client).append("</p>");
        sb.append("<ul>");
        for (Product product : items) {
            sb.append("<li>").append(product).append("</li>");
        }
        sb.append("</table>");
            sb.append("<hr>");
        sb.append("<p style='font-size: 14px;'><strong>Total:</strong> $ ")
          .append(String.format("%.2f", calculateTotal()))
          .append("</p>");
        sb.append("</body></html>");
        return sb.toString();


    }
}


