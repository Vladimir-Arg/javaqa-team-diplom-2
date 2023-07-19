package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BankTest {

    @Test
    void shouldTransferSavingCorrect() { // �������� ���������� �������� ����� ���������� ������� � ����������� ����������
        SavingAccount from = new SavingAccount(200, 0, 15_000, 20);
        SavingAccount to = new SavingAccount(1_000, 10, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 20);
        Assertions.assertEquals(180, from.getBalance());
        Assertions.assertEquals(1_020, to.getBalance());
    }

    @Test
    void shouldTransferCreditPositiveBalance() { // ���������� �������� ����� ���������� ������� � ������������� �������� �� � ����� ��������
        CreditAccount from = new CreditAccount(2_000, 15_000, 20);
        CreditAccount to = new CreditAccount(1_000, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 200);
        Assertions.assertEquals(1_800, from.getBalance());
        Assertions.assertEquals(1_200, to.getBalance());
    }

    @Test
    void shouldTransferCreditNegativeBalance() { // ���������� �������� ����� ���������� ������� � ������������� �������� �� � ����� ��������.
        CreditAccount from = new CreditAccount(0, 15_000, 20);
            // ������ ������������� ������ ����� ����������� �� ��������
            from.pay(2_000);
        CreditAccount to = new CreditAccount(0, 10_000, 15);
            // ������ ������������� ������ ����� ���������� �� ��������
            to.pay(3_000);

        Bank account = new Bank();
        account.transfer(from, to, 2000);
        Assertions.assertEquals(-4_000, from.getBalance());
        Assertions.assertEquals(-1_000, to.getBalance());
    }

    @Test
    void shouldTransferCreditNegativeToPositiveBalance() { // �������� �� �������� ������ ����� ���������� �������������, ����� �������� - �������������
        CreditAccount from = new CreditAccount(5_000, 15_000, 20);
        CreditAccount to = new CreditAccount(0, 10_000, 15);
            to.pay(1_000); // ������ ������������� ������ ����� ���������� �� ��������

        Bank account = new Bank();
        account.transfer(from, to, 2_000);
        Assertions.assertEquals(3_000, from.getBalance());
        Assertions.assertEquals(1_000, to.getBalance());
    }

    @Test
    void shouldTransferCreditPositiveToNegativeBalance() { // �������� �� �������� ������ ����������� �������������, ����� - �������������
        CreditAccount from = new CreditAccount(1_000, 15_000, 20);
        CreditAccount to = new CreditAccount(1_000, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 2000);
        Assertions.assertEquals(-1_000, from.getBalance());
        Assertions.assertEquals(3_000, to.getBalance());

    }

    @Test
    void shouldTransferCreditOverLimit() { // �������� ����� �������� ������ ����������� ������� �� �����
        CreditAccount from = new CreditAccount(1_000, 1_000, 20);
        CreditAccount to = new CreditAccount(1_000, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 3000);
        Assertions.assertEquals(1_000, from.getBalance());
        Assertions.assertEquals(1_000, to.getBalance());
    }
    @Test
    void shouldTransferCreditEqualLimit() { // �������� ����� �������� ������ ����������� ����� ������ �������
        CreditAccount from = new CreditAccount(1_000, 2_000, 20);
        CreditAccount to = new CreditAccount(1_000, 1_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 3_000);
        Assertions.assertEquals(-2_000, from.getBalance());
        Assertions.assertEquals(4_000, to.getBalance());
    }
    @Test
    void shouldTransferZeroAmount() { // ������� ������� (��� ����������� �� ����)
        CreditAccount from = new CreditAccount(111, 1_000, 20);
        CreditAccount to = new CreditAccount(222, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 0);
        Assertions.assertEquals(111, from.getBalance());
        Assertions.assertEquals(222, to.getBalance());
    }
    @Test
    void shouldTransferNegativeAmount() { // ������������� ������� (��� ����������� �� ����)
        CreditAccount from = new CreditAccount(5_000, 1_000, 20);
        CreditAccount to = new CreditAccount(0, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, -200);
        Assertions.assertEquals(5_000, from.getBalance());
        Assertions.assertEquals(0, to.getBalance());
    }
    @Test
    void shouldTransferSavingLessMinBalance() { // ��������� ����� �������� ������ ����������� ������ ������������ �������
        SavingAccount from = new SavingAccount(2_000, 1_000, 15_000, 20);
        SavingAccount to = new SavingAccount(1_000, 1_000, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 3_000);
        Assertions.assertEquals(2_000, from.getBalance());
        Assertions.assertEquals(1_000, to.getBalance());
    }
    @Test
    void shouldTransferSavingOverMaxBalance() { // ��������� ����� �������� ������ ���������� ������ ������������� �������
        SavingAccount from = new SavingAccount(5_000, 1_000, 10_000, 20);
        SavingAccount to = new SavingAccount(2_000, 1_000, 3_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 3_000);
        Assertions.assertEquals(5_000, from.getBalance());
        Assertions.assertEquals(2_000, to.getBalance());
    }
    @Test
    void shouldTransferSavingEqualMaxBalance() { // ��������� ����� �������� ������ ���������� ����� ������������� �������
        SavingAccount from = new SavingAccount(5_000, 1_000, 10_000, 20);
        SavingAccount to = new SavingAccount(2_000, 1_000, 5_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 3_000);
        Assertions.assertEquals(2_000, from.getBalance());
        Assertions.assertEquals(5_000, to.getBalance());
    }
    @Test
    void shouldTransferSavingEqualMinBalance() { // ��������� ����� �������� ������ ����������� ����� ������������ �������
        SavingAccount from = new SavingAccount(4_000, 1_000, 15_000, 20);
        SavingAccount to = new SavingAccount(4_000, 1_000, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 3_000);
        Assertions.assertEquals(1_000, from.getBalance());
        Assertions.assertEquals(7_000, to.getBalance());
    }
    @Test
    void shouldTransferCreditPositiveBalanceToSave() { // �������� ������� � �������� �� ��������� (��� � ������������� �������� �� � ����� ��������)
        CreditAccount from = new CreditAccount(4_000, 10_000, 20);
        SavingAccount to = new SavingAccount(4_000, 1_000, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 3_000);
        Assertions.assertEquals(1_000, from.getBalance());
        Assertions.assertEquals(7_000, to.getBalance());
    }
    @Test
    void shouldTransferCreditNegativeBalanceToSave() { // �������� ������� � �������� �� ��������� (�������� � ������������� �������� �� � ����� ��������)
        CreditAccount from = new CreditAccount(0, 10_000, 20);
            from.pay(500);
        SavingAccount to = new SavingAccount(4_000, 1_000, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 700);
        Assertions.assertEquals(-1_200, from.getBalance());
        Assertions.assertEquals(4_700, to.getBalance());
    }
    @Test
    void shouldTransferCreditZeroBalanceToSave() { // �������� ������� � �������� �� ��������� (�������� � ������� �������� �� � � ������������� �������� ����� ��������)
        CreditAccount from = new CreditAccount(4_000, 10_000, 20);
        SavingAccount to = new SavingAccount(4_000, 1_000, 10_000, 15);

        Bank account = new Bank();
        account.transfer(from, to, 3_000);
        Assertions.assertEquals(1_000, from.getBalance());
        Assertions.assertEquals(7_000, to.getBalance());
    }
}