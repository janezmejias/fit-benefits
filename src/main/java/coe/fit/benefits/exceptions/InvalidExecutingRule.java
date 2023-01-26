package coe.fit.benefits.exceptions;

public class InvalidExecutingRule extends RuntimeException {

    public InvalidExecutingRule() {
        super("An error occurred while executing the rule");
    }
}
