import java.util.ArrayList;

public class Room {
    private String name;
    private String description;
    private Room northRoom;
    private Room southRoom;
    private Room westRoom;
    private Room eastRoom;
    private ArrayList<Item> itemsOnTheGround;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
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
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
}
