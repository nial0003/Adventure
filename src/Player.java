import java.util.ArrayList;

public class Player {
    private Map map;
    private Room currentRoom;
    private ArrayList<Item> inventory;

    public Player() {
        this.map = new Map();
        currentRoom = map.createMap();
        this.inventory = new ArrayList<>();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    private void move(String direction, Room nextRoom, UserInterface ui) {
        if (nextRoom == null) {
            ui.message("The way " + direction + " is blocked!");
        } else {
            currentRoom = nextRoom;
            ui.message("You went " + direction + "! \n" + currentRoom.getRoomDescription());
        }
    }

    public void moveToAdjacentRoom(String direction, UserInterface ui){
        Room nextRoom = currentRoom.getAdjacentRoom(direction);
        move(direction, nextRoom, ui);
    }

    public void pickupItem(String itemName, UserInterface ui) {
        Item item = currentRoom.findItem(itemName);
        if (item != null) {
            inventory.add(item);
            currentRoom.removeItem(item);
            ui.message("You picked up: " + item.getItemName());
        } else {
            ui.message("Item '" + itemName + "' not found in this room.");
        }
    }

    public void dropItem(String itemName, UserInterface ui) {
        Item item = findItemInInventory(itemName);
        if (item != null) {
            inventory.remove(item);
            currentRoom.addItem(item);
            ui.message("You dropped: " + item.getItemName());
        } else {
            ui.message("you don't have the item '" + itemName + "' in your inventory.");
        }
    }

    public void showInventory(UserInterface ui) {
        ui.message("These items are in your inventory:");
        for (Item item : inventory) {
            ui.message(item.getItemDescription());
        }
    }

    private Item findItemInInventory(String itemName) {
        for (Item item : inventory) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
}
