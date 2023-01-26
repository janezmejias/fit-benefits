/*
 *  Order
 *  1.0
 *  11/8/22, 8:28 PM
 *  Copyright (c) 2022 juan.anez
 *  Any illegal reproduction of this content will result in immediate legal action.
 */

package coe.fit.benefits.order;

import java.math.BigDecimal;

/**
 * Order Abstraction.
 */
public interface Order {


    /**
     * @param orderContext
     * @return
     */
    BigDecimal pay(OrderContext orderContext);

    /**
     *
     * @param orderContext
     * @return
     */
    String print(OrderContext orderContext);
}
