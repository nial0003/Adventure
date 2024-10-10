public abstract class Weapon extends Item {
    private int damage;
    boolean isRanged;

    public Weapon(String nameOfWeapon, String descriptionOfWeapon, int damage, Boolean isRanged) {
        super(nameOfWeapon, descriptionOfWeapon, true);
        this.damage = damage;
        this.isRanged = isRanged;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isRanged(){
        return isRanged;
    }

    public abstract boolean canUse();

    public abstract int getAmmo();
    public abstract void setAmmo(int ammo);
}
