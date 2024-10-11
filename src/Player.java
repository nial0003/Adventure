import java.util.ArrayList;

public class Player {
    private Map map;
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private int healthPoint;
    private Weapon rightHand;
    private String name;

    public Player(String name) {
        this.map = new Map();
        currentRoom = map.createMap();
        this.inventory = new ArrayList<>();
        this.healthPoint = 25;
        this.name = name;
    }

    public String getItemsOnTheGround() {
        return currentRoom.getItemsOnTheGround();
    }

    public String getCurrentRoomLongDescription() {
        return currentRoom.getLongRoomDescription();
    }

    public String getName() {
        return name;
    }

    private String move(String direction, Room nextRoom) {
        String s = "";
        if (nextRoom == null) {
            return "The way " + direction + " is blocked!";
        } else if (!nextRoom.getHasBeenVisited()) {
            currentRoom = nextRoom;
            currentRoom.setHasBeenVisited(true);
            s = "You went " + direction + "! \n\n" + currentRoom.getRoomName() + "\n" + currentRoom.getLongRoomDescription();
            if (!currentRoom.getEnemiesInTheRoom().isEmpty()) {
                s += currentRoom.printEnemiesInTheRoom();
            }
            return s;
        } else {
            currentRoom = nextRoom;
            s = "You went " + direction + "! \n" + currentRoom.getRoomName() + "\n" + currentRoom.getShortRoomDescription();
            if (!currentRoom.getEnemiesInTheRoom().isEmpty()) {
                s += currentRoom.printEnemiesInTheRoom();
            }
            return s;
        }
    }

    public String moveToAdjacentRoom(String direction) {
        Room nextRoom = currentRoom.getAdjacentRoom(direction);
        return move(direction, nextRoom);
    }

    public boolean doesItemExist(String action, String itemName) {
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
                if (rightHand.isRanged()) {
                    s += "\n you have " + rightHand.getAmmo() + " ammo left for your " + rightHand.getItemName();
                }
            }
            return s;
        } else {
            String s = "These items are in your inventory:";
            for (Item item : inventory) {
                s += "\n" + item.getItemDescription();
                if (item instanceof Weapon weapon) {
                    if (weapon.isRanged()) {
                        s += ", you have " + weapon.getAmmo() + " ammo left for it";
                    }
                }
            }
            if (rightHand != null) {
                s += "\n\nYou currently have: " + rightHand.getItemName() + " equipped.";
                if (rightHand.isRanged()) {
                    s += "\nyou have " + rightHand.getAmmo() + " ammo left for your " + rightHand.getItemName();
                }
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

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
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
                Food food = (Food) itemInInventory;
                if (food.getHealthOrDamage() < 0) {
                    healthPoint += food.getHealthOrDamage();
                    String s = "You've eaten " + food.getSecretDescription() + " and taken " + food.getHealthOrDamage() + " points of damage and is now on " + healthPoint + "HP";
                    inventory.remove(itemInInventory);
                    return s;
                } else if (healthPoint == 50) {
                    inventory.remove(itemInInventory);
                    return "You just ate a " + itemInInventory.getItemName() + " but were already on max HP";
                } else if (healthPoint < 50) {
                    healthPoint += food.getHealthOrDamage();
                    if (healthPoint > 50) {
                        healthPoint = 50;
                        return "You just ate: " + itemInInventory.getItemName() + " and is now on max HP";
                    }
                    return "You ate: " + itemInInventory.getItemName() + " and gained " + food.getHealthOrDamage() + " life and is now on: " + healthPoint + "HP";
                }
            } else {
                return "You can't eat a " + itemInInventory.getItemName() + " you doughnut!";
            }
        } else if (itemOnTheGround != null) {
            if (status == Status.ISFOOD) {
                Food food = (Food) itemOnTheGround;
                if (food.getHealthOrDamage() < 0) {
                    healthPoint += food.getHealthOrDamage();
                    String s = "You've eaten " + food.getSecretDescription() + " and taken " + food.getHealthOrDamage() + " points of damage and is now on " + healthPoint + "HP";
                    currentRoom.removeItem(itemOnTheGround);
                    return s;
                } else if (healthPoint == 50) {
                    currentRoom.removeItem(itemOnTheGround);
                    return "You just ate a " + itemOnTheGround.getItemName() + " but were already on max HP";
                } else if (healthPoint < 50) {
                    healthPoint += food.getHealthOrDamage();
                    if (healthPoint > 50) {
                        healthPoint = 50;
                        return "You just ate: " + itemOnTheGround.getItemDescription() + " and is now on max HP";
                    }
                    return "You ate: " + itemOnTheGround.getItemName() + " and gained " + food.getHealthOrDamage() + " life and is now on: " + healthPoint + "HP";
                }
            } else {
                return "You can't eat a " + itemOnTheGround.getItemName() + " you doughnut!";
            }
        }
        return "No such item exist in your inventory or in the room.";
    }

    public String equipOrUnequipItem(String action, String item) {
        if (rightHand != null && action.equalsIgnoreCase("equip")) {
            return "You already have " + rightHand.getItemName() + " equipped. Unequip it to equip a new weapon";
        }
        Weapon weapon = (Weapon) findItemInInventory(item);
        if (action.equalsIgnoreCase("equip") && weapon != null && weapon.isWeapon() && rightHand == null) {
            rightHand = weapon;
            inventory.remove(weapon);
            return "You've equipped " + weapon.getItemName();
        } else if (action.equalsIgnoreCase("unequip")) {
            if (item.equalsIgnoreCase(rightHand.getItemName())) {
                inventory.add(rightHand);
                String s = "You have unequipped " + rightHand.getItemName() + " and put it into your inventory";
                rightHand = null;
                return s;
            } else {
                return "You don't have " + item + " equipped";
            }
        } else {
            return null;
        }
    }

    public String playerHitEnemy(String enemyName) {
        //Finder enemy hvis player har givet korrekt navn. Hvis ikke de har givet korrekt navn men enemy eksitere i rummet
        //sætter vi enemy til den første enemy i vores arrayliste.
        Enemy enemy = currentRoom.findEnemy(enemyName);
        if (enemy == null && !currentRoom.getEnemiesInTheRoom().isEmpty()) {
            enemy = currentRoom.getEnemiesInTheRoom().getFirst();
        }

        if (playerShootEnemy() && enemy != null) {
            String s = "";
            if (enemy.getName().equalsIgnoreCase(enemyName)) {
                enemy.setHealthPoint(enemy.getHealthPoint() - rightHand.getDamage());
                rightHand.setAmmo(rightHand.getAmmo() - 1);
                s = "You shot the " + enemyName + " for " + rightHand.getDamage() + " points of damage and have " + rightHand.getAmmo() + " ammo left";
                return isEnemyDead(enemy, s);
            } else {
                enemy.setHealthPoint(enemy.getHealthPoint() - rightHand.getDamage());
                rightHand.setAmmo(rightHand.getAmmo() - 1);
                s = "You shot the closest enemy to you, " + enemy.getName() + ",  for " + rightHand.getDamage() +
                        " points of damage and have " + rightHand.getAmmo() + " ammo left";
                return isEnemyDead(enemy, s);
            }
        }

        if (rightHand != null) {
            if (enemy != null) {
                String s = "";
                if (enemy.getName().equalsIgnoreCase(enemyName)) {
                    enemy.setHealthPoint(enemy.getHealthPoint() - rightHand.getDamage());
                    s = "You hit the " + enemy.getName() + " for " + rightHand.getDamage() + " points of damage!";
                    return isEnemyDead(enemy, s);
                } else {
                    enemy.setHealthPoint(enemy.getHealthPoint() - rightHand.getDamage());
                    s = "You hit the closest enemy to you, " + enemy.getName() + ",  for " + rightHand.getDamage() + " points of damage!";
                    return isEnemyDead(enemy, s);
                }
            } else {
                if (playerShootEnemy()) {
                    rightHand.setAmmo(rightHand.getAmmo() - 1);
                    return "You shot the air and have " + rightHand.getAmmo() + " ammo left you doughnut!";
                } else if (!playerShootEnemy() && rightHand.isRanged()) {
                    return "You don't have anymore ammo left for your weapon!";
                }
                return "You attacked the air you doughnut!";
            }
        }
        return "You don't have a weapon equipped and can't attack";
    }

    private String isEnemyDead(Enemy enemy, String s) {
        if (enemy.isDead()) {
            s += "\n" + enemy.getName() + " has died and dropped " + enemy.getWeaponName();
            currentRoom.addItem(enemy.getWeapon());
            currentRoom.removeEnemy(enemy);
            return s;
        }
        s += "\n" + enemy.attackPlayer(this);
        return s;
    }

    private boolean playerShootEnemy() {
        if (rightHand != null && rightHand.canUse()) {
            return rightHand.canUse();
        }
        return false;
    }

    public boolean playerIsDead() {
        return healthPoint <= 0;
    }
}
