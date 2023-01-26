package coe.fit.benefits.exceptions;

public class InvalidProcessor extends RuntimeException {

    public InvalidProcessor() {
        super("Payment processor is invalid");
    }
}
