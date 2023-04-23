package org.tdd.domain;

import java.util.Objects;

public class Franc extends Money {

    public Franc(int amount) {
        this.amount = amount;
    }


    public Money times(int multiplier) {
        return new Franc(amount * multiplier);
    }
}
