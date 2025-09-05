public class Enemy implements Role{
    private double health;
    private Weapon weapon;
    private int resource;
    private Ability ability;

    public Enemy(double health, Weapon weapon, int resource, Ability ability) {
        this.health = health;
        this.weapon = weapon;
        this.resource = resource;
        this.ability = ability;
    }

    public double getHealth() {
        return health;
    }
    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public void setResource(int resource) {
        this.resource = resource;
    }

    @Override
    public int getResource() {
        return resource;
    }

    @Override
    public String type() {
        return "Enemy";
    }

    @Override
    public void resetHealth() {

    }

    @Override
    public void resetResource() {

    }

    @Override
    public int useAbility() {
        return 0;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public Ability getAbility() {
        return ability;
    }

    @Override
    public String useWeapon() {
        return "";
    }
}
