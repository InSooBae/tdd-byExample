package org.tdd.domain;

public abstract class Expression {

    abstract public Money reduce(Bank bank, String toCurrency);

    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    abstract public Expression times(int multiplier);
}
