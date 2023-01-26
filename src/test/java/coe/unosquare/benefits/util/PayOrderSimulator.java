/*
 *  PayOrderSimulator
 *  1.0
 *  11/8/22, 8:29 PM
 *  Copyright (c) 2022 Unosquare
 *  Any illegal reproduction of this content will result in immediate legal action.
 */

package coe.unosquare.benefits.util;

import coe.fit.benefits.order.Order;
import coe.fit.benefits.order.OrderContext;
import coe.fit.benefits.order.OrderHandler;
import coe.fit.benefits.payment.factory.processors.commons.ProcessorType;
import coe.fit.benefits.product.model.Product;

import java.math.BigDecimal;
import java.util.Map;

/**
 * The type Pay order simulator.
 */
public final class PayOrderSimulator {

    /**
     * Method to simulate the process of an order being paid.
     *
     * @param products    the products
     * @param paymentType the payment type
     * @return the double
     */
    public static BigDecimal payOrder(final Map<Product, Integer> products,
                                      final ProcessorType paymentType) {
        Order order = new OrderHandler();
        return order.pay(OrderContext.builder()
                .products(products)
                .processorType(paymentType)
                .build());
    }

}

