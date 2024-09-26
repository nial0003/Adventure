import java.util.Scanner;

public class UserInterface {
    private Scanner sc;

    public UserInterface(){
        sc = new Scanner(System.in);
    }

    public String getInput(){
        return sc.nextLine();
    }

    public void displayHelp(){
        System.out.println("Available commands:\n" +
                "north, n, go north - move north\n" +
                "west, w, go west - move west\n" +
                "south, s, go south - move south\n" +
                "east, e, go east - move east\n" +
                "look - show description for current room\n" +
                "help - shows available commands\n" +
                "exit - exits the program");
    }
}
