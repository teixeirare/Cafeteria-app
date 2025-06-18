package cafe.model;

import java.util.Arrays;
import java.util.List;

public class ProdutoRepository {
    public static List<Produto> listarProdutos() {
        return Arrays.asList(
            new Produto("Cappuccino", 10.90f),
            new Produto("Caffe Latte", 9.90f),
            new Produto("Expresso puro", 7.90f),
            new Produto("Chocolate quente", 15.90f),
            new Produto("Pão de queijo", 7.00f),
            new Produto("Bolo", 21.90f),
            new Produto("Moka", 15.90f),
            new Produto("Salgado", 10.90f),
            new Produto("Água", 5.50f),
            new Produto("Refrigerante LT", 6.50f)
            
        );
    }
}

