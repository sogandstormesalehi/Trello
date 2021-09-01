package controller;

import model.Task;

public class AddTaskMenu {
    private static AddTaskMenu singleInstance = null;

    public static AddTaskMenu getInstance() {
        if (singleInstance == null)
            singleInstance = new AddTaskMenu();
        return singleInstance;
    }

    public void run() {
        print();
        String command = Input.scanner.nextLine();
        while (!command.equals("back")) {
            createTask(command);
            command = Input.scanner.nextLine();
        }
        System.out.println("back to the main menu!");
    }

    private void print() {
        System.out.println("please type the title of your new task\n" +
                "to exit this menu, type back");
    }

    private void createTask(String title) {
        System.out.println("task added successfully!");
        new Task(title);
    }
}
