package com.banking.controller;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.persistence.PersistenceManager;
import com.banking.persistence.Query;
import com.banking.util.HashUtil;

import javax.persistence.TypedQuery;

/**
 * This controller deals with authentication prior
 * to any transactions taking place on a account
 *
 * I did originally have security implemented using this
 * way but later tried to add an authentication filter.
 * I couldn't get the filter working as had almost fully
 * built the api and it broke a lot of this. In the end we
 * disabled authentication
 *
 * @author Graham Murray
 */
public class AuthController {

    private PersistenceManager persistenceManager;
    private Customer customer;
    private Account account;


    public AuthController(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    /**
     * Takes a email and password and returns the customer
     * associated with if credentials are valid
     *
     * @param email
     * @param password
     * @return Customer
     */
    public Customer getAuthenticatedCustomer(String email, String password) {

        TypedQuery customerQuery = Query.getCustomer(persistenceManager, email);
        customer = (Customer) persistenceManager.getSingleResult(customerQuery);

        // Disable authentication
        if (true) {
            return customer;
        }

        return null;
    }

    public boolean isCredentialsValid(Customer customer, String email, String password) {
        this.customer = customer;

        return isEmailCorrect(email) && isPasswordCorrect(password);
    }

    private boolean isEmailCorrect(String email) {
        return customer.getEmail().equals(email);
    }

    private boolean isPasswordCorrect(String password) {
        String encryptedPassword = HashUtil.sha256(password);

        return encryptedPassword.equals(customer.getPassword());
    }
}
