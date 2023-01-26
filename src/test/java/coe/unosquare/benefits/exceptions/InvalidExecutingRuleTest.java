package coe.unosquare.benefits.exceptions;

import coe.fit.benefits.exceptions.InvalidExecutingRule;
import coe.fit.benefits.payment.rules.KDiscount;
import coe.fit.benefits.payment.rules.Rule;
import coe.fit.benefits.product.helper.ProductHelper;
import coe.fit.benefits.product.model.Product;
import coe.unosquare.benefits.util.ProductGenerator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InvalidExecutingRuleTest {
    @Test
    public void orderWithoutProductsTest() {
        assertThrows(InvalidExecutingRule.class, () -> {
            Map<Product, Integer> products = ProductGenerator.generateProducts(1);

            Integer totalDiffProducts = products.values().stream().reduce(0, Integer::sum);
            BigDecimal priceOfProducts = ProductHelper.getTotalPriceOfProducts(products);

            List<Rule<KDiscount, BigDecimal>> list = List.of(
                    new Rule<>(KDiscount.RULE_1, () -> (totalDiffProducts >= 10), () -> priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(.15)))),
                    new Rule<>(KDiscount.RULE_2, () -> (totalDiffProducts >= 7 && totalDiffProducts <= 9), () -> priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(.10)))),
                    new Rule<>(KDiscount.RULE_3, () -> (totalDiffProducts <= 6), () -> priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(.05))))
            );
            Map<KDiscount, Rule<KDiscount, BigDecimal>> rules = list.stream().collect(Collectors.toMap(k -> k.type, v -> v));

            BigDecimal bigDecimal = Stream.of(KDiscount.values())
                    .filter(discountType -> rules.get(KDiscount.RULE_1).condition.get())
                    .map(discountType -> rules.get(discountType).operation.get())
                    .findFirst()
                    .orElseThrow(InvalidExecutingRule::new);

            BigDecimal expected = priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(0.15))).setScale(2, RoundingMode.HALF_EVEN);

            assertEquals(expected, bigDecimal);
        });
    }
}