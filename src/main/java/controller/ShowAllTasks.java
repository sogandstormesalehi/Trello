package controller;


import model.Task;

public class ShowAllTasks {
    private static ShowAllTasks singleInstance = null;

    public static ShowAllTasks getInstance() {
        if (singleInstance == null)
            singleInstance = new ShowAllTasks();
        return singleInstance;
    }

    public void run() {
        print();
        String command = Input.scanner.nextLine();
        String output;
        while (!command.equals("back")) {
            if (command.matches("\\d+")) {
                output = handleID(command);
            } else {
                output = handleTitle(command);
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
        for (Task task : Task.getAllTasks())
            toPrint.append(task.getID()).append(": ").append(task.getTitle()).append("\n");
        System.out.println(toPrint.toString());
        System.out.println("you can type the ID or the Task's title in order to go into the task's page\n" +
                "to exit this menu type back");
    }

    public String handleID(String ID) {
        int id = Integer.parseInt(ID);
        String command = "";
        while (Task.getTaskByID(id) == null) {
            System.out.println("task with this id doesn't exist! try again!");
            command = Input.scanner.nextLine();
            if (command.equals("back")) break;
            id = Integer.parseInt(command);
        }
        if (command.equals("back")) return null;
        TaskMenu.getInstance().run(Task.getTaskByID(id));
        return "successful";
    }

    public String handleTitle(String title) {
        String command = title;
        while (Task.getTaskByTitle(command) == null && !command.equals("back")) {
            System.out.println("task with this title doesn't exist! try again!");
            command = Input.scanner.nextLine();
        }
        if (command.equals("back")) return null;
        TaskMenu.getInstance().run(Task.getTaskByTitle(command));
        return "successful";
    }
}
