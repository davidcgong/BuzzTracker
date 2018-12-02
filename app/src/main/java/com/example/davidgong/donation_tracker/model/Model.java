package com.example.davidgong.donation_tracker.model;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Model implements Serializable {
    //Singleton Model
    public String locationFile = "model";

    private static final Model instance = new Model();
    private HashMap<String, Account> accounts;
    private List<Location> locations;


    private Model() {
        accounts = new HashMap<>();
    }

    public static Model getInstance() {
        return instance;
    }

    /**
     * creates model from saved model
     *
     * @param savedModel Model used to create new Model
     */
    public void loadModel(Model savedModel) {
        addAllLocations(savedModel.getLocations());
        updateAccounts(savedModel.getAccounts());
    }

    /**
     * adds account to model
     *
     * @param username    the username of the account to add
     * @param password    the password of the account to add
     * @param accountType the account type of the account to add
     */
    public void addAccount(String username, String password, String accountType) {
        accounts.put(username, new Account(username, password, accountType));
    }

    /**
     * add account to the model
     *
     * @param username    the username of the account to add
     * @param password    the password of the account to add
     * @param accountType the account type of the account to add
     * @param location    the location the account to add can access
     */
    public void addAccount(String username, String password, String accountType, String location) {
        accounts.put(username, new Account(username, password, accountType, location));
    }

    /**
     * adds a location to the model
     *
     * @param a model to add to model
     */
    public void addLocation(Location a) {
        locations.add(a);
    }

    /**
     * returns the locations contained in this model
     *
     * @return the locations contained in this model
     */
    public List<Location> getLocations() {
        return locations;
    }

    /**
     * returns the accounts contained in this model
     *
     * @return the accounts contained in this model
     */
    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    /**
     * adds all locations in locations to the model
     *
     * @param locations the locations to add to the model
     */
    public void addAllLocations(List<Location> locations) {
        this.locations = locations;
    }

    /**
     * initializes the accounts using savedAccounts
     *
     * @param savedAccounts the accounts used for initialization
     */
    public void updateAccounts(HashMap<String, Account> savedAccounts) {
        this.accounts = savedAccounts;
    }

    /**
     * returns true if the model contains username
     *
     * @param username the username being looked for
     * @return true if the model contains username
     */
    public boolean containsUsername(String username) {
        return accounts.containsKey(username);
    }

    /**
     * returns true if user with username and password exist in this  model
     *
     * @param username the username of the user being looked for in this model
     * @param password the password of the user being looked for in this model
     * @return true if user with username and password exist in this  model
     */
    public boolean isAccount(String username, String password) {
        return containsUsername(username) && accounts.get(username).getPassword().equals(password);
    }

    /**
     * returns true if password is a valid password
     *
     * @param password the password to verify is valid
     * @return true if password is a valid password
     */
    public boolean validPassword(String password) {
        return Account.validPassword(password);
    }

    /**
     * returns true if username is a valid username
     *
     * @param username the username to verify is valid
     * @return true if username is a valid username
     */
    public boolean validUsername(String username) {
        return Account.validUsername(username);
    }

    /**
     * returns the account type of the account with username equal to username
     *
     * @param username the username of the account whos account type will be returned
     * @return the account type of the account with username equal to username
     */
    public String getAccountType(String username) {
        return accounts.get(username).getAccountType();
    }
}
