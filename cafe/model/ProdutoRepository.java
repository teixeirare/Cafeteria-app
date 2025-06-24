package cafe.model;

import java.util.Arrays;
import java.util.List;

public class ProdutoRepository {
    public static List<Produto> listarProdutos() {
        return Arrays.asList(
            new Produto("Cappuccino", 6f),
            new Produto("Latte", 5.50f),
            new Produto("Espresso coffee", 3.50f),
            new Produto("Hot chocolate", 10f),
            new Produto("Pão de queijo", 2f),
            new Produto("Cakes", 15f),
            new Produto("Moka", 10f),
            new Produto("Snacks", 6.50f),
            new Produto("Bottle of water", 1f),
            new Produto("Soda", 2f)
            
        );
    }
}

