package com.example.davidgong.donation_tracker;

public class Account {
    private static final int validPasswordLength = 7;
    private static final int validUsernameLength = 7;

    private String username;
    private String password;
    private String accountType;

    public Account(String username, String password, String accountType) {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountType() {return accountType;}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountType(String accountType) { this.accountType = accountType; }

    public static boolean validPassword(String password) {
        return password.length() > validPasswordLength;
    }

    public static boolean validUsername(String username) {
        return username.length() > validUsernameLength;
    }

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
