package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BankTest {

    @Test
    void shouldTransferSavingCorrect() { // Успешное проведение перевода между дебетовыми картами с допустимыми значениями
        SavingAccount from = new SavingAccount(200, 0, 15_000, 20);
        SavingAccount to = new SavingAccount(1_000, 10, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 20);
        Assertions.assertEquals(180, from.getBalance());
        Assertions.assertEquals(1_020, to.getBalance());
    }

    @Test
    void shouldTransferCreditPositiveBalance() { // Проведение перевода между кредитными картами с положительным балансом до и после операции
        CreditAccount from = new CreditAccount(2_000, 15_000, 20);
        CreditAccount to = new CreditAccount(1_000, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 200);
        Assertions.assertEquals(1_800, from.getBalance());
        Assertions.assertEquals(1_200, to.getBalance());
    }

    @Test
    void shouldTransferCreditNegativeBalance() { // Проведение перевода между кредитными картами с отрицательным балансом до и после операции.
        CreditAccount from = new CreditAccount(0, 15_000, 20);
            // Делаем отрицательный баланс карты отправителя до перевода
            from.pay(2_000);
        CreditAccount to = new CreditAccount(0, 10_000, 15);
            // Делаем отрицательный баланс карты получателя до перевода
            to.pay(3_000);

        Bank account = new Bank();
        account.transfer(from, to, 2000);
        Assertions.assertEquals(-4_000, from.getBalance());
        Assertions.assertEquals(-1_000, to.getBalance());
    }

    @Test
    void shouldTransferCreditNegativeToPositiveBalance() { // КРЕДИТКИ До перевода баланс карты получателя отрицательный, после перевода - положительный
        CreditAccount from = new CreditAccount(5_000, 15_000, 20);
        CreditAccount to = new CreditAccount(0, 10_000, 15);
            to.pay(1_000); // Делаем отрицательный баланс карты получателя до перевода

        Bank account = new Bank();
        account.transfer(from, to, 2_000);
        Assertions.assertEquals(3_000, from.getBalance());
        Assertions.assertEquals(1_000, to.getBalance());
    }

    @Test
    void shouldTransferCreditPositiveToNegativeBalance() { // КРЕДИТКИ До перевода баланс отправителя положительный, после - отрицательный
        CreditAccount from = new CreditAccount(1_000, 15_000, 20);
        CreditAccount to = new CreditAccount(1_000, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 2000);
        Assertions.assertEquals(-1_000, from.getBalance());
        Assertions.assertEquals(3_000, to.getBalance());

    }

    @Test
    void shouldTransferCreditOverLimit() { // КРЕДИТКИ После перевода баланс отправителя выходит за лимит
        CreditAccount from = new CreditAccount(1_000, 1_000, 20);
        CreditAccount to = new CreditAccount(1_000, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 3000);
        Assertions.assertEquals(1_000, from.getBalance());
        Assertions.assertEquals(1_000, to.getBalance());
    }
    @Test
    void shouldTransferCreditEqualLimit() { // КРЕДИТКИ После перевода баланс отправителя равен лимиту баланса
        CreditAccount from = new CreditAccount(1_000, 2_000, 20);
        CreditAccount to = new CreditAccount(1_000, 1_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 3_000);
        Assertions.assertEquals(-2_000, from.getBalance());
        Assertions.assertEquals(4_000, to.getBalance());
    }
    @Test
    void shouldTransferZeroAmount() { // Нулевой перевод (вне зависимости от карт)
        CreditAccount from = new CreditAccount(111, 1_000, 20);
        CreditAccount to = new CreditAccount(222, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 0);
        Assertions.assertEquals(111, from.getBalance());
        Assertions.assertEquals(222, to.getBalance());
    }
    @Test
    void shouldTransferNegativeAmount() { // Отрицательный перевод (вне зависимости от карт)
        CreditAccount from = new CreditAccount(5_000, 1_000, 20);
        CreditAccount to = new CreditAccount(0, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, -200);
        Assertions.assertEquals(5_000, from.getBalance());
        Assertions.assertEquals(0, to.getBalance());
    }
    @Test
    void shouldTransferSavingLessMinBalance() { // ДЕБЕТОВЫЕ После перевода баланс отправителя меньше минимального баланса
        SavingAccount from = new SavingAccount(2_000, 1_000, 15_000, 20);
        SavingAccount to = new SavingAccount(1_000, 1_000, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 3_000);
        Assertions.assertEquals(2_000, from.getBalance());
        Assertions.assertEquals(1_000, to.getBalance());
    }
    @Test
    void shouldTransferSavingOverMaxBalance() { // ДЕБЕТОВЫЕ После перевода баланс получателя больше максимального баланса
        SavingAccount from = new SavingAccount(5_000, 1_000, 10_000, 20);
        SavingAccount to = new SavingAccount(2_000, 1_000, 3_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 3_000);
        Assertions.assertEquals(5_000, from.getBalance());
        Assertions.assertEquals(2_000, to.getBalance());
    }
    @Test
    void shouldTransferSavingEqualMaxBalance() { // ДЕБЕТОВЫЕ После перевода баланс получателя равен максимальному баланса
        SavingAccount from = new SavingAccount(5_000, 1_000, 10_000, 20);
        SavingAccount to = new SavingAccount(2_000, 1_000, 5_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 3_000);
        Assertions.assertEquals(2_000, from.getBalance());
        Assertions.assertEquals(5_000, to.getBalance());
    }
    @Test
    void shouldTransferSavingEqualMinBalance() { // ДЕБЕТОВЫЕ После перевода баланс отправителя равен минимальному баланса
        SavingAccount from = new SavingAccount(4_000, 1_000, 15_000, 20);
        SavingAccount to = new SavingAccount(4_000, 1_000, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 3_000);
        Assertions.assertEquals(1_000, from.getBalance());
        Assertions.assertEquals(7_000, to.getBalance());
    }
    @Test
    void shouldTransferCreditPositiveBalanceToSave() { // Успешный перевод с кредитки на дебетовую (обе с положительным балансом до и после операции)
        CreditAccount from = new CreditAccount(4_000, 10_000, 20);
        SavingAccount to = new SavingAccount(4_000, 1_000, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 3_000);
        Assertions.assertEquals(1_000, from.getBalance());
        Assertions.assertEquals(7_000, to.getBalance());
    }
    @Test
    void shouldTransferCreditNegativeBalanceToSave() { // Успешный перевод с кредитки на дебетовую (Кредитка с отрицательным балансом до и после операции)
        CreditAccount from = new CreditAccount(0, 10_000, 20);
            from.pay(500);
        SavingAccount to = new SavingAccount(4_000, 1_000, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 700);
        Assertions.assertEquals(-1_200, from.getBalance());
        Assertions.assertEquals(4_700, to.getBalance());
    }
    @Test
    void shouldTransferCreditZeroBalanceToSave() { // Успешный перевод с кредитки на дебетовую (Кредитка с нулевым балансом до и с отрицательным балансом после операции)
        CreditAccount from = new CreditAccount(4_000, 10_000, 20);
        SavingAccount to = new SavingAccount(4_000, 1_000, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 3_000);
        Assertions.assertEquals(1_000, from.getBalance());
        Assertions.assertEquals(7_000, to.getBalance());
    }
}