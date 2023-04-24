package org.tdd.domain;

public interface Expression {

    Money reduce(String toCurrency);
}
