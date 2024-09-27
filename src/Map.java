public class Map {
    private Room currentRoom;

    public void createMap() {
        // Instansiere alle rummene
        Room room1 = new Room("room 1", "A clearing with thick tree walls all around you except for 2 ways. \nYou can see the sky");
        Room room2 = new Room("room 2", "A stream is running through this area. There is still light above, but it's dimmer. \nThere are 2 ways");
        Room room3 = new Room("room 3", "Multiple skeletons line this area. Some are missing limbs, and multiple" +
                " bones have bite marks in them. \nThere are 2 ways");
        Room room4 = new Room("room 4", "You're in a swamp like area. You see wisps trying to lure you into the svamp " +
                "so they can trap you in the deep muddy water and consume you alive. \nThere are 2 ways");
        Room room5 = new Room("room 5", "You've arrived at the elf city! \nCongratulations!! They are unfriendly and quickly kill you.");
        Room room6 = new Room("room 6", "A Sphinx stands in the middle of the room, you must answer its riddles to pass or be killed \nThere are 2 ways");
        Room room7 = new Room("room 7", "You can hear the elves song faintly, it's beautiful! But there are man-eating plants all around you" +
                " you must navigate them to get further. \nThere are 2 ways");
        Room room8 = new Room("room 8", "You hear the elves song loudly. But you've entered a magically frozen part of the forest" +
                ", clearly the elves do not wish to be found. \nYou must move on quickly or freeze to death. \nThere are 3 ways");
        Room room9 = new Room("room 9", "You can hear the elves song faintly, it's beautiful. In the middle runs a river of lava and it's extremely hot" +
                ", you must move on quickly not to die of heat exhaustion. \nThere are 2 ways");

        room1.setEastRoom(room2);
        room1.setSoutRoom(room4);

        room2.setWestRoom(room1);
        room2.setEastRoom(room3);

        room3.setWestRoom(room2);
        room3.setSoutRoom(room6);

        room4.setNorthRoom(room1);
        room4.setSoutRoom(room7);

        room5.setSoutRoom(room8);

        room6.setNorthRoom(room3);
        room6.setSoutRoom(room9);

        room7.setNorthRoom(room4);
        room7.setEastRoom(room8);

        room8.setWestRoom(room7);
        room8.setNorthRoom(room5);
        room8.setEastRoom(room9);

        room9.setNorthRoom(room6);
        room9.setWestRoom(room8);

        currentRoom = room1;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
