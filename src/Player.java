public class Player {
    private UserInterface ui;
    private Map map;
    private Room currentRoom;

    public Player(UserInterface ui){
        this.ui = ui;
        this.map = new Map();
        map.createMap();
        currentRoom = map.getCurrentRoom();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void move(String direction, Room nextRoom) {
        if (nextRoom == null) {
            ui.message("The way " + direction + " is blocked!");
        } else {
            currentRoom = nextRoom;
            ui.message("You went " + direction + "! \n" + currentRoom.getDescription());
        }
    }
}
