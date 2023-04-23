package org.tdd.domain;

import java.util.Objects;

public class Money {
    protected int amount;


    @Override
    public boolean equals(Object o) {
        Money money = (Money) o;
        return amount == money.amount
                && getClass().equals(money.getClass());
    }

}
