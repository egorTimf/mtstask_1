package OrderClasses;

import java.util.HashMap;
import java.util.Map;

public class OrderProducts {
    private final Map<Product, Integer> productQuantities;

    public OrderProducts() {
        productQuantities = new HashMap<>();
    }

    public void addItem(Product product, Integer count) throws Exception {
        if(count <= 0) throw new Exception("Negative count!");
        productQuantities.merge(product, count, Integer::sum);
    }

    public void removeItem(Product product, Integer count) throws Exception {
        if(count <= 0) throw new Exception("Negative count!");
        productQuantities.merge(product, -count, Integer::sum);
        if(productQuantities.get(product) <= 0) {
            productQuantities.remove(product);
        }
    }

    public Integer getQuantity(Product product) throws Exception {
        if (!productQuantities.containsKey(product)) throw new Exception("No product in order");
        return productQuantities.get(product);
    }

    public Integer calculatePrice() {
        return productQuantities.entrySet().stream()
                .mapToInt(e -> e.getKey().getPrice() * e.getValue())
                .sum();
    }
}
