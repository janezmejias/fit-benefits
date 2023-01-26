package coe.fit.benefits.order;

import coe.fit.benefits.payment.factory.processors.commons.ProcessorType;
import coe.fit.benefits.product.helper.ProductHelper;
import coe.fit.benefits.product.model.Product;
import coe.fit.benefits.product.model.ProductType;
import coe.fit.benefits.util.ProductGenerator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderHandlerTest {

    @Test
    void pay() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(10);

        BigDecimal priceOfProducts = ProductHelper.getTotalPriceOfProducts(products);
        BigDecimal expected = priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(0.15))).setScale(2, RoundingMode.HALF_EVEN);

        Order order = new OrderHandler();
        assertEquals(expected, order.pay(OrderContext.builder()
                .products(products)
                .processorType(ProcessorType.VISA)
                .build()));
    }

    @Test
    void print() {
        Product product = Product.builder()
                .name("product-1")
                .price(new BigDecimal("3.33"))
                .type(ProductType.WORK_TOOL)
                .build();

        String messageExpected = String.format(
                "Product:{%s,%s,%s},Quantity:%s,Total:%s", product.getName(), product.getPrice(), product.getType(), 1, "3.33");

        Order order = new OrderHandler();
        assertEquals(messageExpected, order.print(OrderContext.builder()
                .products(Map.of(product, 1))
                .build()));
    }
}