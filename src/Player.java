import java.util.ArrayList;

public class Player {
    private Map map;
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private int life;
    private Weapon rightHand;

    public Player() {
        this.map = new Map();
        currentRoom = map.createMap();
        this.inventory = new ArrayList<>();
        this.life = 25;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public String getItemsOnTheGround() {
        return currentRoom.getItemsOnTheGround();
    }

    public String getCurrentRoomDescription() {
        return currentRoom.getLongRoomDescription();
    }

    private String move(String direction, Room nextRoom) {
        if (nextRoom == null) {
            return "The way " + direction + " is blocked!";
        } else {
            currentRoom = nextRoom;
            return "You went " + direction + "! \n" + currentRoom.getLongRoomDescription();
        }
    }

    public String moveToAdjacentRoom(String direction) {
        Room nextRoom = currentRoom.getAdjacentRoom(direction);
        return move(direction, nextRoom);
    }

    public Boolean doesItemExist(String action, String itemName) {
        if (action.equalsIgnoreCase("take")) {
            Item item = currentRoom.findItem(itemName);
            return item != null;
        } else if (action.equalsIgnoreCase("equip")) {
            Item weapon = findItemInInventory(itemName);
            return weapon instanceof Weapon;
        } else if (action.equalsIgnoreCase("unequip")) {
            return rightHand != null;
        } else {
            Item item = findItemInInventory(itemName);
            return item != null;
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
            String s = "There are no items in your inventory.";
            if (rightHand != null) {
                s += "\n\nYou currently have: " + rightHand.getItemName() + " equipped.";
            }
            return s;
        } else {
            String s = "These items are in your inventory:";
            for (Item item : inventory) {
                s += "\n" + item.getItemDescription();
            }
            if (rightHand != null) {
                s += "\n\nYou currently have: " + rightHand.getItemName() + " equipped.";
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

    public Enum isItFood(String foodItem) {
        Item itemInInventory = findItemInInventory(foodItem);
        Item itemOnTheGround = currentRoom.findItem(foodItem);
        if (itemInInventory != null) {
            if (itemInInventory instanceof Food) {
                return Status.ISFOOD;
            } else {
                return Status.ISNOTFOOD;
            }
        } else if (itemOnTheGround != null) {
            if (itemOnTheGround instanceof Food) {
                return Status.ISFOOD;
            } else {
                return Status.ISNOTFOOD;
            }
        } else {
            return Status.ISNOTFOUND;
        }
    }

    public String eat(Status status, String foodItem) {
        Item itemInInventory = findItemInInventory(foodItem);
        Item itemOnTheGround = currentRoom.findItem(foodItem);
        if (itemInInventory != null) {
            if (status == Status.ISFOOD) {
                if (life == 50) {
                    inventory.remove(itemInInventory);
                    return "You just ate a " + itemInInventory.getItemName() + " but were already on max HP";
                } else if (life < 50) {
                    Food food = (Food) itemInInventory;
                    life += food.getHealthOrDamage();
                    if (life > 50) {
                        life = 50;
                        return "You just ate: " + itemInInventory.getItemName() + " and is now on max HP";
                    }
                    return "You ate: " + itemInInventory.getItemName() + " and changed your life with: " + food.getHealthOrDamage() + " and is now on: " + life + "HP";
                }
            } else {
                return "You can't eat a " + itemInInventory.getItemName() + " you doughnut!";
            }
        } else if (itemOnTheGround != null) {
            if (status == Status.ISFOOD) {
                Food food = (Food) itemOnTheGround;
                if (food.getHealthOrDamage() < 0) {
                    life += food.getHealthOrDamage();
                    String s = "You've eaten poison food and taken " + food.getHealthOrDamage() + " points of damage and is now on " + life + "HP";
                    currentRoom.removeItem(itemOnTheGround);
                    return s;
                } else {
                    if (life == 50) {
                        currentRoom.removeItem(itemOnTheGround);
                        return "You just ate a " + itemOnTheGround.getItemName() + " but were already on max HP";
                    } else if (life < 50) {
                        life += food.getHealthOrDamage();
                        if (life > 50) {
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

    public String equipOrUnequipItem(String action, String item) {
        if (rightHand != null && !action.equalsIgnoreCase("unequip")){
            return "You already have " + rightHand.getItemName() + " equipped. Unequip it to equip a new weapon";
        }
        Weapon weapon = (Weapon) findItemInInventory(item);
        if (action.equalsIgnoreCase("equip")) {
            if (weapon != null && weapon.isWeapon() && rightHand == null) {
                rightHand = weapon;
                inventory.remove(weapon);
                return "You've equipped " + weapon.getItemName();
            } else if (weapon != null && !weapon.isWeapon()) {
                return weapon.getItemName() + " is not a weapon!";
            } else {
                return item + " is not in your inventory!";
            }
        } else if (action.equalsIgnoreCase("unequip")) {
            if (rightHand == null) {
                return "You don't have " + item + " equipped!";
            } else {
                inventory.add(rightHand);
                rightHand = null;
                return "You have unequipped " + item + " and put it into your inventory";
            }
        } else {
            return null;
        }
    }

    public String playerAttack(String enemy) {
        if (rightHand != null) {
            if (!enemy.equalsIgnoreCase("air")) {
                return "You attacked the " + enemy + " for " + rightHand.getDamage() + " points of damage!";
            } else {
                return "You attacked the air you doughnut!";
            }
        } else {
            return "You don't have a weapon equipped and can't attack!";
        }
    }
}
