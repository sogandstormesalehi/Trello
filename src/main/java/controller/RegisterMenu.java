package controller;

import model.Account;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterMenu {

    private static RegisterMenu singleInstance = null;

    public static RegisterMenu getInstance() {
        if (singleInstance == null)
            singleInstance = new RegisterMenu();
        return singleInstance;
    }

    private final Pattern passwordFormat = Pattern.compile("^\\d{1,3}[A-Z][a-z]{5,10}$");

    public void run() {
        print();
        String command = Input.scanner.nextLine();
        if (command.equals("back")) return;
        String validUsername = checkUsername(command);
        if (validUsername.equals("back")) return;
        String fullName = Input.scanner.nextLine();
        if (fullName.equals("back")) return;
        String password = Input.scanner.nextLine();
        if (password.equals("back")) return;
        password = checkPassword(password);
        if (password == null) return;
        createAccount(validUsername, fullName, password);
    }

    private void print() {
        System.out.println("""
                you're in the register menu now!
                first please type the username you want to use, then press enter
                then give us your full name, then press enter
                and at last, enter your password twice with a * character between them, then press enter
                to exit this menu, type 'back'""");
    }

    private void createAccount(String username, String fullName, String password) {
        new Account(fullName, username, password);
        System.out.println("account created successfully!");
    }

    private String checkUsername(String username) {
        String validUsername = username;
        while (Account.getAccountByUsername(validUsername) != null) {
            System.out.println("an account with this username already exists, please enter something else");
            validUsername = Input.scanner.nextLine();
            if (validUsername.equals("back")) break;
        }
        return validUsername;
    }

    private String checkPassword(String password) {
        String validPassword = getValidPassword(password);
        String[] passwords = validPassword.split("\\*");
        passwords = equalityCheck(passwords);
        if (passwords == null) return null;
        return strengthCheck(passwords);
    }

    private String getValidPassword(String password) {
        String validPassword = password;
        while (!validPassword.contains("*") && !validPassword.equals("back")) {
            System.out.println("you should enter your passwords with a * character between");
            validPassword = Input.scanner.nextLine();
        }
        return validPassword;
    }

    private String strengthCheck(String[] passwords) {
        String validForm = "";
        String validPassword = passwords[0];
        Matcher matcher = passwordFormat.matcher(validPassword);
        while (!matcher.find() && !validForm.equals("back")) {
            System.out.println("your password is not strong enough! try again!");
            validForm = Input.scanner.nextLine();
            passwords = validForm.split("\\*");
            validPassword = passwords[0];
            matcher = passwordFormat.matcher(validPassword);
        }
        if (validForm.equals("back")) return null;
        return validPassword;
    }

    private String[] equalityCheck(String[] passwords) {
        String valid = "";
        while (!passwords[0].equals(passwords[1]) && !valid.equals("back")) {
            System.out.println("your passwords are not equal! try again!");
            valid = Input.scanner.nextLine();
            passwords = valid.split("\\*");
        }
        if (valid.equals("back")) return null;
        return passwords;
    }
}
