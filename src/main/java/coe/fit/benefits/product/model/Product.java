/*
 *  Product
 *  1.0
 *  11/8/22, 8:30 PM
 *  Copyright (c) 2022 juan.anez
 *  Any illegal reproduction of this content will result in immediate legal action.
 */

package coe.fit.benefits.product.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * The type Product.
 */
@Data
@Builder
public class Product {
    /**
     * The Product name.
     **/
    private String name;
    /**
     * The Product price.
     **/
    private BigDecimal price;
    /**
     * The Product type defined by: 1 = Basic need, 2 =  Work tool, 3 = Luxury.
     **/
    private ProductType type;
}
