import java.util.ArrayList;

public class Player {
    private Map map;
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private int life;

    public Player() {
        this.map = new Map();
        currentRoom = map.createMap();
        this.inventory = new ArrayList<>();
        this.life = 25;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public String getItemsOnTheGround(){
        return currentRoom.getItemsOnTheGround();
    }

    public String getCurrentRoomDescription(){
        return currentRoom.getRoomDescription();
    }

    private String move(String direction, Room nextRoom) {
        if (nextRoom == null) {
            return "The way " + direction + " is blocked!";
        } else {
            currentRoom = nextRoom;
            return "You went " + direction + "! \n" + currentRoom.getRoomDescription();
        }
    }

    public String moveToAdjacentRoom(String direction) {
        Room nextRoom = currentRoom.getAdjacentRoom(direction);
        return move(direction, nextRoom);
    }

    public Boolean doesItemExist(String action, String itemName){
        if (action.equalsIgnoreCase("take")){
            Item item = currentRoom.findItem(itemName);
            if (item == null){
                return false;
            } else {
                return true;
            }
        } else {
            Item item = findItemInInventory(itemName);
            if (item == null){
                return false;
            } else {
                return true;
            }
        }
    }

    public String takeOrDropItem(String action, String itemName) {
        if (action.equalsIgnoreCase("take")) {
            if (currentRoom.transferItemToPlayer(itemName, inventory)) {
                return "You picked up the " + itemName;
            } else {
                return "Item: " + itemName + " not found in this room";
            }
        } else if (action.equalsIgnoreCase("drop")) {
            Item item = findItemInInventory(itemName);
            if (item != null) {
                inventory.remove(item);
                currentRoom.addItem(item);
                return "You dropped the " + item.getItemName() + " on the ground";
            } else {
                return "You don't have the item '" + itemName + "' in your inventory";
            }
        } else {
            return null;
        }
    }

    public String showInventory() {
        if (inventory.isEmpty()) {
            return "There are no items in your inventory.";
        } else {
            String s = "These items are in your inventory:";
            for (Item item : inventory) {
                s += "\n" + item.getItemDescription();
            }
            return s;
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

    public int getLife() {
        return life;
    }

    public Enum isItFood(String foodItem){
        Item itemInInventory = findItemInInventory(foodItem);
        Item itemOnTheGround = currentRoom.findItem(foodItem);
        if (itemInInventory != null) {
            if (itemInInventory instanceof Food){
                return Status.ISFOOD;
            } else {
                return Status.ISNOTFOOD;
            }
        } else if (itemOnTheGround != null) {
            if (itemOnTheGround instanceof Food){
                return Status.ISFOOD;
            } else {
                return Status.ISNOTFOOD;
            }
        } else {
            return Status.ISNOTFOUND;
        }
    }

    public String eat(Status status, String foodItem){
        Item itemInInventory = findItemInInventory(foodItem);
        Item itemOnTheGround = currentRoom.findItem(foodItem);
        if (itemInInventory != null){
            if (status == Status.ISFOOD){
                if (life == 50){
                    inventory.remove(itemInInventory);
                    return "You just ate a " + itemInInventory.getItemName() + " but were already on max HP";
                } else if (life < 50){
                    Food food = (Food) itemInInventory;
                    life += food.getHealthOrDamage();
                    if (life > 50){
                        life = 50;
                        return "You just ate: " + itemInInventory.getItemName() + " and is now on max HP";
                    }
                    return "You ate: " + itemInInventory.getItemName() + " and changed your life with: " + food.getHealthOrDamage() + " and is now on: " + life + "HP";
                }
            } else {
                return "You can't eat a " + itemInInventory.getItemName() + " you doughnut!";
            }
        } else if (itemOnTheGround != null) {
            if (status == Status.ISFOOD){
                Food food = (Food) itemOnTheGround;
                if (food.getHealthOrDamage() < 0){
                    life += food.getHealthOrDamage();
                    String s = "You've eaten poison food and taken " + food.getHealthOrDamage() + " points of damage and is now on " + life + "HP";
                    currentRoom.removeItem(itemOnTheGround);
                    return s;
                } else {
                    if (life == 50){
                        currentRoom.removeItem(itemOnTheGround);
                        return "You just ate a " + itemOnTheGround.getItemName() + " but were already on max HP";
                    } else if (life < 50){
                        life += food.getHealthOrDamage();
                        if (life > 50){
                            life = 50;
                            return "You just ate: " + itemOnTheGround.getItemName() + " and is now on max HP";
                        }
                        return "You ate: " + itemOnTheGround.getItemName() + " and changed your life with: " + food.getHealthOrDamage() + " and is now on: " + life + "HP";
                    }
                }
            } else {
                return "You can't eat a " + itemOnTheGround.getItemName() + " you doughnut!";
            }
        }
        return "No such item exist in your inventory or in the room.";
    }
}
