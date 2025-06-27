package coffee.model;

import java.util.Arrays;
import java.util.List;

public class ProductRepository {
    public static List<Product> listProducts() {
        return Arrays.asList(
            new Product("Cappuccino", 6f),
            new Product("Latte", 5.50f),
            new Product("Espresso coffee", 3.50f),
            new Product("Hot chocolate", 10f),
            new Product("Moka", 10f),
            new Product("Snacks", 6.50f),
            new Product("Bottle of water", 1f)
            
        );
    }
}

