package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    // Общие проверки

    @Test
    public void shouldMinBalanceMoreNaxBalance() { // минимальный баланс больше максимального

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(5_000, 5_000, 2_000, -15);
        });

    }

    @Test
    public void shouldMinBalanceLessZero() { // минимальный баланс меньше 0

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(5_000, -1_000, 10_000, 15);
        });
    }

    @Test
    public void shouldMaxBalanceLessZero() { // максимальный баланс меньше 0

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(5_000, 1_000, -1_000, 15);
        });
    }

    @Test
    public void shouldMaxBalanceEqualsZero() { // максимальный баланс равен 0

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(5_000, 0, 0, 15);
        });
    }

    @Test
    public void shouldInitialBalanceEqualsZero() { // начальный баланс меньше минимального

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(500, 1_000, 10_000, 15);
        });
    }


    @Test
    public void shouldInitialBalanceMoreMaxBalance() { // начальный баланс больше максимального

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(12_000, 1_000, 10_000, 15);
        });
    }

    
    // Тесты на метод Pay
    @Test
    public void shouldPayWithinMinBalance() { // покупка с соблюдением всех условий
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(500);

        Assertions.assertEquals(2_000 - 500, account.getBalance());
    }

    @Test
    public void shouldPayMoreThanTheMinBalance() { // покупка на сумму превышающую мин.баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(1_100);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayNegativeBalance() { // покупка когда баланс уходит в минус
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(3_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayZero() { // покупка на 0 сумму
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(0);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayEqualMinBalance() { //покупка с конечным балансом равным минимальному
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(1_000);

        Assertions.assertEquals(2_000 - 1_000, account.getBalance());
    }


    // Тесты на метод Add
    @Test
    public void shouldAddThanMaxBalance() { // пополнение не больше максимального
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(11_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldAddEqualMaxBalance() { // пополнение больше максимального
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(10_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldAddZeroValue() { // пополнение на 0
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(0);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldAddNegativInMaxBalance() { // пополнение на отрицательную сумму
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(-1_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldAddEqualsMaxBalance() { // пополнение на сумму равную максимальному балансу
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(8_000);

        Assertions.assertEquals(10_000, account.getBalance());
    }

    @Test
    public void shouldYearChange() { // нормальный годовой процент
        SavingAccount account = new SavingAccount(
                50_000,
                1_000,
                10_000,
                5
        );

        account.yearChange();

        Assertions.assertEquals(2_500, account.yearChange());
    }

    @Test
    public void shouldZeroInitialBalanceYearChange() { // нулевая сумма на счету
        SavingAccount account = new SavingAccount(
                0,
                1_000,
                10_000,
                10
        );

        account.yearChange();

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void shouldZeroYearChange() { // годовой процент 0
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                0
        );

        account.yearChange();

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void shouldNegativeYearChange() { //годовой процент отрицательное число

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(5_000, 1_000, 10_000, -15);
        });

    }
}