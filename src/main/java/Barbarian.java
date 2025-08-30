public class Barbarian implements Role {
    private double health;
    private Sword sword;
    private int resource;
    private Ability ability;

    public Barbarian( double health, Sword sword, int resource, Ability ability) {
        this.health = health;
        this.sword = sword;
        this.resource = resource;
        this.ability = ability;
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
    public int resource() {
        return resource;
    }

    @Override
    public String type() {
        return "barbarian";
    }
}

