public class Ranger implements Role {
    private double health;
    private Bow bow;
    private int resource;
    private Ability ability;

    public Ranger(double health, Bow bow, int resource, Ability ability) {
        this.health = health;
        this.bow = bow;
        this.resource = resource;
        this.ability = ability;
    }

    public Bow getBow() {
        return bow;
    }
    public void setBow(Bow bow) {
        this.bow = bow;
    }

    @Override
    public double health() {
        return health;
    }

    @Override
    public int resource() {
        return resource;
    }

    @Override
    public String type() {
        return "Ranger";
    }
}
