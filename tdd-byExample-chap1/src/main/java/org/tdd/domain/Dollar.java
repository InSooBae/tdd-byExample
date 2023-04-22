package org.tdd.domain;

public class Dollar {
    int amount;

    public Dollar(int amount) {
        this.amount = amount;
    }

    public Dollar times(int multiplier) {
        this.amount *= multiplier;

        return null;
    }
}
