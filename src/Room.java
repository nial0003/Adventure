import java.util.ArrayList;

public class Room {
    private String roomName;
    private String roomDescription;
    private Room northRoom;
    private Room southRoom;
    private Room westRoom;
    private Room eastRoom;
    private ArrayList<Item> itemsOnTheGround;

    public Room(String name, String description) {
        this.roomName = name;
        this.roomDescription = description;
        this.itemsOnTheGround = new ArrayList<>();
    }

    public void setNorthRoom(Room northRoom) {
        this.northRoom = northRoom;
    }

    public Room getNorthRoom() {
        return northRoom;
    }

    public void setSoutRoom(Room soutRoom) {
        this.southRoom = soutRoom;
    }

    public Room getSouthRoom() {
        return southRoom;
    }

    public void setWestRoom(Room westRoom) {
        this.westRoom = westRoom;
    }

    public Room getWestRoom() {
        return westRoom;
    }

    public void setEastRoom(Room eastRoom) {
        this.eastRoom = eastRoom;
    }

    public Room getEastRoom() {
        return eastRoom;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public String getRoomName() {
        return roomName;
    }

    public void addItem(Item item) {
        itemsOnTheGround.add(item);
    }

    public void removeItem(Item item) {
        itemsOnTheGround.remove(item);
    }

    public ArrayList<Item> getItemsOnTheGround() {
        return itemsOnTheGround;
    }

    public Item findItem(String itemName) {
        for (Item item : itemsOnTheGround) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public Room getAdjacentRoom(String direction){
        return switch (direction) {
            case "north", "n" -> northRoom;
            case "east", "e" -> eastRoom;
            case "south", "s" -> southRoom;
            case "west", "w" -> westRoom;
            default -> null;
        };
    }
}
