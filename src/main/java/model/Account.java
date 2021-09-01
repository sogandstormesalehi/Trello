package model;

import java.util.ArrayList;

public class Account {
    private static ArrayList<Account> allAccounts = new ArrayList<>();
    private String name;
    private String username;
    private String password;
    private ArrayList<Task> allTasks = new ArrayList<>();

    public Account(String name, String username, String password) {
        setName(name);
        setUsername(username);
        setPassword(password);
        allAccounts.add(this);
    }

    public static ArrayList<Account> getAllAccounts() {
        return allAccounts;
    }

    public static void setAllAccounts(ArrayList<Account> allAccounts) {
        Account.allAccounts = allAccounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    public static Account getAccountByUsername(String username) {
        for (Account account : allAccounts)
            if (account.username.equals(username)) return account;
        return null;
    }


}
