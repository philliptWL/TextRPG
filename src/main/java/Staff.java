public class Staff implements Weapon {
    private String name;
    private double damage;

    public Staff(String name, double damage) {
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
        return "cast";
    }
}
