package ru.netology.javaqadiplom;

/**
 * Сберегательный счёт
 * Может иметь баланс только в пределах от указанного минимального до указанного максимального включительно.
 * Не может уходить в минус (минимальный баланс не может быть отрицательным).
 * Имеет ставку - количество процентов годовых на остаток.
 */
public class SavingAccount extends Account {
    protected int minBalance;
    protected int maxBalance;

    /**
     * Создаёт новый объект сберегательного счёта с заданными параметрами.
     * Если параметры некорректны (мин. баланс больше максимального и так далее), то
     * должно выкидываться исключения вида IllegalArgumentException.
     * @param initialBalance - начальный баланс
     * @param minBalance - минимальный баланс
     * @param maxBalance - максимальный баланс
     * @param rate - неотрицательное число, ставка в процентах годовых на остаток
     */
    public SavingAccount(int initialBalance, int minBalance, int maxBalance, int rate) {
        if (rate < 0) {
            throw new IllegalArgumentException(
              "Накопительная ставка не может быть отрицательной, а у вас: " + rate
            );
        } if (minBalance < 0) { // Проверка, что минимальный баланс не может быть отрицательный #8
            throw new IllegalArgumentException(
                    "Минимальный баланс не может быть отрицательный, а у вас: " + minBalance
            );
        } if (maxBalance == 0) { // Проверка, что максимальный баланс не может быть нулевым #10
            throw new IllegalArgumentException(
                    "Минимальный баланс не может нулевым, а у вас: " + maxBalance
            );
        }
        if (initialBalance < minBalance) { // Проверка, что начальный баланс не может быть меньше минимального #11
            throw new IllegalArgumentException(
                    "Начальный баланс не может быть меньше минимального, а у вас: начальный баланс" + initialBalance + "и минимальный баланс: " + minBalance
            );
        }
        if (initialBalance > maxBalance) { // Проверка, что начальный баланс не может быть больше максимального #
            throw new IllegalArgumentException(
                    "Начальный баланс не может быть больше максимального, а у вас: начальный баланс" + initialBalance + "и максимальный баланс: " + maxBalance
            );
        }
        if (maxBalance < minBalance) { // Проверка на то, что максимальный баланс не может быть меньше минимального #7, соответственно не может быть меньше нуля #9
            throw new IllegalArgumentException(
                    "Максимальный баланс не может быть меньше минимального, а у вас минимальный баланс: " + minBalance + "а максимальный баланс: " + maxBalance
            );
        }
        this.balance = initialBalance;
        this.minBalance = minBalance;
        this.maxBalance = maxBalance;
        this.rate = rate;
    }

    /**
     * Операция оплаты с карты на указанную сумму.
     * В результате успешного вызова этого метода, баланс должен уменьшиться
     * на сумму покупки. Если же операция может привести к некорректному
     * состоянию счёта (например, баланс может уйти в минус), то операция должна
     * завершиться вернув false и ничего не поменяв на счёте.
     * @param amount - сумма покупки
     * @return true если операция прошла успешно, false иначе.
     */
    @Override
    public boolean pay(int amount) {
        if (amount <= 0) {
            return false;
        }
        if (balance - amount >= minBalance) { // Проверка что баланс не выйдет за рамки минимума после покупки #4, #5
            balance = balance - amount;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Операция пополнения карты на указанную сумму.
     * В результате успешного вызова этого метода, баланс должен увеличиться
     * на сумму покупки. Если же операция может привести к некорректному
     * состоянию счёта, то операция должна
     * завершиться вернув false и ничего не поменяв на счёте.
     * @param amount - сумма пополнения
     * @return true если операция прошла успешно, false иначе.
     */
    @Override
    public boolean add(int amount) {
        if (amount <= 0) {
            return false;
        }
        if (balance + amount <= maxBalance) {  // Проверка что баланс не выйдет за рамки максимума после пополнения #6
            balance = balance + amount;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Операция расчёта процентов на остаток счёта при условии, что
     * счёт не будет меняться год. Сумма процентов приводится к целому
     * числу через отбрасывание дробной части (так и работает целочисленное деление).
     * Пример: если на счёте 200 рублей, то при ставке 15% ответ должен быть 30.
     * @return
     */
    @Override
    public int yearChange() {
        return balance / 100 * rate;
    }

    public int getMinBalance() {
        return minBalance;
    }

    public int getMaxBalance() {
        return maxBalance;
    }
}
