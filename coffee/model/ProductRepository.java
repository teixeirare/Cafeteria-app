package coffee.model;

import java.util.Arrays;
import java.util.List;

public class ProductRepository {
    public static List<Product> listProducts() {
        return Arrays.asList(
            new Product("Cappuccino", 6f),
            new Product("Latte M", 5.50f),
            new Product("Latte ", 10f),
            new Product("Espresso coffee", 3.50f),
            new Product("Double", 10f),
            new Product("Americano", 6f),
            new Product("Macchiato", 9f),
            new Product("Moka", 12f),
            new Product("Hot chocolate", 10f),
            new Product("Hot Tea", 5f),
            new Product("Iced Tea", 5f),
            new Product("Cheese bread", 2f),
            new Product("Italian soda", 10f),
            new Product("Cake", 12f),
            new Product("Snacks", 10f),
            new Product("Drinks", 5f)
            
            
            
        );
    }
}

