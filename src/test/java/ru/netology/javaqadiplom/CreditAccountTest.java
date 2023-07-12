package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {
    // Тесты для проверки метода ADD
    @Test
    public void shouldAddZeroBalanceToPositiveBalance() { // Пополнение нулевого баланса на положительный баланс
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldDeductZeroBalanceToNegativeBalance() { // Пополнение нулевого баланса на отрицательное число (вычитание от нулевого баланса через функцию add)
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(-3_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    void shouldPositiveBalanceAddNull() { // Пополнение положительного баланса на "ноль"
        CreditAccount account = new CreditAccount(
                37,
                5_000,
                15
        );
        account.add(0);
        Assertions.assertEquals(37, account.getBalance());

    }

    @Test
    void shouldNegativeBalanceAddNull() { // Пополнение отрицатаельного баланса на "ноль"
        CreditAccount account = new CreditAccount(
                -68,
                5_000,
                15
        );
        account.add(0);
        Assertions.assertEquals(-68, account.getBalance());

    }

    // Тесты для проверки создания кредитного аккаунта
    @Test
    void shouldCreateAccountWithNegativeBalanceAndGetBalance() { // Создание кредитного аккаунта с отрицательным начальным балансом
        CreditAccount account = new CreditAccount(
                -2_000,
                5_000,
                25
        );

        Assertions.assertEquals(-2_000, account.getBalance());

    }

    @Test
        // ОР != ФР ( ОР должен быть в рамках лимита, либо выкидывать на ошибку)
    void shouldCreateAccountWithBalanceFewLimitAndGetBalance() { // Создание кредитного аккаунта с отрицательным начальным балансом, который выходит за пределы лимита
        CreditAccount account = new CreditAccount(
                -20_000,
                1_000,
                25
        );

        Assertions.assertEquals(-20_000, account.getBalance());

    }

    @Test
    void shouldCreateCorrectAccountAndGetBalance() { // Создание кредитного аккаунта с ненулевыми значениями
        CreditAccount account = new CreditAccount(
                2_000,
                5_000,
                25
        );

        Assertions.assertEquals(2_000, account.getBalance());

    }

    // никак не могу придумать как написать тест для проверки   if (rate <= 0) {throw new IllegalArgumentException
/* @Test
    void shouldCreateCorrectNullAccountAndGetBalance(){ // Создание кредитного аккаунта с нулевыми значениями начального баланса, лимита и кредитной ставкой
        CreditAccount account = new CreditAccount(
                0,
                0,
                0
        );

        Assertions.assertEquals(0, account.getBalance());

    }
    @Test
    void shouldCreateCorrectNullAccountAndGetRate(){ // Создание кредитного аккаунта с нулевыми значениями начального баланса, лимита и кредитной ставкой
        CreditAccount account = new CreditAccount(
                0,
                0,
                0
        );

        Assertions.assertEquals(0, account.getRate());

    }
    @Test
    void shouldCreateAccountWithNegativeRateAndGetRate(){ // Создание кредитного аккаунта с отрицательной процентной ставкой
        CreditAccount account = new CreditAccount(
                2_000,
                5_000,
                -25
        );

        Assertions.assertEquals(-25, account.getRate());

    }
  */
    @Test
    void shouldCreateCorrectAccountAndGetRate() { // Создание кредитного аккаунта с ненулевыми корректными значениями
        CreditAccount account = new CreditAccount(
                2_000,
                5_000,
                25
        );

        Assertions.assertEquals(25, account.getRate());

    }

    // Тесты для проверки метода PAY
    @Test
    void shouldPayPositive() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.pay(3_000);
        Assertions.assertEquals(-3000, account.getBalance());
    }

    @Test
    void shouldPayNegativeAmountMinus() {
        CreditAccount account = new CreditAccount(
                66,
                5_000,
                15
        );
        account.pay(-3_000);
        Assertions.assertEquals(66, account.getBalance());
    }

    @Test
    void shouldPayNegativeBalanceLessCreditLimit() {
        CreditAccount account = new CreditAccount(
                777,
                0,
                15
        );
        account.pay(3_000);
        Assertions.assertEquals(-2223, account.getBalance());
    }

    @Test
// ОР != ФР (если баланс больше 0, то процент на кредит должен быть равен нулю, т.к. по сути кредита нет)
    void shouldYearChangeWithPositiveBalance() {  // Проверить как считается в программе
        CreditAccount account = new CreditAccount(
                200,
                5_000,
                15
        );

        Assertions.assertEquals(30, account.yearChange());
    }

    @Test
    void shouldYearChangeWithNegativeBalance() {  //
        CreditAccount account = new CreditAccount(
                -200,
                5_000,
                15
        );

        Assertions.assertEquals(-30, account.yearChange());
    }

    @Test
// ОР != ФР
    void shouldYearChangeWhenInitialBalanceMoreCreditLimit() {  // По условию Баланс не должен превышать лимит
        CreditAccount account = new CreditAccount(
                -200,
                50,
                15
        );

        Assertions.assertEquals(-30, account.yearChange());
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
