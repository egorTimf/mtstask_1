import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class OrderProducts {
    private Map<Product, Integer> productQuantities = new HashMap<>();

    public void AddItem(Product product, Integer count) throws Exception {
        if(count <= 0) throw new Exception("Negative count!");

    }

    public void RemoveItem(Product product, Integer count) throws Exception {
        if(count <= 0) throw new Exception("Negative count!");
        productQuantities.merge(product, -count, Integer::sum);
        if(productQuantities.get(product) <= 0) {
            productQuantities.remove(product);
        }
    }

    public Integer GetQuantity(Product product) throws Exception {
        if (!productQuantities.containsKey(product)) throw new Exception("No product in order");
        return productQuantities.get(product);
    }

    public Integer CalculatePrice() {
        return productQuantities.entrySet().stream()
                .mapToInt(e -> e.getKey().GetPrice() * e.getValue())
                .sum();
    }
}
