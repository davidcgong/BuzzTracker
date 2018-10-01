package com.example.davidgong.donation_tracker;

public class Account {
    private static final int validPasswordLength = 7;
    private static final int validUsernameLength = 7;

    private String username;
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean validPassword(String password) {
        return password.length() > validPasswordLength;
    }

    public static boolean validUsername(String username) {
        return username.length() > validUsernameLength;
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
