public class Ranger implements Role {
    private double health;
    private Bow bow;

    public Ranger(double health, Bow bow) {
        this.health = health;
        this.bow = bow;
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
    public String type() {
        return "ranger";
    }
}
