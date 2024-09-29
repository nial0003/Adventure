import java.util.Scanner;

public class Adventure {
    private UserInterface ui;
    private Player player;

    public Adventure() {
        this.ui = new UserInterface();
        this.player = new Player();
        ui.gameDescription();
    }

    public void roomNavigation() {
        boolean running = true;
        while (running) {
            ui.messageSameLine("\n\nwhat will you do?: ");
            String action = ui.getInput();

            switch (action.toLowerCase()) {
                case "north", "n", "go north" -> player.move("north", player.getCurrentRoom().getNorthRoom(), ui);
                case "east", "e", "go east" -> player.move("east", player.getCurrentRoom().getEastRoom(), ui);
                case "south", "s", "go south" -> player.move("south", player.getCurrentRoom().getSouthRoom(), ui);
                case "west", "w", "go west" -> player.move("west", player.getCurrentRoom().getWestRoom(),ui );
                case "look" -> ui.message(player.getCurrentRoom().getDescription());
                case "help" -> ui.displayHelp();
                case "exit" -> running = false;
                default -> ui.message("You've not entered an invalid command, please type help to see valid commands");
            }
        }
    }
}
