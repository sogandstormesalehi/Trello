package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task {
    private static ArrayList<Task> allTasks = new ArrayList<>();
    private static int idBuilder = 1;
    private int ID = 0;
    private final Account backUpAccount = new Account("", "", "");
    private Account user = backUpAccount;
    private String title = "";
    private LocalDateTime deadline = LocalDateTime.of(2017, 2, 13, 15, 56);

    public Task(String title) {
        this.setTitle(title);
        this.setID();
        allTasks.add(this);
    }

    public static ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    public static void setAllTasks(ArrayList<Task> allTasks) {
        Task.allTasks = allTasks;
    }

    public int getID() {
        return ID;
    }

    public void setID() {
        this.ID = idBuilder++;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public static Task getTaskByID(int ID) {
        for (Task task : allTasks)
            if (task.ID == ID) return task;
        return null;
    }

    public static Task getTaskByTitle(String title) {
        for (Task task : allTasks)
            if (task.title.equals(title)) return task;
        return null;
    }

    public static void removeTask(Task task) {
        allTasks.remove(task);
        idBuilder = --idBuilder;
    }
}

