public interface Weapon {
    String name();
    double damage();
    String attackType();
    double useWeapon(double health);
    double setWeaponDamage(double damage);
}
