package coe.fit.benefits.payment;

import coe.fit.benefits.payment.rules.KDiscount;
import coe.fit.benefits.payment.rules.Rule;
import coe.fit.benefits.order.OrderContext;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Interface responsible for abstracting payments
 */
public interface Payment {

    /**
     * Pay method
     *
     * @param orderContext relevant information to do the payment
     * @return result of the amount to be paid with the discounts applied
     */
    BigDecimal pay(OrderContext orderContext);

    /**
     * Create the condition and operation rules based on OrderContext
     *
     * @param orderContext
     * @return
     */
    Map<KDiscount, Rule<KDiscount, BigDecimal>> discount(OrderContext orderContext);
}
