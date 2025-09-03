public class Character {
    private String name;
    private boolean dead;
    private Role role;

    public Character(String name, Role role, boolean dead) {
        this.name = name;
        this.role = role;
        this.dead = dead;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) { this.dead = dead; }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
