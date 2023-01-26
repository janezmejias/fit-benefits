package coe.fit.benefits.order;

import coe.fit.benefits.payment.factory.processors.commons.ProcessorType;
import coe.fit.benefits.product.model.Product;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class OrderContext {

    private Map<Product, Integer> products;
    private ProcessorType processorType;

}
