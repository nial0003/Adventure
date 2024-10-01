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
            String input = ui.getInput();
            String[] command = input.split(" ", 2);

            String action = command[0];
            String parameter = (command.length > 1) ? command[1] : "";

            switch (action) {

                case "go":
                    handleMovement(parameter, player, ui); // Handle multi-word "go" commands like "go north"
                    break;

                case "north":
                case "n":
                    player.move("north", player.getCurrentRoom().getNorthRoom(), ui);
                    break;

                case "east":
                case "e":
                    player.move("east", player.getCurrentRoom().getEastRoom(), ui);
                    break;

                case "south":
                case "s":
                    player.move("south", player.getCurrentRoom().getSouthRoom(), ui);
                    break;

                case "west":
                case "w":
                    player.move("west", player.getCurrentRoom().getWestRoom(), ui);
                    break;

                case "look":
                    ui.message(player.getCurrentRoom().getDescription());
                    if (player.getCurrentRoom().getItemsOnTheGround() != null) {
                        for (Item item : player.getCurrentRoom().getItemsOnTheGround()) {
                            ui.message(item.getDescription() + " is in the room");
                        }
                    }
                    break;

                case "inventory":
                case "inv":
                case "invent":
                    player.showInventory(ui);
                    break;

                case "take":
                    if (!parameter.isEmpty()) {
                        player.pickupItem(parameter, ui);
                    } else {
                        ui.message("Specify an item to take.");
                    }
                    break;

                case "drop":
                    if (!parameter.isEmpty()) {
                        player.dropItem(parameter, ui);
                    } else {
                        ui.message("Specify an item to drop.");
                    }
                    break;


                case "help":
                    ui.displayHelp();
                    break;

                case "exit":
                    running = false;
                    break;


                default:
                    ui.message("Invalid command, please type 'help' to see valid commands.");
                    break;
            }
        }
    }

    private void handleMovement(String direction, Player player, UserInterface ui) {
        switch (direction) {
            case "north":
            case "n":
                player.move("north", player.getCurrentRoom().getNorthRoom(), ui);
                break;
            case "south":
            case "s":
                player.move("south", player.getCurrentRoom().getSouthRoom(), ui);
                break;
            case "east":
            case "e":
                player.move("east", player.getCurrentRoom().getEastRoom(), ui);
                break;
            case "west":
            case "w":
                player.move("west", player.getCurrentRoom().getWestRoom(), ui);
                break;
            default:
                ui.message("Unknown direction. Try 'north', 'south', 'east', 'west' or 'n', 's', 'e', 'w'.");
                break;
        }
    }

}
