package OrderClasses;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OrderItems {
    private final Map<Product, Integer> productQuantities = new HashMap<>();

    public void addItem(Product product, int count) {
        Objects.requireNonNull(product, "product must not be null");
        if (count <= 0) {
            throw new IllegalArgumentException("Количество должно быть > 0");
        }
        productQuantities.merge(product, count, Integer::sum);
    }

    public void removeItem(Product product, int count) {
        Objects.requireNonNull(product, "product must not be null");
        if (count <= 0) {
            throw new IllegalArgumentException("Количество должно быть > 0");
        }
        productQuantities.computeIfPresent(product, (k, current) -> {
            int updated = current - count;
            return updated > 0 ? updated : null;
        });
    }

    public int getQuantity(Product product) {
        return productQuantities.getOrDefault(product, 0);
    }

    public int calculatePrice() {
        return productQuantities.entrySet().stream()
                .mapToInt(e -> e.getKey().getPrice() * e.getValue())
                .sum();
    }

    public Map<Product, Integer> getItems() {
        return Collections.unmodifiableMap(productQuantities);
    }
}