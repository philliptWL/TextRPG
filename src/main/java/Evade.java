public class Evade implements Ability {
    private final String name = "Evade";
    private final double rate = 1.0;

    @Override
    public String type() {
        return "evasion";
    }

    @Override
    public int cost() {
        return 30;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getDamage() {
        return 0;
    }

    @Override
    public double getHeal() {
        return 0;
    }

    @Override
    public double getEvasion() {
        return 0;
    }
}
