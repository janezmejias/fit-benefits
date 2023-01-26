package coe.fit.benefits.exceptions;

import coe.fit.benefits.payment.factory.processors.commons.ProcessorType;
import coe.fit.benefits.product.helper.ProductHelper;
import coe.fit.benefits.product.model.Product;
import coe.fit.benefits.util.ProductGenerator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static coe.fit.benefits.util.PayOrderSimulator.payOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NoProductFoundTest {

    @Test
    public void orderWithoutProductsTest() {
        assertThrows(NoProductFound.class, () -> {
            Map<Product, Integer> products = ProductGenerator.generateProducts(0);

            BigDecimal priceOfProducts = ProductHelper.getTotalPriceOfProducts(products);
            BigDecimal expected = priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(0.15))).setScale(2, RoundingMode.HALF_EVEN);

            assertEquals(expected, payOrder(products, ProcessorType.VISA));
        });
    }

}