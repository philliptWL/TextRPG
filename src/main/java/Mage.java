public class Mage implements Role {
    private double health;
    private Staff staff;
    private int resource;
    private Ability ability;

    public Mage(double health,Staff staff,int resource,Ability ability) {
        this.health = health;
        this.staff = staff;
        this.resource = resource;
        this.ability = ability;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
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
        return "Mage";
    }
}
