package coe.fit.benefits.exceptions;

public class NoProductFound extends RuntimeException {

    public NoProductFound() {
        super("No products found");
    }

}
