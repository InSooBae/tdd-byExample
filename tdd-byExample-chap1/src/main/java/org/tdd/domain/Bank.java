package org.tdd.domain;

import java.util.Hashtable;

public class Bank {

    private final Hashtable<Pair, Integer> rates = new Hashtable<>();

    public Money reduce(Expression source, String toCurrency) {
        return source.reduce(this, toCurrency);
    }

    public int rate(String from, String to) {
        if (from.equals(to)) return 1;
        return rates.get(new Pair(from, to));
    }

    public void addRate(String from, String to, int rate) {
        rates.put(new Pair(from, to), rate);
    }
}
