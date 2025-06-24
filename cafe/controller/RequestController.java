package cafe.controller;

import cafe.model.Product;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RequestController {

    private final List<Product> cart = new ArrayList<>();
    private String client;

    
    public void adicionarProduto(Product product) {
        cart.add(product);
    }

    public void removeProduct(Product product) {
        cart.remove(product); // remove a primeira ocorrência
    }

    public List<Product> getCart() {
        return new ArrayList<>(cart); // retorno defensivo
    }

    public float calculateTotal() {
        float total = 0f;
        for (Product p : cart) {
            total += p.getPrice();
        }
        return total;
    }

    public void cleanCart() {
        cart.clear();
    }

    public void setClient(String name) {
        this.client = name;
    }

    public String getClient() {
        return client;
    }

    public int getQuantity(Product product) {
    int count = 0;
    for (Product p : cart) {
        if (p.equals(product)) {
            count++;
        }
    }
    return count;
}

    public float getTotal() {
    float total = 0f;
    for (Product p : cart) {
        total += p.getPrice();
    }
    return total;
}


    public String generateReceiptHTML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body style='font-family: sans-serif;'>");
        sb.append("<h2 style='text-align:center; font-size: 22px;'>Receipt</h2>");
        sb.append("<p><strong>Customer:</strong> ").append(client).append("</p>");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dateTimeFormatted = now.format(formatter);

        sb.append("<p><strong>Date:</strong> ").append(dateTimeFormatted).append("</p><br>");


        Map<Product, Integer> accountant = new LinkedHashMap<>();
        for (Product p : cart) {
            accountant.put(p, accountant.getOrDefault(p, 0) + 1);
        }

        sb.append("<table style='width:100%; border-spacing: 0 8px;'>");
        for (Map.Entry<Product, Integer> entry : accountant.entrySet()) {
            Product product = entry.getKey();
            int qtd = entry.getValue();
            float subtotal = qtd * product.getPrice();

            sb.append("<tr>")
              .append("<td style='padding: 2px 5px;'>")
              .append(qtd).append("x")
              .append("&nbsp;&nbsp;")
              .append(product.getNome())
              .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
              .append("</td>")
              .append("<td style='text-align:left; font-size:10px;'>")
              .append("<td style='text-align:right; font-size:10px;'>$ ")
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
              .append("<td style='text-align:right; font-size:15px;'>$ ")
              .append(String.format("%.2f", calculateTotal())).append("</td>")
              .append("</tr>");
            sb.append("</table>");

        return sb.toString();
    }
}

