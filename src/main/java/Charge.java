public class Charge implements Ability {
    private final String name = "Charge";
    private final int damage = 20;

    @Override
    public String type() {
        return "damage";
    }

    @Override
    public int cost() {
        return 20;
    }
}
