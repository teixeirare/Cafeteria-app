package coffee.controller;

import java.util.ArrayList;
import java.util.List;

import coffee.model.Product;

public class Order {
    private String client;
    private final List<Product> items = new ArrayList<>();

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

    public void addItem(Product product) {
        items.add(product);
    }

    public List<Product> getItems() {
        return new ArrayList<>(items);
    }

    public float calculateTotal() {
        return (float) items.stream().mapToDouble(Product::getPrice).sum();
    }

}


