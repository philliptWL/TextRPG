public class Bow implements Weapon{
    private String name;
    private double damage;

    public Bow(String name, double damage) {
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
        return "fire";
    }
}
