package OrderClasses;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProductCatalog {
    private final Map<String, Product> productsByName = new LinkedHashMap<>();

    public void addProduct(Product product) {
        Objects.requireNonNull(product, "product must not be null");
        productsByName.put(product.getName(), product);
    }

    public Product findByName(String name) {
        return productsByName.get(name);
    }

    public List<Product> getAll() {
        return List.copyOf(productsByName.values());
    }

    public boolean contains(String name) {
        return productsByName.containsKey(name);
    }
}