public class Sword implements Weapon{
    private String name;
    private double damage;

    public Sword(String name, int damage) {
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
        return "swing";
    }

    @Override
    public double useWeapon(double health) {
        return health - damage;
    }
}
