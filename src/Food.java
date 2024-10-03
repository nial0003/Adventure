public class Food extends Item{
    private int healthOrDamage;

    public Food(String foodName, String foodDescription, int healthOrDamage){
        super(foodName, foodDescription);
        this.healthOrDamage =healthOrDamage;
    }

    public int getHealthOrDamage() {
        return healthOrDamage;
    }
}
