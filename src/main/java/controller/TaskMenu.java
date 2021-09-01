package controller;

import model.Account;
import model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskMenu {
    private static TaskMenu singleInstance = null;

    public static TaskMenu getInstance() {
        if (singleInstance == null)
            singleInstance = new TaskMenu();
        return singleInstance;
    }

    public void run(Task task) {
        print(task);
        String command = Input.scanner.nextLine();
        String output;
        while (!command.equals("back")) {
            if (command.equals("deadline")) {
                output = setDeadline(task);
            } else {
                output = setUser(command, task);
            }
            if (output == null) {
                System.out.println("back to the previous menu!");
                break;
            }
            command = Input.scanner.nextLine();
        }
        if (command.equals("back")) System.out.println("back to the previous menu!");
    }

    private void print(Task task) {
        String toPrint = "#id: " + task.getID() + " #title: " +
                task.getTitle() + " #user: " + task.getUser().getUsername() + " #deadline: " + task.getDeadline().toString();
        System.out.println(toPrint);
        System.out.println("""
                if you want to set a deadline type deadline
                if you want to assign this task to someone, type their username
                to exit this menu, type back""");
    }

    public String setDeadline(Task task) {
        System.out.println("type the deadline you want to set with the format yyyy-MM-dd HH:mm:ss");
        String input = Input.scanner.nextLine();
        while (!input.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}") && !input.equals("back")) {
            System.out.println("the format you entered is not valid! try again!");
            input = Input.scanner.nextLine();
        }
        if (input.equals("back")) return null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime deadline = LocalDateTime.parse(input, format);
        task.setDeadline(deadline);
        System.out.println("deadline set successfully!");
        return "successful";
    }

    public String setUser(String username, Task task) {
        String validUsername = username;
        while (Account.getAccountByUsername(validUsername) == null && !validUsername.equals("back")) {
            System.out.println("no account with this username exists! try again!");
            validUsername = Input.scanner.nextLine();
        }
        if (validUsername.equals("back")) return null;
        Account user = Account.getAccountByUsername(validUsername);
        System.out.println("the user you chose is " + user.getName() + "! if you wish to continue type yes\n" +
                "if you want to choose someone else type that person's username\n" +
                "if you want to go back to the task page press enter");
        String input = Input.scanner.nextLine();
        if (input.equals("yes")) {
            user.getAllTasks().add(task);
            task.setUser(user);
            return "successful";
        } else if (input.equals("\n")) return "successful";
        else return setUser(input, task);
    }
}
