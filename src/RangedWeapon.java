public class RangedWeapon extends Weapon {

    private int ammo;

    public RangedWeapon(String rangedWeaponName, String rangedWeaponDescription, int damage, int ammo) {
        super(rangedWeaponName, rangedWeaponDescription, damage, true);
        this.ammo = ammo;
    }


    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    @Override
    public boolean canUse() {
        return ammo > 0;
    }

    public int getAmmo() {
        return ammo;
    }
}
