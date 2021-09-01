package controller;

import model.Account;

public class MainMenu {
    private static MainMenu singleInstance = null;

    public static MainMenu getInstance() {
        if (singleInstance == null)
            singleInstance = new MainMenu();
        return singleInstance;
    }

    public Account loggedIn;

    public void run() {
        print();
        String command = Input.scanner.nextLine();
        label:
        while (true) {
            switch (command) {
                case "checkAll":
                    ShowAllTasks.getInstance().run();
                    break;
                case "checkMine":
                    ShowMyTasks.getInstance().run();
                    break;
                case "addTask":
                    AddTaskMenu.getInstance().run();
                    break;
                case "back":
                    break label;
                default:
                    System.out.println("invalid command! try again!");
                    break;
            }
            command = Input.scanner.nextLine();
        }
        System.out.println("back to the login menu!");
    }

    private void print() {
        System.out.println("username: " + loggedIn.getUsername() + "\nfull name: " + loggedIn.getName());
        System.out.println("""
                to see all tasks type checkAll
                to see your own tasks, type checkMine
                to add a new task, type addTask
                to exit this menu, type back""");
    }


}
