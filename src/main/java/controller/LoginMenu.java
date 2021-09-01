package controller;

import model.Account;

public class LoginMenu {
    private static LoginMenu singleInstance = null;

    public static LoginMenu getInstance() {
        if (singleInstance == null)
            singleInstance = new LoginMenu();
        return singleInstance;
    }

    public void run() {
        print();
        String command = Input.scanner.nextLine();
        if (command.equals("back")) {
            System.out.println("back to the greeting menu!");
            return;
        }
        String username = handleUsername(command);
        if (username.equals("back")) {
            System.out.println("back to the greeting menu!");
            return;
        }
        String password = handlePassword(username);
        if (password.equals("back")) {
            System.out.println("back to the greeting menu!");
            return;
        }
        System.out.println("login successful!");
        MainMenu.getInstance().loggedIn = Account.getAccountByUsername(username);
        MainMenu.getInstance().run();
        run();
    }

    private void print() {
        System.out.println("""
                you're in the login menu now!
                first type your username, then press enter
                then type your password
                to exit this menu, type back""");
    }

    private String handlePassword(String username) {
        String password = Input.scanner.nextLine();
        Account account = Account.getAccountByUsername(username);
        while (!password.equals(account.getPassword()) && !password.equals("back")) {
            System.out.println("wrong password! try again!");
            password = Input.scanner.nextLine();
        }
        return password;
    }

    private String handleUsername(String username) {
        String validUsername = username;
        while (Account.getAccountByUsername(validUsername) == null && !validUsername.equals("back")) {
            System.out.println("account doesnt exist! try again!");
            validUsername = Input.scanner.nextLine();
        }
        return validUsername;
    }

}
