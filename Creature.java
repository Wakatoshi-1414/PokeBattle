public abstract class Creature {
    protected String name;
    protected String type1;
    protected String type2;
    protected int level;
    protected int maxHP;
    protected int currentHP;
    protected int attack;
    protected int defense;
    protected int spAttack;
    protected int spDefense;
    protected int speed;

    public Creature(String name, String type1, String type2, int level) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.level = level;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType1() { return type1; }
    public String getType2() { return type2; }
    public int getLevel() { return level; }
    public int getMaxHP() { return maxHP; }
    public int getCurrentHP() { return currentHP; }
    public void setCurrentHP(int hp) { this.currentHP = Math.max(0, Math.min(hp, maxHP)); }
    public boolean isFainted() { return currentHP <= 0; }

    public abstract void showStats();
}
