package com.kata.bankAccount.service;

import com.kata.bankAccount.Exception.AmountTooBigForWithdrawalException;
import com.kata.bankAccount.Exception.MinDepositAllowedException;
import com.kata.bankAccount.Exception.NotEnoughMoneyException;
import com.kata.bankAccount.domain.Account;
import com.kata.bankAccount.domain.Operation;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    /**
     * save money for an account
     *
     * @param account
     * @param amount
     * @throws MinDepositAllowedException
     */
    public void depositMoney(Account account, BigDecimal amount) throws MinDepositAllowedException;

    /**
     * withdraw money from an account
     *
     * @param account
     * @param amount
     * @throws AmountTooBigForWithdrawalException
     * @throws NotEnoughMoneyException
     */
    public void withdrawalMoney(Account account, BigDecimal amount) throws AmountTooBigForWithdrawalException, NotEnoughMoneyException;

    /**
     * retrieve operations from an account
     *
     * @param account
     * @return
     */
    public List<Operation> getOperations(Account account);

}
