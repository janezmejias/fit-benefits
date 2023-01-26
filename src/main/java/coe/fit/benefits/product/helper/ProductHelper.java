package coe.fit.benefits.product.helper;

import coe.fit.benefits.exceptions.NoProductFound;
import coe.fit.benefits.product.model.Product;

import java.math.BigDecimal;
import java.util.Map;

public class ProductHelper {

    public static BigDecimal getTotalPriceOfProducts(Map<Product, Integer> products) {
        if (products.isEmpty()) {
            throw new NoProductFound();
        }
        return BigDecimal.valueOf(products.entrySet()
                .stream()
                .mapToDouble(product -> map(product.getKey().getPrice(), product.getValue()))
                .sum());
    }

    private static Double map(BigDecimal price, Integer total) {
        return price.multiply(BigDecimal.valueOf(total)).doubleValue();
    }
}
