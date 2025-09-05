public class RPGCharacter {
    private String name;
    private boolean dead;
    private Role role;
    private int level;

    public RPGCharacter(String name, Role role, int level, boolean dead) {
        this.name = name;
        this.role = role;
        this.dead = dead;
        this.level = level;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
