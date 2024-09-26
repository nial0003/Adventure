public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        Adventure adventure = new Adventure(ui);
        System.out.println("Welcome to the game.\nYou're an adventurer called Brutus! You've heard of a magical forest" +
                "\nin which, in the middle, exists a magical elf society filled with magical artifacts and treasures." +
                "\nYou've walked for what feels like hours through thick brush and brambles until you finally reach" +
                "\na clearing. But it feels as though the magic forest has closed off the way from which you came as" +
                "\nthick trees, that you can't push through, have suddenly sprouted all around you except for 2 ways." +
                "\nYou're sure you're on the right track!");
        adventure.roomNavigation();
    }
}
