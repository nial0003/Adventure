
public class Enemy {
    private int healthPoints;
    private Weapon weapon;
    private String name;
    private String description;
    private boolean isBoss;


    public Enemy(String name, int healthPoints, Weapon weapon) {
        this.name = name;
        this.weapon = weapon;
        this.healthPoints = healthPoints;
    }

    //man kunne måske lave en boss klasse der arvede fra enemy klassen.
    public Enemy(String name, int healthPoints, Weapon weapon, String description) {
        this.name = name;
        this.weapon = weapon;
        this.healthPoints = healthPoints;
        this.description = description;
        isBoss = true;
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

    public String attackPlayer(Player player) {
        String s = "";
        player.setHealthPoint(player.getHealthPoint() - weapon.getDamage());
        if (isBoss) {
            s = description + " hit the player for " + weapon.getDamage() + " points of damage";
        } else {
            s = name + " hit the player for " + weapon.getDamage() + " points of damage";
        }
        if (player.getHealthPoint() <= 0) {
            s += "\n" + name + " has killed the player, game over!";
            return s;
        }
        return s;
    }

    public boolean isDead() {
        return healthPoints <= 0;
    }

    public String getDescription() {
        return description;
    }

    public boolean isBoss() {
        return isBoss;
    }
}
