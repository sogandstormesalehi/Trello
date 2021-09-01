package controller;

import model.Task;

public class ShowMyTasks {
    private static ShowMyTasks singleInstance = null;

    public static ShowMyTasks getInstance() {
        if (singleInstance == null)
            singleInstance = new ShowMyTasks();
        return singleInstance;
    }

    public void run() {
        print();
        String command = Input.scanner.nextLine();
        String output;
        while (!command.equals("back")) {
            if (command.matches("\\d+")) {
                output = ShowAllTasks.getInstance().handleID(command);
            } else {
                output = ShowAllTasks.getInstance().handleTitle(command);
            }
            if (output == null) {
                System.out.println("back to the main menu!");
                break;
            }
            command = Input.scanner.nextLine();
        }
        if (command.equals("back")) System.out.println("back to the main menu!");
    }

    private void print() {
        StringBuilder toPrint = new StringBuilder();
        for (Task task : MainMenu.getInstance().loggedIn.getAllTasks())
            toPrint.append(task.getID()).append(": ").append(task.getTitle()).append("\n");
        System.out.println(toPrint.toString());
        System.out.println("you can type the ID or the Task's title in order to go into the task's page\n" +
                "to exit this menu type back");
    }
}
