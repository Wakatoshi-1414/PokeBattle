import java.io.*;
import java.util.Scanner;

public class poke {
    static void TypeEffect(String text, int delay) throws InterruptedException {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            Thread.sleep(delay);
        }
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void savePokemon(Pokemon pokemon) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("pokemon_save.txt"))) {
            writer.println(pokemon.getName());
            writer.println(pokemon.getType1());
            writer.println(pokemon.getType2() != null ? pokemon.getType2() : "null");
            writer.println(pokemon.getLevel());
            writer.println(pokemon.getCurrentHP());
            writer.println(pokemon.getMaxHP());
            writer.println(pokemon.getAttack());
            writer.println(pokemon.getDefense());
            writer.println(pokemon.getSpAttack());
            writer.println(pokemon.getSpDefense());
            writer.println(pokemon.getSpeed());

            String[] moves = pokemon.getMoves();
            for (String move : moves) {
                writer.println(move);
            }

            System.out.println("\n💾 Pokémon auto-saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving Pokémon: " + e.getMessage());
        }
    }

    public static Pokemon loadPokemon() {
        File file = new File("pokemon_save.txt");
        if (!file.exists()) {
            System.out.println("No saved Pokémon found.");
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String name = reader.readLine();
            String type1 = reader.readLine();
            String type2 = reader.readLine();
            if ("null".equals(type2)) type2 = null;

            int level = Integer.parseInt(reader.readLine());
            int currentHP = Integer.parseInt(reader.readLine());
            int maxHP = Integer.parseInt(reader.readLine());
            int attack = Integer.parseInt(reader.readLine());
            int defense = Integer.parseInt(reader.readLine());
            int spAttack = Integer.parseInt(reader.readLine());
            int spDefense = Integer.parseInt(reader.readLine());
            int speed = Integer.parseInt(reader.readLine());

            String[] moves = new String[4];
            for (int i = 0; i < 4; i++) {
                moves[i] = reader.readLine();
            }

            Pokemon p = new Pokemon(name, type1, type2);
            p.setLevel(level);
            p.setCurrentHP(currentHP);
            p.setMoves(moves);
            System.out.println("\nPokémon loaded successfully!");
            return p;
        } catch (IOException e) {
            System.out.println("Error loading Pokémon: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        Pokemon pokemon = null;

        System.out.println("1. New Game");
        System.out.println("2. Load Saved Pokémon");
        System.out.print("Choose an option: ");
        int menuChoice = input.nextInt();

        if (menuChoice == 2) {
            pokemon = loadPokemon();
            if (pokemon == null) {
                System.out.println("Starting a new game instead...");
            }
        }

        if (pokemon == null) {
            TypeEffect("Please choose your starter:", 50);
            System.out.println("\n1. Charmander");
            System.out.println("2. Squirtle");
            System.out.println("3. Bulbasaur");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    pokemon = new Pokemon("Charmander", "Fire", null);
                    break;
                case 2:
                    pokemon = new Pokemon("Squirtle", "Water", null);
                    break;
                case 3:
                    pokemon = new Pokemon("Bulbasaur", "Grass", "Poison");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }

            
            pokemon.setMoves(MoveManager.getMovesFor(pokemon.getName()));

            TypeEffect("Would you like to give your " + pokemon.getName() + " a nickname", 40);
            System.out.println("\nY/N");
            System.out.print(">");
            char ans = input.next().toUpperCase().charAt(0);
            if (ans == 'Y') {
                String oldName = pokemon.getName();
                System.out.print("Enter nickname: ");
                String nickname = input.next();
                pokemon.setName(nickname);
                TypeEffect(oldName + " is now " + pokemon.getName() + "!", 40);
            } else {
                System.out.println(pokemon.getName());
            }
        }

        Thread.sleep(1000);
        clearConsole();

        pokemon.showStats();

        PokemonManager.startAdventure(pokemon);
        
        savePokemon(pokemon);

        input.close();
    }
}
