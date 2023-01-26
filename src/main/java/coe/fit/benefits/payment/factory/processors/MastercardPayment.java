package coe.fit.benefits.payment.factory.processors;

import coe.fit.benefits.payment.rules.KDiscount;
import coe.fit.benefits.payment.rules.Rule;
import coe.fit.benefits.product.helper.ProductHelper;
import coe.fit.benefits.order.OrderContext;
import coe.fit.benefits.payment.factory.processors.commons.AbstractProcessor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of payment with MASTERCARD processor
 */
public class MastercardPayment extends AbstractProcessor {

    /**
     * Main method in charge of executing the payment based on rules and discounts
     *
     * @param orderContext relevant information to do the payment
     * @return result of the amount to be paid with the discounts applied
     */
    @Override
    public BigDecimal pay(OrderContext orderContext) {
        Map<KDiscount, Rule<KDiscount, BigDecimal>> discountRuleMap = discount(orderContext);

        return mapRule(discountRuleMap);
    }


    /**
     * Create method to generate Rules based on condition & arithmetical operation
     *
     * @param orderContext
     * @return The return type for every block is Map<KDiscount, Rule<KDiscount, BigDecimal>>
     */
    @Override
    public Map<KDiscount, Rule<KDiscount, BigDecimal>> discount(OrderContext orderContext) {
        BigDecimal priceOfProducts = ProductHelper.getTotalPriceOfProducts(orderContext.getProducts());

        List<Rule<KDiscount, BigDecimal>> rules = List.of(
                new Rule<>(KDiscount.RULE_1, () -> (priceOfProducts.doubleValue() >= 100), buildOperation(priceOfProducts, 0.17)),
                new Rule<>(KDiscount.RULE_2, () -> (priceOfProducts.doubleValue() >= 75
                        && priceOfProducts.doubleValue() <= 99), buildOperation(priceOfProducts, 0.12)),
                new Rule<>(KDiscount.RULE_3, () -> (priceOfProducts.doubleValue() < 75.0), buildOperation(priceOfProducts, 0.08))
        );

        return rules.stream().collect(Collectors.toMap(k -> k.type, v -> v));
    }


}
