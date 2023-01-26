package coe.fit.benefits.exceptions;

import coe.fit.benefits.order.Order;
import coe.fit.benefits.order.OrderContext;
import coe.fit.benefits.order.OrderHandler;
import coe.fit.benefits.product.helper.ProductHelper;
import coe.fit.benefits.product.model.Product;
import coe.fit.benefits.util.ProductGenerator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InvalidProcessorTest {

    @Test
    public void orderWithInvalidProcessorTest() {
        assertThrows(InvalidProcessor.class, () -> {
            Map<Product, Integer> products = ProductGenerator.generateProducts(10);

            BigDecimal priceOfProducts = ProductHelper.getTotalPriceOfProducts(products);
            BigDecimal expected = priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(0.15))).setScale(2, RoundingMode.HALF_EVEN);

            Order order = new OrderHandler();

            assertEquals(expected, order.pay(OrderContext.builder()
                    .products(products)
                    .build()));
        });
    }

}