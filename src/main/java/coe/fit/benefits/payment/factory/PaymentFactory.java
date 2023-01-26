package coe.fit.benefits.payment.factory;

import coe.fit.benefits.payment.Payment;
import coe.fit.benefits.payment.factory.processors.MastercardPayment;
import coe.fit.benefits.payment.factory.processors.commons.ProcessorType;
import coe.fit.benefits.payment.factory.processors.VisaPayment;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PaymentFactory {

    static Map<ProcessorType, Payment> operationMap = new HashMap<>();

    static {
        operationMap.put(ProcessorType.VISA, new VisaPayment());
        operationMap.put(ProcessorType.MASTERCARD, new MastercardPayment());
    }

    public static Optional<Payment> of(ProcessorType processorType) {
        return Optional.ofNullable(operationMap.get(processorType));
    }
}
