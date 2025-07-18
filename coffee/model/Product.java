package coffee.model;

public class Product {
    private final String nome;
    private final float preco;

    public Product(String nome, float preco) {
        this.nome = nome;
        this.preco = preco;
        
    }

    public String getNome() {
        return nome;
    }

    public float getPrice() {
        return preco;
    }

    @Override
    public String toString() {
        return nome + "- $ " + String.format("%.2f", preco);
    }
}
