package org.tdd.domain;

public interface Expression {

    Money reduce(Bank bank, String toCurrency);
}
