package org.tdd.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OperationTest {

    @Test
    @DisplayName("어떤 금액(주가)을 어떤 수(주식의 수)에 곱한 금액을 결과로 얻을 수 있어야 한다.")
    public void testMultiplication() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    @Test
    @DisplayName("돈(금액)이 같은 돈(금액)인지(동치성)")
    public void testEquality() {
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertNotEquals(Money.dollar(5), Money.dollar(6));
        assertNotEquals(Money.dollar(5), Money.franc(5));
    }


    @Test
    @DisplayName("같은 통화(currency)인지 확인")
    public void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

    @Test
    @DisplayName("$5 + $5 = $10")
    public void testSimpleAddition() {
        Expression sum = new Operand(Money.dollar(3), Money.dollar(4));

        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(7), reduced);
    }

    @Test
    @DisplayName("reduce메서드 Sum 이외에도 동작이 잘 되는지")
    public void testReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertEquals(Money.dollar(1), result);
    }

    @Test
    @DisplayName("프랑 달러로 환전")
    public void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");
        assertEquals(Money.dollar(1), result);
    }

    @Test
    @DisplayName("같은 돈 환전시 같은 비율 리턴")
    public void testIdentityRate() {
        assertEquals(1, new Bank().rate("USD", "USD"));
    }

    @Test
    @DisplayName("서로 다른 돈 합칠 때 환전 후 합치기")
    public void testMixedAddition() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFranc = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(fiveBucks.plus(tenFranc), "USD");
        assertEquals(Money.dollar(10), result);
    }

    @Test
    @DisplayName("$5 + 10CHF 환율 바꾼 뒤 합")
    public void testSumPlusMoney() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Operand(fiveBucks, tenFrancs).plus(fiveBucks);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(15), result);
    }

    @Test
    @DisplayName("두 돈을 더한 뒤 곱해보기")
    public void testSumTimes() throws Exception {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFranc = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Operand(fiveBucks, tenFranc).times(2);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(20), result);
    }

    @Test
    @DisplayName("같은 통화면 Money 리턴 -> 결국 외부 객체가 처리해야 편하지 않을까?")
    public void testPlusSSameCurrencyReturnMoney() {
        Money dollar = Money.dollar(1);
        Money dollar2 = Money.dollar(2);
//        Expression sum = Money.dollar(1).plus(Money.dollar(1));
        Bank bank = new Bank();
        Expression sum = dollar.plus(dollar2);
        boolean equals = dollar.currency().equals(dollar2.currency());
        if (equals) {
            sum = sum.reduce(bank, dollar.currency());
        }
        assertTrue(sum instanceof Money);
    }
}
