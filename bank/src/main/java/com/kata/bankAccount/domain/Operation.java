package com.kata.bankAccount.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Operation implements Comparable<Operation> {

    private OperationType type;
    private Date date;
    private BigDecimal amount;
    private BigDecimal balance;

    public Operation(OperationType type, Date date, BigDecimal amount, BigDecimal balance) {

        super();
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public OperationType getType() {

        return type;
    }

    public void setType(OperationType operationType) {

        this.type = operationType;
    }

    public Date getDate() {

        return date;
    }

    public void setDate(Date date) {

        this.date = date;
    }

    public BigDecimal getAmount() {

        return amount;
    }

    public void setAmount(BigDecimal amount) {

        this.amount = amount;
    }

    public BigDecimal getBalance() {

        return balance;
    }

    public void setBalance(BigDecimal balance) {

        this.balance = balance;
    }

    public int compareTo(Operation o) {
        // TODO Auto-generated method stub
        return this.getDate().compareTo(o.getDate());
    }

}
