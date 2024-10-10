public class MeleeWeapon extends Weapon {

    public MeleeWeapon(String meleeWeaponName, String meleeWeaponDescription, int damage) {
        super(meleeWeaponName, meleeWeaponDescription, damage, false);
    }

    @Override
    public boolean canUse() {
        return false;
    }

    @Override
    public int getAmmo() {
        return 0;
    }

    @Override
    public void setAmmo(int ammo) {
    }
}
