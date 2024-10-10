public class Enemy{
    private int healthPoints;
    private Weapon weapon;
    private String name;


    public Enemy(String name, int healthPoints, Weapon weapon) {
        this.name = name;
        this.weapon = weapon;
        this.healthPoints = healthPoints;
    }

    public int getHealthPoint() {
        return healthPoints;
    }

    public String getWeaponName() {
        return weapon.getItemName();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public String getName() {
        return name;
    }

    public void setHealthPoint(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public String attackPlayer(Player player){
        String s = "";
        player.setHealthPoint(player.getHealthPoint() - weapon.getDamage());
        s = name + " hit the player for " + weapon.getDamage() + " points of damage";
        if (player.getHealthPoint() <= 0){
            s += "\n" + name + " has killed the player, game over!";
            return s;
        }
        return s;
    }

    public boolean isDead(){
        return healthPoints <= 0;
    }
}
