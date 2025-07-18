package coffee.controller;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import coffee.model.Product;
import coffee.receipt.ReceiptGenerator;
import coffee.receipt.ReceiptPrinter;
import coffee.receipt.ReceiptSaver;

public class RequestController {

    private final List<Product> cart = new ArrayList<>();
    private String client;
    private String orderNumber;
    private static String lastOrderDate = "";
    private static int orderSequence = 0;

    private String paymentMethod;
    private Float valueReceived;
    private Float change;

    // =====================Add extra fields if you need to =====================

    // private String phone;
    // private String observation;

    // ===================== Standard trolley methods =====================

    public void addProduct(Product product) {
        cart.add(product);
    }

    public void removeProduct(Product product) {
        cart.remove(product);
    }

    public List<Product> getCart() {
        return new ArrayList<>(cart);
    }

    public void cleanCart() {
        cart.clear();
    }

    public float calculateTotal() {
        float total = 0f;
        for (Product p : cart) {
            total += p.getPrice();
        }
        return total;
    }

    public int getQuantity(Product product) {
        int count = 0;
        for (Product p : cart) {
            if (p.equals(product)) count++;
        }
        return count;
    }

    // ===================== Getters e Setters =====================
    
     public void setClient(String client) {
    if (client != null && !client.isEmpty()) {
        String[] parts = client.trim().toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                sb.append(part.substring(0, 1).toUpperCase())
                  .append(part.substring(1))
                  .append(" ");
            }
        }
        this.client = sb.toString().trim();
    } else {
        this.client = client;
    }
}
    public String getClient() {
        return client;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setValueReceived(Float valueReceived) {
        this.valueReceived = valueReceived;
    }
    public Float getValueReceived() {
        return valueReceived;
    }

    public void setChange(Float change) {
        this.change = change;
    }
    public Float getChange() {
        return change;
    }

    // ===================== Order sequence number generation =====================
    
    public void generateAndSetOrderNumber() {
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        if (!today.equals(lastOrderDate)) {
            lastOrderDate = today;
            orderSequence = 1;
        } else {
            orderSequence++;
        }
        String num = String.format("%03d", orderSequence);
        setOrderNumber(num);
    }

    // ===================== Receipt methods =====================

    public void saveReceiptToHtmlFile() {
        String html = ReceiptGenerator.generateHTML(cart, client, orderNumber, paymentMethod, valueReceived, change, calculateTotal());
        String timestamp = ZonedDateTime.now(ZoneId.of("America/Toronto")).format(DateTimeFormatter.ofPattern("yyyyMMdd_hhmm_a"));
        String fileName = orderNumber + "_" + (client == null ? "customer" : client) + "__receipt__" + timestamp + ".html";
        ReceiptSaver.saveToFile(html, fileName);
    }

    public void saveReceiptPlainTextToFile() {
        String text = ReceiptGenerator.generateText(cart, client, orderNumber, paymentMethod, valueReceived, change, calculateTotal());
        String timestamp = ZonedDateTime.now(ZoneId.of("America/Toronto")).format(DateTimeFormatter.ofPattern("yyyyMMdd_hhmm_a"));
        String fileName = orderNumber + "_" + (client == null ? "customer" : client) + "__receipt__" + timestamp + ".txt";
        ReceiptSaver.saveToFile(text, fileName);
    }

    public void printReceiptComplete() {
        String text = ReceiptGenerator.generateText(cart, client, orderNumber, paymentMethod, valueReceived, change, calculateTotal());
        ReceiptPrinter.print(text, "Coffee Receipt (Customer)");
    }

    // ===================== Preparation list  =====================

    public String generatePreparationList() {
        Map<Product, Integer> accountant = new LinkedHashMap<>();
        for (Product p : cart) accountant.put(p, accountant.getOrDefault(p, 0) + 1);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd  hh:mm a"));

        StringBuilder sb = new StringBuilder();
        sb.append("ORDER\n\n");
        if (orderNumber != null) {
            sb.append("Order No: ").append(orderNumber).append("\n");
        }
        sb.append(timestamp).append("\n");
        sb.append("-----------------------------\n");
        for (Map.Entry<Product, Integer> entry : accountant.entrySet()) {
            sb.append(entry.getValue()).append("x ").append(entry.getKey().getNome()).append("\n");
        }
        sb.append("-----------------------------\n");
        return sb.toString();
    }

    public void printPreparationList() {
        String prepList = generatePreparationList();
        ReceiptPrinter.print(prepList, "Coffee Preparation List");
    }

    // ===================== Add any other methods you wish =====================
    // Example: methods for saving/restoring orders, listing histories, etc.

}









