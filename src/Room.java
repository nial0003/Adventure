import java.util.ArrayList;

public class Room {
    private String roomName;
    private String longRoomDescription;
    private String shortRoomDescription;
    private Room northRoom;
    private Room southRoom;
    private Room westRoom;
    private Room eastRoom;
    private ArrayList<Item> itemsOnTheGround;
    private ArrayList<Enemy> enemiesInTheRoom;
    private boolean hasBeenVisited = false;

    public Room(String name, String longRoomDescription, String shortRoomDescription) {
        this.roomName = name;
        this.longRoomDescription = longRoomDescription;
        this.shortRoomDescription = shortRoomDescription;
        this.itemsOnTheGround = new ArrayList<>();
        this.enemiesInTheRoom = new ArrayList<>();
    }

    public void setNorthRoom(Room northRoom) {
        this.northRoom = northRoom;
        if(northRoom.southRoom != this)
        {
            northRoom.setSouthRoom(this);
        }
    }

    public void setSouthRoom(Room southRoom) {
        this.southRoom = southRoom;
        if(southRoom.northRoom != this)
        {
            southRoom.setNorthRoom(this);
        }
    }

    public void setWestRoom(Room westRoom) {
        this.westRoom = westRoom;
        if (westRoom.eastRoom != this){
            westRoom.setEastRoom(this);
        }
    }

    public void setEastRoom(Room eastRoom) {
        this.eastRoom = eastRoom;
        if (eastRoom.westRoom != this){
            eastRoom.setWestRoom(this);
        }
    }

    public String getLongRoomDescription() {
        return longRoomDescription;
    }

    public String getRoomName() {
        return roomName;
    }

    public void addItem(Item item) {
        itemsOnTheGround.add(item);
    }

    public void addEnemy(Enemy enemy){
        enemiesInTheRoom.add(enemy);
    }

    public void removeEnemy(Enemy enemy){
        enemiesInTheRoom.remove(enemy);
    }

    public void removeItem(Item item) {
        itemsOnTheGround.remove(item);
    }

    public String getItemsOnTheGround() {
        if (itemsOnTheGround != null){
            String onTheGround = "\nOn the ground you find:";
            for (Item item : itemsOnTheGround){
                onTheGround += "\n" + item.getItemDescription();
            }
            return onTheGround;
        }
        return null;
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

    public Boolean transferItemToPlayer(String itemName, ArrayList<Item> inventory){
        Item item = findItem(itemName);
        if (item != null){
            inventory.add(item);
            itemsOnTheGround.remove(item);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Enemy> getEnemiesInTheRoom() {
        return enemiesInTheRoom;
    }

    public Enemy findEnemy(String enemyName){
        for (Enemy enemy : enemiesInTheRoom){
            if (enemy.getName().equalsIgnoreCase(enemyName))
            {
                return enemy;
            }
        }
        return null;
    }

    public void setHasBeenVisited(boolean hasBeenVisited) {
        this.hasBeenVisited = hasBeenVisited;
    }

    public boolean getHasBeenVisited(){
        return hasBeenVisited;
    }

    public String getShortRoomDescription() {
        return shortRoomDescription;
    }

    public String printEnemiesInTheRoom(){
        if (enemiesInTheRoom != null) {
            String enemyInTheRoom = "\nEnemies in the room: ";
            for (Enemy enemy : enemiesInTheRoom) {
                if (enemy.isBoss()){
                    enemyInTheRoom +="\n" + enemy.getDescription();
                }else {
                    enemyInTheRoom += "\nA " + enemy.getName();
                }
            }
            return enemyInTheRoom;
        }
        return null;
    }
}
