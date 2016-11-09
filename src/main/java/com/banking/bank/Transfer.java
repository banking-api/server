package com.banking.bank;

import com.banking.bank.exception.CustomerNotOwnerException;
import com.banking.bank.exception.InsufficentFundsException;
import com.banking.bank.exception.InvalidAmountException;

/**
 * Created by graham on 09/11/16.
 */
public class Transfer extends Transaction {

    public Transfer(Customer fromCustomer,
                    Account fromAccount,
                    Customer toCustomer,
                    Account toAccount,
                    double amount) throws CustomerNotOwnerException,
                                            InsufficentFundsException,
                                            InvalidAmountException {
        super(Type.DEBIT, amount);
        transfer(fromCustomer, fromAccount, toCustomer, toAccount, amount);
    }

    private void transfer(Customer fromCustomer,
                          Account fromAccount,
                          Customer toCustomer,
                          Account toAccount,
                          double amount) throws CustomerNotOwnerException,
                                                InsufficentFundsException,
                                                InvalidAmountException {
        if (fromCustomer.isOwner(fromAccount)) {
            fromAccount.withdraw(fromCustomer, amount);
            toAccount.lodge(toCustomer, amount);
        }
    }
}