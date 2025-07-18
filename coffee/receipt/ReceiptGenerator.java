package coffee.receipt;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import coffee.model.Product;

public class ReceiptGenerator {

    public static String generateHTML(List<Product> cart, String client, String orderNumber, 
    String paymentMethod, Float valueReceived, Float change, float total) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head>")
          .append("<style>")
          .append("body{font-family:Arial,Helvetica,sans-serif;max-width:400px;width:400px;margin:auto;padding:15px;}")
          .append("h2{text-align:center;margin-bottom:10px;font-size:15px;}")
          .append(".info{margin-bottom:6px;font-size:11px;}")
          .append("table{width:100%;border-collapse:collapse;margin-top:6px;}")
          .append("th{font-weight:700;padding:2px 0;font-size:11px;}")
          .append("td{font-size:11px;padding:2px 0;}")
          .append("tr{border-bottom:1px dotted #aaa;}")
          .append(".total{font-size:12px;font-weight:bold;}")
          .append(".right{text-align:right;}")
          .append("</style></head><body>");
        sb.append("<h2>RECEIPT</h2>");
        sb.append("<div class='info'>");
        if (orderNumber != null) sb.append("<b>Order No:</b> ").append(orderNumber).append("<br>");
        sb.append("<b>Customer:</b> ").append(client == null ? "N/A" : client).append("<br>");
        sb.append("<b>Date:</b> ")
          .append(ZonedDateTime.now(ZoneId.of("America/Toronto"))
          .format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm a")))
          .append("</div>");

        // ======================================= Trolley items =======================================

        Map<Product, Integer> accountant = new LinkedHashMap<>();
        for (Product p : cart) accountant.put(p, accountant.getOrDefault(p, 0) + 1);

        sb.append("<table>");
        sb.append("<tr>")
          .append("<th style='width:12%;text-align:left;'>Qty</th>")
          .append("<th style='width:62%;text-align:left;'>Item</th>")
          .append("<th style='width:26%;text-align:right;'>Subtotal</th>")
          .append("</tr>");
        for (Map.Entry<Product, Integer> entry : accountant.entrySet()) {
            Product product = entry.getKey();
            int qtd = entry.getValue();
            float subtotal = qtd * product.getPrice();
            sb.append("<tr>")
              .append("<td style='text-align:left;padding-left:0;'>").append(qtd).append("</td>")
              .append("<td style='text-align:left;padding-left:0;'>").append(product.getNome()).append("</td>")
              .append("<td style='text-align:right;padding-right:8px;'>").append(String.format("$ %.2f", subtotal))
              .append("</td>")
              .append("</tr>");
        }
        sb.append("</table>");

        // ============================================ Payment information ===========================================

         if (paymentMethod != null) {
            sb.append("<div class='info'><b>Payment:</b> ")
              .append(paymentMethod.equalsIgnoreCase("CASH") ? "Cash" :
                      paymentMethod.equalsIgnoreCase("CREDIT") ? "Credit" :
                      paymentMethod.equalsIgnoreCase("DEBIT") ? "Debit" :
                      paymentMethod)
              .append("</div>");
        }
        if (paymentMethod != null && paymentMethod.equalsIgnoreCase("CASH")) {
            if (valueReceived != null)
                sb.append("<div class='info'><b>Received:</b> $ ").append(String.format("%.2f", valueReceived))
                .append("</div>");
            if (change != null)
                sb.append("<div class='info'><b>Change:</b> $ ").append(String.format("%.2f", change))
                .append("</div>");
        }

        sb.append("<hr style='margin:4px 0 2px 0;'>");
        sb.append("<table style='width:100%; margin-top:0;'>")
          .append("<tr style='border-bottom:none;'>")
          .append("<td class='total' style='text-align:left;'>Total:</td>")
          .append("<td class='total right' style='text-align:right;padding-right:8px;'>")
          .append(String.format("$ %.2f", total))
          .append("</td>")
          .append("</tr>")
          .append("</table>");
        sb.append("</body></html>");
        return sb.toString();
    }

    public static String generateText(List<Product> cart, String client, String orderNumber, 
    String paymentMethod, Float valueReceived, Float change, float total) {
        StringBuilder sb = new StringBuilder();
        int width = 40;
        sb.append(centerText("RECEIPT", width)).append("\n\n");
        if (orderNumber != null) sb.append("Order No: ").append(orderNumber).append("\n");
        sb.append("Customer: ").append(client == null ? "N/A" : client).append("\n");
        sb.append("Date: ").append(ZonedDateTime.now(ZoneId.of("America/Toronto"))
                            .format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm a")))
          .append("\n\n");
        sb.append(String.format("%-4s %-20s %8s\n", "Qty", "Item", "Subtotal"));
        Map<Product, Integer> accountant = new LinkedHashMap<>();
        for (Product p : cart) accountant.put(p, accountant.getOrDefault(p, 0) + 1);
        for (Map.Entry<Product, Integer> entry : accountant.entrySet()) {
            Product product = entry.getKey();
            int qtd = entry.getValue();
            float subtotal = qtd * product.getPrice();
            sb.append(String.format("%-4d %-20s %8s\n", qtd, product.getNome(), String.format("$ %.2f", subtotal)));
        }
        if (paymentMethod != null) sb.append("\nPayment: ").append(paymentMethod).append("\n");
        if (paymentMethod != null && paymentMethod.equalsIgnoreCase("CASH")) {
            if (valueReceived != null)
                sb.append("Received: $ ").append(String.format("%.2f", valueReceived)).append("\n");
            if (change != null)
                sb.append("Change:   $ ").append(String.format("%.2f", change)).append("\n");
        }
        sb.append("----------------------------------------\n");
        sb.append(String.format("%-25s %12s\n", "Total:", String.format("$ %.2f", total)));
        return sb.toString();
    }

    // ================================ Utility to center text on receipt text ================================
    
    private static String centerText(String text, int width) {
        if (text.length() >= width) return text;
        int leftPadding = (width - text.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < leftPadding; i++) sb.append(" ");
        sb.append(text);
        return sb.toString();
    }
}

