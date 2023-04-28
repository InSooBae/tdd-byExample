package org.tdd.domain;

import java.util.LinkedList;
import java.util.List;

public class Operand extends Expression {
    private final Expression augend;
    private final Expression addend;

    public Operand(Expression augend, Expression addend) {
        this.augend = augend;
        this.addend = addend;
    }
    @Override
    public Money reduce(Bank bank, String toCurrency) {
        int amount = augend.reduce(bank, toCurrency).amount + addend.reduce(bank, toCurrency).amount;
        return new Money(amount, toCurrency);
    }

    @Override
    public Expression times(int multiplier) {
        return new Operand(augend.times(multiplier), addend.times(multiplier));
    }
}
