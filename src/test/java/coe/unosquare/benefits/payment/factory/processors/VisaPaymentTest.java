package coe.unosquare.benefits.payment.factory.processors;

import coe.fit.benefits.payment.factory.processors.commons.ProcessorType;
import coe.fit.benefits.product.helper.ProductHelper;
import coe.fit.benefits.product.model.Product;
import coe.unosquare.benefits.util.ProductGenerator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static coe.unosquare.benefits.util.PayOrderSimulator.payOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VisaPaymentTest {

    @Test
    void orderWithVisa10ProductsTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(10);

        BigDecimal priceOfProducts = ProductHelper.getTotalPriceOfProducts(products);
        BigDecimal expected = priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(0.15))).setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expected, payOrder(products, ProcessorType.VISA));
    }

    @Test
    void orderWithVisaMoreThan10ProductsTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(11);

        BigDecimal priceOfProducts = ProductHelper.getTotalPriceOfProducts(products);
        BigDecimal expected = priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(0.15))).setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expected, payOrder(products, ProcessorType.VISA));
    }

    @Test
    void orderWithVisa7ProductsTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(7);

        BigDecimal priceOfProducts = ProductHelper.getTotalPriceOfProducts(products);
        BigDecimal expected = priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(0.10))).setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expected, payOrder(products, ProcessorType.VISA));
    }

    @Test
    void orderWithVisa8ProductsTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(8);

        BigDecimal priceOfProducts = ProductHelper.getTotalPriceOfProducts(products);
        BigDecimal expected = priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(0.10))).setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expected, payOrder(products, ProcessorType.VISA));
    }

    @Test
    void orderWithVisa9ProductsTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(9);

        BigDecimal priceOfProducts = ProductHelper.getTotalPriceOfProducts(products);
        BigDecimal expected = priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(0.10))).setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expected, payOrder(products, ProcessorType.VISA));
    }

    @Test
    void orderWithVisaLessThan7ProductsTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(6);

        BigDecimal priceOfProducts = ProductHelper.getTotalPriceOfProducts(products);
        BigDecimal expected = priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(0.05))).setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expected, payOrder(products, ProcessorType.VISA));
    }

}