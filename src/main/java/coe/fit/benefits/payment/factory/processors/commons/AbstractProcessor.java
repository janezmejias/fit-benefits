package coe.fit.benefits.payment.factory.processors.commons;

import coe.fit.benefits.exceptions.InvalidExecutingRule;
import coe.fit.benefits.payment.Payment;
import coe.fit.benefits.payment.rules.KDiscount;
import coe.fit.benefits.payment.rules.Rule;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class AbstractProcessor implements Payment {

    /**
     * Given a set of filters, proceed to apply function map on the Supplier
     *
     * @param rules Set of rules
     * @param <T>
     * @return First supplier found
     */
    protected <T> T mapRule(Map<KDiscount, Rule<KDiscount, T>> rules) {
        return Stream.of(KDiscount.values())
                .filter(discountType -> rules.get(discountType).condition.get())
                .map(discountType -> rules.get(discountType).operation.get())
                .findFirst()
                .orElseThrow(InvalidExecutingRule::new);
    }

    /**
     * Generate arithmetical operation
     *
     * @param discount discount to apply
     * @return Supplier with result
     */
    protected Supplier buildOperation(BigDecimal priceOfProducts, Double discount) {
        return () -> priceOfProducts.subtract(priceOfProducts.multiply(BigDecimal.valueOf(discount)));
    }
}
