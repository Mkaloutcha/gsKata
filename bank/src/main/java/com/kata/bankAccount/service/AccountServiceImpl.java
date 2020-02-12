package com.kata.bankAccount.service;

import com.kata.bankAccount.Exception.AmountTooBigForWithdrawalException;
import com.kata.bankAccount.Exception.MinDepositAllowedException;
import com.kata.bankAccount.Exception.NotEnoughMoneyException;
import com.kata.bankAccount.domain.Account;
import com.kata.bankAccount.domain.Operation;
import com.kata.bankAccount.domain.OperationType;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class AccountServiceImpl implements AccountService {

    private static final BigDecimal MAXDEPOSIT = new BigDecimal(10);
    private static final BigDecimal MAXWITHDRAWAL = new BigDecimal(2000);

    public void depositMoney(Account account, BigDecimal amount) throws MinDepositAllowedException {

        if (amount.compareTo(MAXDEPOSIT) < 0) {
            throw new MinDepositAllowedException("The minimum deposit allowed is 10");
        }

        account.setBalance(account.getBalance().add(amount));
        saveOperation(account, amount, OperationType.DEPOSIT);

    }

    public void withdrawalMoney(Account account, BigDecimal amount) throws AmountTooBigForWithdrawalException, NotEnoughMoneyException {

        BigDecimal res = account.getBalance().subtract(amount);

        if (res.compareTo(new BigDecimal(0)) < 0) {
            throw new NotEnoughMoneyException("Not enough money");
        }

        if (amount.compareTo(MAXWITHDRAWAL) > 0) {
            throw new AmountTooBigForWithdrawalException("The maximum withdrawal authorized is 2000");
        }

        account.setBalance(res);
        saveOperation(account, amount, OperationType.WITHDRAWAL);

    }

    private void saveOperation(Account account, BigDecimal amount, OperationType type) {

        Operation operation = new Operation(type, Calendar.getInstance().getTime(), amount, account.getBalance());
        account.addOperation(operation);
    }

    public List<Operation> getOperations(Account account) {

        List<Operation> operationList = account.getOperations().getItems();
        operationList.sort(Comparator.comparing(Operation::getType));

        return operationList;
    }

}
