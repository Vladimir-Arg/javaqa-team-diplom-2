package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }
/* никак не могу придумать как написать тест для проверки   if (rate <= 0) {
            throw new IllegalArgumentException

    @Test
    public void shouldAddNegativeRate() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                -15
        );

        account.add(3_000);

        Assertions.assertEquals(3, account.getBalance());
    }
*/

    @Test
    void shouldPayPositive() {
        CreditAccount account = new CreditAccount(
            0,
            5_000,
            15
    );
        account.pay (3_000);
        Assertions.assertEquals(-3000,account.getBalance());
    }
    @Test
    void shouldPayNegativeAmountMinus() {
        CreditAccount account = new CreditAccount(
                66,
                5_000,
                15
        );
        account.pay (-3_000);
        Assertions.assertEquals(66,account.getBalance());
    }
    @Test
    void shouldPayNegativeBalanceLessCreditLimit() {
        CreditAccount account = new CreditAccount(
                777,
                0,
                15
        );
        account.pay (3_000);
        Assertions.assertEquals(-2223,account.getBalance());
    }

    @Test
    void shouldAddTrue() {
        CreditAccount account = new CreditAccount(
                37,
                5_000,
                15
        );
        account.add(5);
        Assertions.assertEquals(5, account.getBalance());

    }

    @Test
    void yearChange() {  // Проверить как считается в программе
        CreditAccount account = new CreditAccount(
                200,
                5_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    void shouldGetCreditLimit() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        Assertions.assertEquals(5_000, account.getCreditLimit());
    }
}
