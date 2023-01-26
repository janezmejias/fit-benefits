package coe.fit.benefits.payment.rules;

import lombok.AllArgsConstructor;

import java.util.function.Supplier;

@AllArgsConstructor
public class Rule<K, T> {

    public K type;
    public Supplier<Boolean> condition;
    public Supplier<T> operation;
}
