package coe.fit.benefits.payment.factory.processors;

import coe.fit.benefits.payment.rules.KDiscount;
import coe.fit.benefits.payment.rules.Rule;
import coe.fit.benefits.product.helper.ProductHelper;
import coe.fit.benefits.order.OrderContext;
import coe.fit.benefits.payment.factory.processors.commons.AbstractProcessor;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of payment with VISA processor
 */
public class VisaPayment extends AbstractProcessor {


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
     * Generate Rules based on condition & arithmetical operation
     *
     * @param orderContext
     * @return The return type for every block is Map<KDiscount, Rule<KDiscount, BigDecimal>>
     */
    @Override
    public Map<KDiscount, Rule<KDiscount, BigDecimal>> discount(OrderContext orderContext) {
        Collection<Integer> products = orderContext.getProducts().values();
        Integer totalDiffProducts = products.stream().reduce(0, Integer::sum);
        BigDecimal priceOfProducts = ProductHelper.getTotalPriceOfProducts(orderContext.getProducts());

        List<Rule<KDiscount, BigDecimal>> rules = List.of(
                new Rule<>(KDiscount.RULE_1, () -> (totalDiffProducts >= 10), buildOperation(priceOfProducts, 0.15)),
                new Rule<>(KDiscount.RULE_2, () -> (totalDiffProducts >= 7 && totalDiffProducts <= 9), buildOperation(priceOfProducts, 0.10)),
                new Rule<>(KDiscount.RULE_3, () -> (totalDiffProducts <= 6), buildOperation(priceOfProducts, 0.05))
        );
        return rules.stream().collect(Collectors.toMap(k -> k.type, v -> v));
    }

}

