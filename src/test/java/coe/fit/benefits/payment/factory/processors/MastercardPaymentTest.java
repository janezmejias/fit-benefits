package coe.fit.benefits.payment.factory.processors;

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

class MastercardPaymentTest {

    @Test
    void orderWithMastercardMoreThan100ProductsTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(101.0);

        BigDecimal priceOfProducts = ProductHelper.getTotalPriceOfProducts(products);
        BigDecimal expected = priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(0.17))).setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expected, payOrder(products, ProcessorType.MASTERCARD));
    }

    @Test
    void orderWithMastercard100ProductsTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(100.00);

        BigDecimal priceOfProducts = ProductHelper.getTotalPriceOfProducts(products);
        BigDecimal expected = priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(0.17))).setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expected, payOrder(products, ProcessorType.MASTERCARD));
    }

    @Test
    void orderWithMastercard75ProductsTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(75.0);

        BigDecimal priceOfProducts = ProductHelper.getTotalPriceOfProducts(products);
        BigDecimal expected = priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(0.12))).setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expected, payOrder(products, ProcessorType.MASTERCARD));
    }

    @Test
    void orderWithMastercardMoreThan75ProductsTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(75.01);

        BigDecimal priceOfProducts = ProductHelper.getTotalPriceOfProducts(products);
        BigDecimal expected = priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(0.12))).setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expected, payOrder(products, ProcessorType.MASTERCARD));
    }

    @Test
    void orderWithMastercard99ProductsTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(99.0);

        BigDecimal priceOfProducts = ProductHelper.getTotalPriceOfProducts(products);
        BigDecimal expected = priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(0.12))).setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expected, payOrder(products, ProcessorType.MASTERCARD));
    }

    @Test
    void orderWithMastercardLessThan75ProductsTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(74.99);

        BigDecimal priceOfProducts = ProductHelper.getTotalPriceOfProducts(products);
        BigDecimal expected = priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(0.08))).setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expected, payOrder(products, ProcessorType.MASTERCARD));
    }

}