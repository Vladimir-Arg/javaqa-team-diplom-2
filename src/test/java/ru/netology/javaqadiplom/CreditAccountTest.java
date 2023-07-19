package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {
    // Тесты для проверки метода ADD
    @Test
    public void shouldAddZeroBalanceToPositiveBalance() { // Пополнение нулевого баланса на положительный баланс; запрос баланса после пополнения
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.add(3_000);
        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldDeductZeroBalanceToNegativeBalance() { // Пополнение ненулевого баланса на отрицательное число (вычитание от нулевого баланса через функцию add); запрос баланса после пополнения
        CreditAccount account = new CreditAccount(
                4_000,
                5_000,
                15
        );
        account.add(-3_000);
        Assertions.assertEquals(4_000, account.getBalance());
    }

    @Test
    void shouldPositiveBalanceAddNull() { // Пополнение положительного баланса на "ноль"; запрос баланса после пополнения
        CreditAccount account = new CreditAccount(
                37,
                5_000,
                15
        );
        account.add(0);
        Assertions.assertEquals(37, account.getBalance());
    }

    // Тесты для проверки создания кредитного аккаунта
    @Test
    void shouldCreateAccountWithNegativeBalance() { // Создание кредитного аккаунта с отрицательным начальным балансом

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(-5_000, 1_000, 15);
        });
    }

    @Test
    void shouldCreateCorrectAccountAndGetBalance() {         // Создание кредитного аккаунта с ненулевыми значениями; запрос баланса
        CreditAccount account = new CreditAccount(
                2_000,
                5_000,
                25
        );
        Assertions.assertEquals(2_000, account.getBalance());

    }

    @Test
    void shouldCreateCorrectNullAccountAndGetBalance() { // Создание кредитного аккаунта с нулевыми значениями начального баланса, лимита и кредитной ставкой; запрос баланса
        CreditAccount account = new CreditAccount(
                0,
                0,
                0
        );
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    void shouldCreateCorrectNullAccountAndGetRate() { // Создание кредитного аккаунта с нулевыми значениями начального баланса, лимита и кредитной ставкой; запрос кредитной ставки
        CreditAccount account = new CreditAccount(
                0,
                0,
                0
        );

        Assertions.assertEquals(0, account.getRate());
    }

    @Test
    void shouldCreateAccountWithNegativeRate() {// Создание кредитного аккаунта с отрицательной процентной ставкой
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(5_000, 1_000, -15);
        });
    }

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
    void shouldPayFromZeroBalanceToLessCreditLimitAnd() { // Покупка при нулевом начальном балансе, после покупки баланс отрицательный, входит в лимит; запрос баланса
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.pay(3_000);
        Assertions.assertEquals(-3000, account.getBalance());
    }

    @Test
    void shouldPayWithNegativeAmount() { // Покупка на отрицательную сумму (пополнение баланса через метод pay); запрос баланса

        CreditAccount account = new CreditAccount(
                66,
                5_000,
                15
        );
        account.pay(-3_000);
        Assertions.assertEquals(66, account.getBalance());
    }

    @Test
    void shouldPayWithZeroAmount() { // Покупка на нулевую сумму; запрос баланса

        CreditAccount account = new CreditAccount(
                66,
                5_000,
                15
        );
        account.pay(0);
        Assertions.assertEquals(66, account.getBalance());
    }

    @Test
    void shouldPayFromPositiveBalanceToPositiveBalance() { // Покупка при положительном начальном балансе, после покупки баланс положительный; запрос баланса
        CreditAccount account = new CreditAccount(
                777,
                1_000,
                15
        );
        account.pay(300);
        Assertions.assertEquals(777 - 300, account.getBalance());
    }

    @Test
    void shouldPayFromPositiveBalanceToNegativeBalanceLessCreditLimit() { // Покупка при положительном начальном балансе, после покупки баланс отрицательный, в рамках лимита; запрос баланса
        CreditAccount account = new CreditAccount(
                777,
                1_000,
                15
        );
        account.pay(1_000);
        Assertions.assertEquals(777 - 1_000, account.getBalance());
    }

    @Test
    void shouldPayFromPositiveBalanceEqualCreditLimit() { // Покупка при положительном начальном балансе, после покупки баланс равен лимиту; запрос баланса
        CreditAccount account = new CreditAccount(
                1_000,
                4_000,
                15
        );
        account.pay(5_000);
        Assertions.assertEquals(-4000, account.getBalance());
    }

    @Test
    void shouldPayFromZeroBalanceToMoreCreditLimit() { // Покупка при нулевом начальном балансе, после покупки баланс отрицательный, выходит за рамки лимита; запрос баланса
        CreditAccount account = new CreditAccount(
                0,
                500,
                15
        );
        account.pay(3_000);
        Assertions.assertEquals(0, account.getBalance());
    }

    // Тесты для проверки метода yearChange
    @Test
    void shouldYearChangePositiveBalance() { // Расчет процентов при положительном балансе
        CreditAccount account = new CreditAccount(
                200,
                5_000,
                15
        );
        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    void shouldYearChangeNegativeBalance() {  // Расчет процентов при отрицательном балансе
        CreditAccount account = new CreditAccount(
                0, // по условию начальный баланс - неотрицательное число
                5_000,
                15
        );
        account.pay(200); // выставление отрицательного баланса
        Assertions.assertEquals(-30, account.yearChange());
    }

    @Test
    void shouldYearChangeZeroBalance() {  // Расчет процентов при нулевом балансе
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    void shouldYearChangeZeroRate() {  // Расчет процентов при нулевой ставке
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                0
        );
        account.pay(2_000); // выставление отрицательного баланса
        Assertions.assertEquals(0, account.yearChange());
    }


    @Test
    void shouldGetCreditLimit() { // получение текущего лимита
        CreditAccount account = new CreditAccount(
                100,
                5_000,
                15
        );
        Assertions.assertEquals(5_000, account.getCreditLimit());
    }

    @Test
    void shouldSetRate() { // выставление новой ставки (проверка метода account.setRate)
        CreditAccount account = new CreditAccount(
                200,
                5_000,
                15
        );
        account.setRate(39);
        Assertions.assertEquals(39, account.getRate());
    }
}
