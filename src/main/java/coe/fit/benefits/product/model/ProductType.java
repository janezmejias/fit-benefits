package coe.fit.benefits.product.model;

public enum ProductType {
    BASIC(1), WORK_TOOL(2), LUXURY(3);

    public final Integer id;

    ProductType(Integer id) {
        this.id = id;
    }
}
