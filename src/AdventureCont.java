public class AdventureCont {
    private Player player = new Player("The Player");

    public String moveToAdjacentRoom(String direction){
        return player.moveToAdjacentRoom(direction);
    }

    public String showInventory(){
        return player.showInventory();
    }

    public String getCurrentRoomLongDescription(){
        return player.getCurrentRoomLongDescription();
    }

    public String getItemsOnTheGround(){
        return player.getItemsOnTheGround();
    }

    public String takeOrDropItem(String action, String parameter){
        return player.takeOrDropItem(action, parameter);
    }

    public boolean doesItemExist(String action, String itemName){
        return player.doesItemExist(action, itemName);
    }

    public Enum isItFood(String foodItem){
        return player.isItFood(foodItem);
    }

    public String eat(Status status, String foodItem){
        return player.eat(status,foodItem);
    }

    public String equipOrUnequipItem(String action, String item){
        return player.equipOrUnequipItem(action, item);
    }

    public String playerHitEnemy(String enemy){
        return player.playerHitEnemy(enemy);
    }
}


