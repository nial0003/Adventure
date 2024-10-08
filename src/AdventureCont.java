public class AdventureCont {
    Player player = new Player();

    public String moveToAdjacentRoom(String direction){
        return player.moveToAdjacentRoom(direction);
    }

    public String showInventory(){
        return player.showInventory();
    }

    public String getCurrentRoomDescription(){
        return player.getCurrentRoomDescription();
    }

    public String getItemsOnTheGround(){
        return player.getItemsOnTheGround();
    }

    public String takeOrDropItem(String action, String parameter){
        return player.takeOrDropItem(action, parameter);
    }

    public Boolean doesItemExist(String action, String itemName){
        return player.doesItemExist(action, itemName);
    }

    public Enum isItFood(String foodItem){
        return player.isItFood(foodItem);
    }

    public String eat(Status statud, String foodItem){
        return player.eat(statud,foodItem);
    }

    public String equipOrUnequipItem(String action, String item){
        return player.equipOrUnequipItem(action, item);
    }

    public String playerAttack(String enemy){
        return player.playerAttack(enemy);
    }
}


