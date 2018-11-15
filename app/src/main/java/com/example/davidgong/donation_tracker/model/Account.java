package com.example.davidgong.donation_tracker.model;

import java.io.Serializable;

public class Account implements Serializable{
    private static final int validPasswordLength = 7;
    private static final int validUsernameLength = 7;

    private String username;
    private String password;
    private String accountType;

    private String locationAccess;

    /**
     * creates a new account
     * @param username the username of this account
     * @param password the password of this account
     * @param accountType the accountType of this account
     */
    public Account(String username, String password, String accountType) {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
        this.locationAccess = null;
    }

    /**
     * creates a new account
     * @param username the username of this account
     * @param password the password of this account
     * @param accountType this account type of this account
     * @param locationAccess the location this account has access to
     */
    public Account(String username, String password, String accountType, String locationAccess) {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
        this.locationAccess = locationAccess;
    }

    /**
     * returns the username of this account
     * @return the username of this account
     */
    public String getUsername() {
        return username;
    }

    /**
     * returns the password of this account
     * @return the password of this account
     */
    public String getPassword() {
        return password;
    }

    /**
     * returns the account type of this account
     * @return the account type of this account
     */
    public String getAccountType() {return accountType;}

    /**
     * sets the username of this account
     * @param username the username of this account
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * sets password of this account
     * @param password thie password of this account
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * returns the location access of this account
     * @return the location access of this account
     */
    public String getLocationAccess() {
        return locationAccess;
    }

    /**
     * sets the location access of this account
     * @param locationAccess thie location access of this account
     */
    public void setLocationAccess(String locationAccess) {
        this.locationAccess = locationAccess;
    }

    /**
     * sets the account type of this account
     * @param accountType the account tyoe of this account
     */
    public void setAccountType(String accountType) { this.accountType = accountType; }

    /**
     * checks if password is a valid password
     * @param password the password to verify is valid
     * @return true if password is valid
     */
    public static boolean validPassword(String password) {
        return password.length() > validPasswordLength;
    }

    /**
     * checks if username is a valid username
     * @param username the username to verify is valid
     * @return returns true if username is valid
     */
    public static boolean validUsername(String username) {
        return username.length() > validUsernameLength;
    }

    /**
     * checks if accountType is a valid account type
     * @param accountType the account type to verify
     * @return returns true if Account type is valid
     */
    public static boolean validAccountType(String accountType) {
        return (accountType.equals("Location Employee") || accountType.equals("User") || accountType.equals("Admin"));
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other instanceof Account &&
                ((Account) other).getUsername().equals(username);
    }

    @Override
    public int hashCode(){
        return 3 * username.hashCode() + 7 * password.hashCode();
    }
}
