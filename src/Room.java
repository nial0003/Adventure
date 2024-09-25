public class Room {
    private String name;
    private String description;
    private Room northRoom;
    private Room southRoom;
    private Room westRoom;
    private Room eastRoom;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
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
}
