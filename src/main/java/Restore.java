public class Restore implements Ability{
    private final String name = "Restore";
    private final double restore = 50.0;
    @Override
    public String type() {
        return "heal";
    }

    @Override
    public int cost() {
        return 40;
    }
}
