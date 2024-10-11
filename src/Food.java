public class Food extends Item {
    private int healthOrDamage;
    private String secretDescription;

    public Food(String foodName, String foodDescription, int healthOrDamage) {
        super(foodName, foodDescription, false);
        this.healthOrDamage = healthOrDamage;
    }

    //man kunne måske lave en poisonFood klasse der arvede fra food klassen
    public Food(String foodName, String foodDescription, int healthOrDamage, String secretDescription) {
        super(foodName, foodDescription, false);
        this.secretDescription = secretDescription;
        this.healthOrDamage = healthOrDamage;
    }

    public int getHealthOrDamage() {
        return healthOrDamage;
    }

    public String getSecretDescription() {
        return secretDescription;
    }
}
