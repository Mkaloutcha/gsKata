package com.kata.bankAccount.domain;

import java.math.BigDecimal;

public class Account {

    private String firstName;
    private String lastName;
    private BigDecimal balance;
    private final Operations operations = new Operations();

    public Account(String firstName, String lastName, BigDecimal balance) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;

    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public BigDecimal getBalance() {

        return balance;
    }

    public void setBalance(BigDecimal balance) {

        this.balance = balance;
    }

    public Operations getOperations() {

        return operations;
    }

    public void addOperation(Operation operation) {

        this.operations.add(operation);
    }

}
