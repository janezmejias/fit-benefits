package coe.fit.benefits.order;

import coe.fit.benefits.exceptions.InvalidProcessor;
import coe.fit.benefits.payment.Payment;
import coe.fit.benefits.payment.factory.PaymentFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderHandler implements Order {

    /**
     * Search the employer factory for the implementer and proceed to do the payment
     *
     * @param orderContext
     * @return
     */
    @Override
    public BigDecimal pay(OrderContext orderContext) {
        Payment payment = PaymentFactory.of(orderContext.getProcessorType()).orElseThrow(InvalidProcessor::new);
        return payment.pay(orderContext).setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * Prints the results iterating through each product
     *
     * @param orderContext
     * @return
     */
    @Override
    public String print(OrderContext orderContext) {
        StringBuilder stringBuilder = new StringBuilder();
        orderContext.getProducts().forEach((product, quantity) ->
                stringBuilder.append("Product:{")
                        .append(product.getName()).append(",").append(product.getPrice()).append(",")
                        .append(product.getType()).append("},Quantity:").append(quantity).append(",Total:")
                        .append(product.getPrice().multiply(BigDecimal.valueOf(quantity))));
        return stringBuilder.toString();
    }
}
