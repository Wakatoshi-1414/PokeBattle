import java.io.*;

public class Pokemon extends Creature implements Savable {
    int attack, defense, spAttack, spDefense, speed;
    private String[] moves = new String[4];
    private int exp = 0;

    public Pokemon(String name, String type1, String type2, int level) {
        super(name, type1, type2, level);
        this.maxHP = 20 + level*5;
        this.currentHP = maxHP;
        this.attack = 10 + level*2;
        this.defense = 10 + level*2;
        this.spAttack = 10 + level*2;
        this.spDefense = 10 + level*2;
        this.speed = 10 + level;
    }

    public void setMoves(String[] moves) { this.moves = moves; }
    public String[] getMoves() { return moves; }

    public void gainExp(int amount) {
        exp += amount;
        System.out.println(name + " gained " + amount + " EXP!");
        while(exp >= level*100) {
            exp -= level*100;
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        maxHP += 5; currentHP = maxHP;
        attack += 2; defense += 2;
        spAttack += 2; spDefense += 2; speed += 1;
        System.out.println(name + " leveled up! Now level " + level + "!");
        checkEvolution();
    }

    private void checkEvolution() {
        switch(name.toLowerCase()) {
            case "charmander": if(level>=16) evolve("Charmeleon","Fire",null); break;
            case "charmeleon": if(level>=36) evolve("Charizard","Fire","Flying"); break;
            case "squirtle": if(level>=16) evolve("Wartortle","Water",null); break;
            case "wartortle": if(level>=36) evolve("Blastoise","Water",null); break;
            case "bulbasaur": if(level>=16) evolve("Ivysaur","Grass","Poison"); break;
            case "ivysaur": if(level>=32) evolve("Venusaur","Grass","Poison"); break;
        }
    }

    private void evolve(String newName, String newType1, String newType2) {
        System.out.println(name + " evolved into " + newName + "!");
        this.name = newName;
        this.type1 = newType1;
        this.type2 = newType2;
        this.moves = MoveDatabase.getMovesFor(newName);
    }

    @Override
    public void showStats() {
        System.out.println("=== " + name + " ===");
        System.out.println("Type: " + type1 + (type2!=null?"/"+type2:""));
        System.out.println("Level: " + level + " | EXP: " + exp + "/" + level*100);
        System.out.println("HP: " + currentHP + "/" + maxHP);
        System.out.println("Attack: " + attack + " | Defense: " + defense);
        System.out.println("Sp. Atk: " + spAttack + " | Sp. Def: " + spDefense + " | Speed: " + speed);
        System.out.println("Moves:");
        for(String move:moves) System.out.println("- "+move);
    }

    @Override
    public void save() {
        try(PrintWriter writer = new PrintWriter(new FileWriter("pokemon_save.txt"))){
            writer.println(name);
            writer.println(type1);
            writer.println(type2!=null?type2:"null");
            writer.println(level);
            writer.println(currentHP);
            writer.println(maxHP);
            writer.println(attack);
            writer.println(defense);
            writer.println(spAttack);
            writer.println(spDefense);
            writer.println(speed);
            for(String move:moves) writer.println(move!=null?move:"null");
            System.out.println("Game saved successfully!");
        } catch(IOException e){ System.out.println("Error saving: "+e.getMessage()); }
    }

    @Override
    public void load() {
        File file = new File("pokemon_save.txt");
        if(!file.exists()) return;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            name = reader.readLine();
            type1 = reader.readLine();
            type2 = reader.readLine();
            if("null".equals(type2)) type2 = null;
            level = Integer.parseInt(reader.readLine());
            currentHP = Integer.parseInt(reader.readLine());
            maxHP = Integer.parseInt(reader.readLine());
            attack = Integer.parseInt(reader.readLine());
            defense = Integer.parseInt(reader.readLine());
            spAttack = Integer.parseInt(reader.readLine());
            spDefense = Integer.parseInt(reader.readLine());
            speed = Integer.parseInt(reader.readLine());
            for(int i=0;i<4;i++){
                String move = reader.readLine();
                moves[i] = "null".equals(move)?null:move;
            }
            System.out.println("Game loaded successfully!");
        } catch(IOException e){ System.out.println("Error loading: "+e.getMessage()); }
    }
}
