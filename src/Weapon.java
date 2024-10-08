public class Weapon extends Item {
    private int damage;
    boolean isRanged;

    public Weapon(String nameOfWeapon, String descriptionOfWeapon, int damage, Boolean isRanged) {
        super(nameOfWeapon, descriptionOfWeapon, true);
        this.damage = damage;
        this.isRanged = isRanged;
    }

    @Override
    public String getItemName() {
        return super.getItemName();
    }

    public int getDamage() {
        return damage;
    }
}
