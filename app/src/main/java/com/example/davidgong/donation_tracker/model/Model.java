package com.example.davidgong.donation_tracker.model;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Model implements Serializable{
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
     * @param savedModel Model used to create new Model
     */
    public void loadModel(Model savedModel) {
        addAllLocations(savedModel.getLocations());
        updateAccounts(savedModel.getAccounts());
    }

    /**
     * adds account to model
     * @param username the username of the account to add
     * @param password the password of the account to add
     * @param accountType the account type of the account to add
     */
    public void addAccount(String username, String password, String accountType) {
        accounts.put(username, new Account(username, password, accountType));
    }

    /**
     * add account to the model
     * @param username the username of the account to add
     * @param password the password of the account to add
     * @param accountType the account type of the account to add
     * @param location the location the account to add can access
     */
    public void addAccount(String username, String password, String accountType, String location) {
        accounts.put(username, new Account(username, password, accountType, location));
    }

    /**
     * adds a location to the model
     * @param a model to add to model
     */
    public void addLocation(Location a){
        locations.add(a);
    }

    /**
     * returns the locations contained in this model
     * @return  the locations contained in this model
     */
    public List<Location> getLocations() {
        return locations;
    }


    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    public void addAllLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void updateAccounts(HashMap<String, Account> savedAccounts) {
        this.accounts = savedAccounts;
    }

    public boolean containsUsername(String username) {
        return accounts.containsKey(username);
    }

    public boolean isAccount(String username, String password) {
        return containsUsername(username) && accounts.get(username).getPassword().equals(password);
    }

    public boolean validPassword(String password) {
        return Account.validPassword(password);
    }

    public boolean validUsername(String username) {
        return Account.validUsername(username);
    }

    //get account type for LoginActivity
    public String getAccountType(String username) {
        return accounts.get(username).getAccountType();
    }
}
