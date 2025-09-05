public class Fangs implements Weapon{
    private String name;
    private double damage;

    public Fangs(String name, double damage) {
        this.name = name;
        this.damage = damage;
    }
    @Override
    public String name() {
        return name;
    }

    @Override
    public double damage() {
        return damage;
    }

    @Override
    public String attackType() {
        return "bite";
    }

    @Override
    public double useWeapon(double health) {
        return health - damage;
    }

    @Override
    public double setWeaponDamage(double damage) {
        return this.damage = damage;
    }
}
