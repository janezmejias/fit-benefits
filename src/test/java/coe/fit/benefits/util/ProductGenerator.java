/*
 *  ProductGenerator
 *  1.0
 *  11/8/22, 8:30 PM
 *  Copyright (c) 2022 juan.anez
 *  Any illegal reproduction of this content will result in immediate legal action.
 */

package coe.fit.benefits.util;

import coe.fit.benefits.product.model.Product;
import coe.fit.benefits.product.model.ProductType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * The type Product generator.
 */
public final class ProductGenerator {

    /**
     * Hide constructor to avoid instances of this utility class.
     */
    private ProductGenerator() {
    }

    /**
     * Generate products map.
     *
     * @param expectedSize the expected size of the map which matches
     *                     the number of different products in the cart.
     * @return the map
     */
    public static Map<Product, Integer> generateProducts(final Integer expectedSize) {
        HashMap<Product, Integer> products = new HashMap<>();
        int quantity = new Random().nextInt(5) + 1;
        IntStream.rangeClosed(1, expectedSize).forEach(id -> products.put(Product.builder()
                .name("product-" + quantity)
                .price(BigDecimal.valueOf(new Random().nextDouble() * 10.00))
                .type(ProductType.BASIC)
                .build(), 1));
        return products;
    }

    /**
     * Generate products map.
     *
     * @param expectedTotal the expected total amount to pay for the order before discount
     * @return the map
     */
    public static Map<Product, Integer> generateProducts(final Double expectedTotal) {
        HashMap<Product, Integer> products = new HashMap<>();
        double price = (expectedTotal / 10);
        IntStream.rangeClosed(1, 10).forEach(value -> products.put(Product.builder()
                .name("product-" + value)
                .price(BigDecimal.valueOf(price))
                .type(ProductType.BASIC)
                .build(), 1));
        return products;
    }
}
