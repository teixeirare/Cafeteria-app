package coffee.controller;

import java.util.ArrayList;
import java.util.List;

import coffee.model.Product;

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

}


