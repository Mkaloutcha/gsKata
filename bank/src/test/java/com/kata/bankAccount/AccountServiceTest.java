package com.kata.bankAccount;

import com.kata.bankAccount.Exception.AmountTooBigForWithdrawalException;
import com.kata.bankAccount.Exception.MinDepositAllowedException;
import com.kata.bankAccount.Exception.NotEnoughMoneyException;
import com.kata.bankAccount.domain.Account;
import com.kata.bankAccount.domain.Operation;
import com.kata.bankAccount.domain.OperationType;
import com.kata.bankAccount.service.AccountService;
import com.kata.bankAccount.service.AccountServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class AccountServiceTest {

    AccountService accountService = null;

    @Before
    public void setUp() {

        accountService = new AccountServiceImpl();
    }

    @Test
    public void shouldDepositMoney() throws Exception {

        // Given
        Account account = new Account("firstName", "lastName", new BigDecimal(500));

        // When
        accountService.depositMoney(account, new BigDecimal(200));

        // Then
        Assertions.assertThat(account.getBalance()).isEqualTo(new BigDecimal(700));
    }

    @Test
    public void shouldRetrieveMoney() throws Exception {

        // Given
        Account account = new Account("firstName", "lastName", new BigDecimal(500));

        // When
        accountService.withdrawalMoney(account, new BigDecimal(200));

        // Then
        Assertions.assertThat(account.getBalance()).isEqualTo(new BigDecimal(300));
    }

    @Test
    public void shouldGetOperations() throws Exception {

        // Given
        Account account = new Account("firstName", "lastName", new BigDecimal(0));

        // When 
        accountService.depositMoney(account, new BigDecimal(200));
        accountService.depositMoney(account, new BigDecimal(600));
        accountService.depositMoney(account, new BigDecimal(800));
        accountService.withdrawalMoney(account, new BigDecimal(300));

        // Then
        Assertions.assertThat(account.getOperations().getItems()).hasSize(4);
        Assertions.assertThat(account.getBalance()).isEqualTo(new BigDecimal(1300));
    }

    @Test
    public void shouldThrowMinimumDepositExceptionWhenFailsForMinimumDeposit() throws Exception {

        // Given
        Account account = new Account("user1", "last1", new BigDecimal(500));

        //When Then
        Assertions.assertThatThrownBy(() -> accountService.depositMoney(account, new BigDecimal(2)))
            .isInstanceOf(MinDepositAllowedException.class)
            .hasMessage("The minimum deposit allowed is 10");
    }

    @Test
    public void shouldThrowNotEnoughMoneyExceptionWhenFailsRetrieveMoney() throws Exception {

        // Given
        Account account = new Account("user1", "last1", new BigDecimal(500));

        //When Then
        Assertions.assertThatThrownBy(() -> accountService.withdrawalMoney(account, new BigDecimal(700)))
            .isInstanceOf(NotEnoughMoneyException.class)
            .hasMessage("Not enough money");
    }

    @Test
    public void shouldThrowAmountTooBigForWithdrawalExceptionWhenRetrieveMoreThan2500() throws Exception {

        // Given
        Account account = new Account("user1", "last1", new BigDecimal(5000));

        // When Then
        Assertions.assertThatThrownBy(() -> accountService.withdrawalMoney(account, new BigDecimal(3000)))
            .isInstanceOf(AmountTooBigForWithdrawalException.class)
            .hasMessage("The maximum withdrawal authorized is 2000");
    }
    
    @Test
    public void shouldGetOperationsSortedByType() throws Exception {
        
        // Given
        Account account = new Account("firstName", "lastName", new BigDecimal(0));
        accountService.depositMoney(account, new BigDecimal(1200));
        accountService.withdrawalMoney(account, new BigDecimal(300));
        accountService.withdrawalMoney(account, new BigDecimal(600));
        accountService.depositMoney(account, new BigDecimal(800));
        
        // When
        List<Operation> operations = accountService.getOperations(account);

        // Then
        Assertions.assertThat(operations.get(0).getType()).isEqualTo(OperationType.DEPOSIT);
        Assertions.assertThat(operations.get(1).getType()).isEqualTo(OperationType.DEPOSIT);
        Assertions.assertThat(operations.get(2).getType()).isEqualTo(OperationType.WITHDRAWAL);
        Assertions.assertThat(operations.get(3).getType()).isEqualTo(OperationType.WITHDRAWAL);
    }

}
