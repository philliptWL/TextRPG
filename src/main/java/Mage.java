public class Mage implements Role {
    private double health;
    private Staff staff;

    public Mage(double health,Staff staff) {
        this.health = health;
        this.staff = staff;
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
    public String type() {
        return "mage";
    }
}
