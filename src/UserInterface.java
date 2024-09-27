import java.util.Scanner;

public class UserInterface {
    private Scanner sc;

    public UserInterface() {
        sc = new Scanner(System.in);
    }

    public String getInput() {
        return sc.nextLine();
    }

    public void displayHelp() {
        System.out.println("Available commands:\n" +
                "north, n, go north - move north\n" +
                "west, w, go west - move west\n" +
                "south, s, go south - move south\n" +
                "east, e, go east - move east\n" +
                "look - show description for current room\n" +
                "help - shows available commands\n" +
                "exit - exits the program");
    }

    public void gameDescription() {
        System.out.println("Welcome to the game." +
                "\nYou're an adventurer called Brutus! You've heard of a magical forest" +
                "\nin which, in the middle, exists a magical elf society filled with magical artifacts and treasures." +
                "\nYou've walked for what feels like hours through thick brush and brambles until you finally reach" +
                "\na clearing. But it feels as though the magic forest has closed off the way from which you came as" +
                "\nthick trees, that you can't push through, have suddenly sprouted all around you except for 2 ways." +
                "\nYou're sure you're on the right track!");
    }

    public void message(String message){
        System.out.println(message);
    }
}
