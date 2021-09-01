import controller.*;
import model.Account;
import model.Task;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AllTests {
    @Test
    public void registerCheck() {
        InputStream backup = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("prometheus\r\nreDeath\r\n444Marine*444Marine\r\n".getBytes());
        ByteArrayOutputStream outputStream = getByteArrayOutputStream(backup, input);
        RegisterMenu.getInstance().run();
        Assertions.assertEquals("""
                you're in the register menu now!
                first please type the username you want to use, then press enter
                then give us your full name, then press enter
                and at last, enter your password twice with a * character between them, then press enter
                to exit this menu, type 'back'\r
                account created successfully!\r
                """, outputStream.toString());
        Account.setAllAccounts(new ArrayList<>());
    }

    @Test
    public void loginDoesntExistCheck() {
        InputStream backup = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("prometheus\r\nback\r\n".getBytes());
        ByteArrayOutputStream outputStream = getByteArrayOutputStream(backup, input);
        LoginMenu.getInstance().run();
        Assertions.assertEquals("""
                you're in the login menu now!
                first type your username, then press enter
                then type your password
                to exit this menu, type back\r
                account doesnt exist! try again!\r
                back to the greeting menu!\r
                """, outputStream.toString());
    }

    @Test
    public void deadlineTest() {
        Task task = new Task("The Hanged Man Rusts");
        InputStream backup = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("deadline\r\n2043-12-04 04:04:04\r\n".getBytes());
        getByteArrayOutputStream(backup, input);
        TaskMenu.getInstance().setDeadline(task);
        Assertions.assertEquals("2043-12-04T04:04:04", task.getDeadline().toString());
        Task.setAllTasks(new ArrayList<>());
        Task.removeTask(task);
    }

    @Test
    public void setUserTest() {
        Account oneEye = new Account("Cinder's Song", "Holder", "duh");
        Task task = new Task("The Hanged Man Rusts");
        InputStream backup = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("Holder\r\nyes\r\nback\r\n".getBytes());
        getByteArrayOutputStream(backup, input);
        TaskMenu.getInstance().run(task);
        Assertions.assertEquals(oneEye, task.getUser());
        Task.setAllTasks(new ArrayList<>());
        Account.setAllAccounts(new ArrayList<>());
        Task.removeTask(task);
    }

    @Test
    public void mainMenuTest() {
        MainMenu.getInstance().loggedIn = new Account("One Eye Jack", "Old King Cole", "duh");
        InputStream backup = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("help\r\nback\r\n".getBytes());
        ByteArrayOutputStream outputStream = getByteArrayOutputStream(backup, input);
        MainMenu.getInstance().run();
        Assertions.assertEquals("""
                username: Old King Cole
                full name: One Eye Jack\r
                to see all tasks type checkAll
                to see your own tasks, type checkMine
                to add a new task, type addTask
                to exit this menu, type back\r
                invalid command! try again!\r
                back to the login menu!\r
                """, outputStream.toString());
        Account.setAllAccounts(new ArrayList<>());
    }

    @Test
    public void showMyTasksTest() {
        Task spacePirate = new Task("Drunk Space Pirate");
        spacePirate.setDeadline(LocalDateTime.of(2017, 2, 13, 15, 56));
        Account oneEye = new Account("One Eyed Jack", "Old King Cole", "duh");
        oneEye.getAllTasks().add(spacePirate);
        MainMenu.getInstance().loggedIn = oneEye;
        InputStream backup = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("1\r\nback\r\nback\r\n".getBytes());
        ByteArrayOutputStream outputStream = getByteArrayOutputStream(backup, input);
        ShowMyTasks.getInstance().run();
        Assertions.assertEquals("""
                1: Drunk Space Pirate
                \r
                you can type the ID or the Task's title in order to go into the task's page
                to exit this menu type back\r
                #id: 1 #title: Drunk Space Pirate #user:  #deadline: 2017-02-13T15:56\r
                if you want to set a deadline type deadline
                if you want to assign this task to someone, type their username
                to exit this menu, type back\r
                back to the previous menu!\r
                back to the main menu!\r
                """, outputStream.toString());
        Task.removeTask(spacePirate);
        MainMenu.getInstance().loggedIn = null;
    }

    @Test
    public void showAllTasksID() {
        Task spacePirate = new Task("Drunk Space Pirate");
        spacePirate.setDeadline(LocalDateTime.of(2017, 2, 13, 15, 56));
        Account oneEye = new Account("One Eyed Jack", "Old King Cole", "duh");
        oneEye.getAllTasks().add(spacePirate);
        MainMenu.getInstance().loggedIn = oneEye;
        InputStream backup = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("1\r\nback\r\nback\r\n".getBytes());
        ByteArrayOutputStream outputStream = getByteArrayOutputStream(backup, input);
        ShowAllTasks.getInstance().run();
        Assertions.assertEquals("""
                1: Drunk Space Pirate
                \r
                you can type the ID or the Task's title in order to go into the task's page
                to exit this menu type back\r
                #id: 1 #title: Drunk Space Pirate #user:  #deadline: 2017-02-13T15:56\r
                if you want to set a deadline type deadline
                if you want to assign this task to someone, type their username
                to exit this menu, type back\r
                back to the previous menu!\r
                back to the main menu!\r
                """, outputStream.toString());
        Task.removeTask(spacePirate);
        MainMenu.getInstance().loggedIn = null;
    }

    @Test
    public void showAllTasksTitle() {
        Task spacePirate = new Task("Drunk Space Pirate");
        spacePirate.setDeadline(LocalDateTime.of(2017, 2, 13, 15, 56));
        Account oneEye = new Account("One Eyed Jack", "Old King Cole", "duh");
        oneEye.getAllTasks().add(spacePirate);
        MainMenu.getInstance().loggedIn = oneEye;
        InputStream backup = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("Drunk Space Pirate\r\nback\r\nback\r\n".getBytes());
        ByteArrayOutputStream outputStream = getByteArrayOutputStream(backup, input);
        ShowAllTasks.getInstance().run();
        Assertions.assertEquals("""
                1: Drunk Space Pirate
                \r
                you can type the ID or the Task's title in order to go into the task's page
                to exit this menu type back\r
                #id: 1 #title: Drunk Space Pirate #user:  #deadline: 2017-02-13T15:56\r
                if you want to set a deadline type deadline
                if you want to assign this task to someone, type their username
                to exit this menu, type back\r
                back to the previous menu!\r
                back to the main menu!\r
                """, outputStream.toString());
        Task.removeTask(spacePirate);
    }

    @Test
    public void addTaskMenuTest() {
        InputStream backup = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("pellinore and the beast\r\nback\r\n".getBytes());
        getByteArrayOutputStream(backup, input);
        AddTaskMenu.getInstance().run();
        Assertions.assertFalse(Task.getAllTasks().isEmpty());
        Task beast = Task.getTaskByTitle("pellinore and the beast");
        Task.removeTask(beast);
    }

    @Test
    public void greetingsTest() {
        InputStream backup = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("somewhere else\r\ncancel\r\n".getBytes());
        getByteArrayOutputStream(backup, input);
        Greetings.getInstance().run();
    }

    @Test
    public void loginTest() {
        Account theHierophant = new Account("Avalon", "Golfridian", "SiegeSeat");
        InputStream backup = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("Golfridian\r\nSiegeSeat\r\nback\r\nback\n".getBytes());
        getByteArrayOutputStream(backup, input);
        LoginMenu.getInstance().run();
        Assertions.assertEquals(MainMenu.getInstance().loggedIn, theHierophant);
        MainMenu.getInstance().loggedIn = null;
        Account.getAllAccounts().remove(theHierophant);
    }

    @Test
    public void wrongPassword() {
        Account theHierophant = new Account("Avalon", "Golfridiane", "Siege");
        InputStream backup = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("Golfridiane\r\nSiegeSeat\r\nback\r\n".getBytes());
        ByteArrayOutputStream outputStream = getByteArrayOutputStream(backup, input);
        LoginMenu.getInstance().run();
        Assertions.assertEquals("""
                you're in the login menu now!
                first type your username, then press enter
                then type your password
                to exit this menu, type back\r
                wrong password! try again!\r
                back to the greeting menu!\r
                """, outputStream.toString());
        MainMenu.getInstance().loggedIn = null;
        Account.getAllAccounts().remove(theHierophant);
    }

    @Test
    public void passwordStrength() {
        InputStream backup = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("prometheus\r\nreDeath\r\nMarine*Marine\r\nback\r\n".getBytes());
        ByteArrayOutputStream outputStream = getByteArrayOutputStream(backup, input);
        RegisterMenu.getInstance().run();
        Assertions.assertEquals("""
                you're in the register menu now!
                first please type the username you want to use, then press enter
                then give us your full name, then press enter
                and at last, enter your password twice with a * character between them, then press enter
                to exit this menu, type 'back'\r
                your password is not strong enough! try again!\r
                """, outputStream.toString());
        Account.setAllAccounts(new ArrayList<>());
    }

    @Test
    public void IdTest() {
        Task spacePirate = new Task("Drunk Space Pirates");
        spacePirate.setDeadline(LocalDateTime.of(2017, 2, 13, 15, 56));
        Account oneEye = new Account("One Eyed Jackie", "Old King Colie", "duh");
        oneEye.getAllTasks().add(spacePirate);
        MainMenu.getInstance().loggedIn = oneEye;
        InputStream backup = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("100\r\nback\r\nback\r\n".getBytes());
        ByteArrayOutputStream outputStream = getByteArrayOutputStream(backup, input);
        ShowMyTasks.getInstance().run();
        Assertions.assertEquals("""
                1: Drunk Space Pirates
                \r
                you can type the ID or the Task's title in order to go into the task's page
                to exit this menu type back\r
                task with this id doesn't exist! try again!\r
                back to the main menu!\r
                """, outputStream.toString());
        Task.removeTask(spacePirate);
        MainMenu.getInstance().loggedIn = null;
    }

    @Test
    public void mainTest1() {
        MainMenu.getInstance().loggedIn = new Account("Two Eye Jack", "Old Kingdom Cole", "duh");
        InputStream backup = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("checkMine\r\nback\r\nback\r\n".getBytes());
        ByteArrayOutputStream outputStream = getByteArrayOutputStream(backup, input);
        MainMenu.getInstance().run();
        Assertions.assertEquals("""
                username: Old Kingdom Cole
                full name: Two Eye Jack\r
                to see all tasks type checkAll
                to see your own tasks, type checkMine
                to add a new task, type addTask
                to exit this menu, type back\r
                \r
                you can type the ID or the Task's title in order to go into the task's page
                to exit this menu type back\r
                back to the main menu!\r
                back to the login menu!\r
                """, outputStream.toString());
        Account.setAllAccounts(new ArrayList<>());
    }

    @Test
    public void mainTest2() {
        MainMenu.getInstance().loggedIn = new Account("Two Eye Jack", "Old Kingdom Cole", "duh");
        InputStream backup = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("checkAll\r\nback\r\nback\r\n".getBytes());
        ByteArrayOutputStream outputStream = getByteArrayOutputStream(backup, input);
        MainMenu.getInstance().run();
        Assertions.assertEquals("""
                username: Old Kingdom Cole
                full name: Two Eye Jack\r
                to see all tasks type checkAll
                to see your own tasks, type checkMine
                to add a new task, type addTask
                to exit this menu, type back\r
                \r
                you can type the ID or the Task's title in order to go into the task's page
                to exit this menu type back\r
                back to the main menu!\r
                back to the login menu!\r
                """, outputStream.toString());
        Account.setAllAccounts(new ArrayList<>());
    }

    @Test
    public void mainTest3() {
        MainMenu.getInstance().loggedIn = new Account("Two Eye Jackson", "Old Kingdom Colin", "duh");
        InputStream backup = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("addTask\r\nback\r\nback\r\n".getBytes());
        ByteArrayOutputStream outputStream = getByteArrayOutputStream(backup, input);
        MainMenu.getInstance().run();
        Assertions.assertEquals("""
                username: Old Kingdom Colin
                full name: Two Eye Jackson\r
                to see all tasks type checkAll
                to see your own tasks, type checkMine
                to add a new task, type addTask
                to exit this menu, type back\r
                please type the title of your new task
                to exit this menu, type back\r
                back to the main menu!\r
                back to the login menu!\r
                """, outputStream.toString());
        Account.setAllAccounts(new ArrayList<>());
    }

    @NotNull
    private ByteArrayOutputStream getByteArrayOutputStream(InputStream backup, ByteArrayInputStream input) {
        System.setIn(input);
        Input.resetScanner();
        System.setIn(backup);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        return outputStream;
    }
}
