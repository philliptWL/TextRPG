public class Barbarian implements Role {
    private double health;
    private Sword sword;

    public Barbarian( double health, Sword sword) {
        this.health = health;
        this.sword = sword;
    }

    public Sword getSword() {
        return sword;
    }
    public void setSword(Sword sword) {
        this.sword = sword;
    }
    @Override
    public double health() {
        return health;
    }

    @Override
    public String type() {
        return "barbarian";
    }
}

