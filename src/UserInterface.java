import java.util.Scanner;

public class UserInterface {
    private Scanner sc;
    private AdventureCont adventureCont;

    public UserInterface() {
        sc = new Scanner(System.in);
        adventureCont = new AdventureCont();
        roomNavigation();
    }

    public void roomNavigation() {
        gameDescription();
        boolean running = true;
        while (running) {
            System.out.print("\n\nwhat will you do?: ");
            String input = sc.nextLine();
            String[] command = input.split(" ", 2);

            String action = command[0];
            String parameter = (command.length > 1) ? command[1] : "";

            switch (action) {
                case "go" -> handleMovement(parameter); // Handle multi-word "go" commands like "go north"

                case "north", "n", "east", "e", "south", "s", "west", "w" ->
                        System.out.println(adventureCont.moveToAdjacentRoom(action));

                case "inventory", "inv", "invent" -> System.out.println(adventureCont.showInventory());
                case "look" -> {
                    System.out.println(adventureCont.getCurrentRoomDescription());
                    if (adventureCont.getItemsOnTheGround() != null) {
                        System.out.println(adventureCont.getItemsOnTheGround());
                    }
                }
                case "take", "drop" -> {
                    if (!parameter.isEmpty()) {
                        if (adventureCont.doesItemExist(action,parameter)) {
                            System.out.println(adventureCont.takeOrDropItem(action, parameter));
                        }
                    } else if (action.equalsIgnoreCase("take")){
                        System.out.println("Specify an item to take.");
                    } else {
                        System.out.println("Specify and item to drop.");
                    }
                }

                case "equip", "unequip" -> {
                    if (!parameter.isEmpty()){
                        if (adventureCont.doesItemExist(action, parameter)){
                            System.out.println(adventureCont.equipOrUnequipItem(action, parameter));
                        } else {
                            System.out.println(parameter + " is not a weapon and can't be equipped.");
                        }
                    } else if (action.equalsIgnoreCase("equip")){
                        System.out.println("Specify an item to equip.");
                    } else {
                        System.out.println("Specify an item to unequip.");
                    }
                }

                case "attack" -> {
                    if (!parameter.isEmpty()){
                        System.out.println(adventureCont.playerAttack(parameter));
                    } else {
                        System.out.println(adventureCont.playerAttack("air"));
                    }
                }

                case "eat" -> System.out.println(eat(parameter));

                case "help" -> displayHelp();
                case "exit" -> running = false;
                default -> System.out.println("Invalid command, please type 'help' to see valid commands.");
            }
        }
    }

    private void handleMovement(String direction) {
        switch (direction) {
            case "north", "n", "east", "e", "south", "s", "west", "w" ->
                    System.out.println(adventureCont.moveToAdjacentRoom(direction));
            default ->
                    System.out.println("Unknown direction. Try 'north', 'south', 'east', 'west' or 'n', 's', 'e', 'w'.");
        }
    }

    public void displayHelp() {
        System.out.println("Available commands:\n" +
                "north, n, go north - move north\n" +
                "west, w, go west - move west\n" +
                "south, s, go south - move south\n" +
                "east, e, go east - move east\n" +
                "look - show description for current room\n" +
                "Take <item> - take an item from the room\n" +
                "drop <item> - drop an item in the room \n" +
                "inventory, inv, invent - display player inventory\n" +
                "equip <weapon> - equip an weapon into your hand\n" +
                "unequip <weapon> - unequip weapon back into your inventory\n" +
                "attakc <enemy> - attacks your enemy with your weapon\n"+
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

    public String eat(String parameter){
        return switch (adventureCont.isItFood(parameter)){
            case Status.ISFOOD -> adventureCont.eat(Status.ISFOOD, parameter);
            case Status.ISNOTFOOD -> adventureCont.eat(Status.ISNOTFOOD, parameter);
            default -> adventureCont.eat(Status.ISNOTFOUND, parameter);
        };
    }
}
