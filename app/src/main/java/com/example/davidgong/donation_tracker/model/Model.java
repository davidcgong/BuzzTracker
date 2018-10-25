package com.example.davidgong.donation_tracker.model;

import java.util.HashMap;
import java.util.List;

public class Model {
    //Singleton Model
    private static final Model instance = new Model();
    private HashMap<String, Account> accounts;
    private List<Location> locations;

    private Model() {
        accounts = new HashMap<>();
    }

    public static Model getInstance() {
        return instance;
    }

    public void addAccount(String username, String password, String accountType) {
        accounts.put(username, new Account(username, password, accountType));
    }

    public void addAccount(String username, String password, String accountType, String location) {
        accounts.put(username, new Account(username, password, accountType, location));
    }

    public void addLocation(Location a){
        locations.add(a);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void addAllLocations(List<Location> locations) {
        this.locations = locations;
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
