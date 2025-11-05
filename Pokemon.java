public class Pokemon {
    private String name;
    private String type1;
    private String type2;
    private int level;
    private int maxHP;
    private int currentHP;
    private int attack;
    private int defense;
    private int spAttack;
    private int spDefense;
    private int speed;
    private String[] moves;

    public String[] getMoves() { return moves; }
    public void setMoves(String[] moves) { this.moves = moves; }


    public Pokemon(String name, String type1, String type2) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.level = 10;
        this.maxHP = 100;
        this.currentHP = maxHP;
        this.attack = 50;
        this.defense = 30;
        this.spAttack = 50;
        this.spDefense = 20;
        this.speed = 35;
    }

    public String getName() { return name; }
    public String getType1() { return type1; }
    public String getType2() { return type2; }
    public int getLevel() { return level; }
    public int getMaxHP() { return maxHP; }
    public int getCurrentHP() { return currentHP; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpAttack() { return spAttack; }
    public int getSpDefense() { return spDefense; }
    public int getSpeed() { return speed; }

    public void setName(String name) { this.name = name; }
    public void setLevel(int level) { this.level = level; }
    public void setCurrentHP(int currentHP) {
        this.currentHP = Math.max(0, Math.min(currentHP, maxHP));
    }

    public boolean isFainted() {
        return currentHP <= 0;
    }

    public void showStats() {
        System.out.println("Name: " + name);
        System.out.println("Type: " + type1 + (type2 != null ? "/" + type2 : ""));
        System.out.println("Level: " + level);
        System.out.println("HP: " + currentHP + "/" + maxHP);
        System.out.println("Attack: " + attack);
        System.out.println("Defense: " + defense);
        System.out.println("Sp. Attack: " + spAttack);
        System.out.println("Sp. Defense: " + spDefense);
        System.out.println("Speed: " + speed);
        System.out.println();
    }
}
