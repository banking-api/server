package com.banking.bank;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Credit")
public class Lodgement extends Transaction {
    public Lodgement() {
        super();
    }

    public Lodgement(Account account, double amount) {
        super(amount, account);
    }

    @Override
    protected void doTransaction() {
        getAccount().updateBalance(getAccount().getBalance() + getAmount());
        setPostBalance(getAccount().getBalance());
    }
}
