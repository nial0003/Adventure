public class AdventureGameFlow {

    public AdventureGameFlow() {
        roomNavigation();
    }

    public void roomNavigation() {
        UserInterface ui = new UserInterface();
        Player player = new Player();
        ui.gameDescription();
        boolean running = true;
        while (running) {
            ui.messageSameLine("\n\nwhat will you do?: ");
            String input = ui.getInput();
            String[] command = input.split(" ", 2);

            String action = command[0];
            String parameter = (command.length > 1) ? command[1] : "";

            switch (action) {
                case "go" -> handleMovement(parameter, player, ui); // Handle multi-word "go" commands like "go north"

                case "north", "n", "east", "e", "south", "s", "west", "w" -> player.moveToAdjacentRoom(action, ui);

                case "inventory", "inv", "invent" -> player.showInventory(ui);
                case "look" -> {
                    ui.message(player.getCurrentRoom().getRoomDescription());
                    if (player.getCurrentRoom().getItemsOnTheGround() != null) {
                        for (Item item : player.getCurrentRoom().getItemsOnTheGround()) {
                            ui.message(item.getItemDescription() + " is in the room");
                        }
                    }
                }
                case "take" -> {
                    if (!parameter.isEmpty()) {
                        player.pickupItem(parameter, ui);
                    } else {
                        ui.message("Specify an item to take.");
                    }
                }

                case "drop" -> {
                    if (!parameter.isEmpty()) {
                        player.dropItem(parameter, ui);
                    } else {
                        ui.message("Specify an item to drop.");
                    }
                }

                case "help" -> ui.displayHelp();
                case "exit" -> running = false;
                default -> ui.message("Invalid command, please type 'help' to see valid commands.");
            }
        }
    }

    private void handleMovement(String direction, Player player, UserInterface ui) {
        switch (direction) {
            case "north", "n", "east", "e", "south", "s", "west", "w" -> player.moveToAdjacentRoom(direction, ui);
            default -> ui.message("Unknown direction. Try 'north', 'south', 'east', 'west' or 'n', 's', 'e', 'w'.");
        }
    }
}
