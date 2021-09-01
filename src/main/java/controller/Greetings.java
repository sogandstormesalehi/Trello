package controller;

public class Greetings {
    private static Greetings singleInstance = null;

    public static Greetings getInstance() {
        if (singleInstance == null)
            singleInstance = new Greetings();
        return singleInstance;
    }

    public void run() {
        print();
        String command = Input.scanner.nextLine();
        while (!command.equals("cancel")) {
            if (command.equals("sign up"))
                RegisterMenu.getInstance().run();
            else if (command.equals("login"))
                LoginMenu.getInstance().run();
            else System.out.println("invalid command! try again!");
            command = Input.scanner.nextLine();
        }
    }

    private void print() {
        System.out.println("""
                welcome!
                Trello helps teams move work forward.
                Collaborate, manage projects, and reach new productivity peaks. 
                From high rises to the home office, the way your team works is unique—accomplish it all with Trello.
                To sign up, type sign up
                Already have an account? type login
                If you want to exit the program, type cancel""");
    }
}
