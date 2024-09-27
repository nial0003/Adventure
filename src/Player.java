public class Player {
    private Map map;
    private Room currentRoom;

    public Player(){
        this.map = new Map();
        map.createMap();
        currentRoom = map.getCurrentRoom();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void move(String direction, Room nextRoom, UserInterface ui) {
        if (nextRoom == null) {
            ui.message("The way " + direction + " is blocked!");
        } else {
            currentRoom = nextRoom;
            ui.message("You went " + direction + "! \n" + currentRoom.getDescription());
        }
    }
}
