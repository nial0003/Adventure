import java.util.Scanner;

public class Adventure {
    private UserInterface ui;
    private Player player;

    public Adventure(UserInterface ui) {
        this.ui = ui;
        this.player = new Player(ui);
    }

    public void roomNavigation() {
        boolean running = true;
        while (running) {
            System.out.print("\n\nwhat will you do?: ");
            String action = ui.getInput();

            switch (action.toLowerCase()) {
                case "north", "n", "go north" -> player.move("north", player.getCurrentRoom().getNorthRoom());
                case "east", "e", "go east" -> player.move("east", player.getCurrentRoom().getEastRoom());
                case "south", "s", "go south" -> player.move("south", player.getCurrentRoom().getSouthRoom());
                case "west", "w", "go west" -> player.move("west", player.getCurrentRoom().getWestRoom());
                case "look" -> ui.message(player.getCurrentRoom().getDescription());
                case "help" -> ui.displayHelp();
                case "exit" -> running = false;
                default -> ui.message("You've not entered an invalid command, please type help to see valid commands");
            }
        }
    }
}
