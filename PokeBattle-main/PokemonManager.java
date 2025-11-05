import java.util.Random;

public class PokemonManager {
    private static final Random rand = new Random();

    public static void startAdventure(Pokemon player) throws InterruptedException {
       poke.TypeEffect("\nYour adventure begins!", 50);
        Thread.sleep(1000);

        int battleCount = 1;

        while (true) {
            System.out.println("\n--- Battle " + battleCount + " ---");
            Pokemon wild = generateWildPokemon();
            BattleSystem.startBattle(player, wild);

            if (player.isFainted()) {
                System.out.println("\nYour Pokémon has fainted...");
                System.out.println("Game Over!");
                break;
            }

            poke.TypeEffect("\n You take a short rest", 50);
            poke.TypeEffect(". . .", 100);
            Thread.sleep(3000);

            
            int healAmount = 35;
            player.setCurrentHP(player.getCurrentHP() + healAmount);
            System.out.println("\n" + player.getName() + " recovered " + healAmount + " HP!");
            Thread.sleep(1500);

            battleCount++;
            poke.clearConsole();
        }
    }

    private static Pokemon generateWildPokemon() {
        String[] possibleNames = {"Pidgey", "Rattata", "Caterpie", "Weedle", "Spearow"};
        String name = possibleNames[rand.nextInt(possibleNames.length)];
        String type1 = switch (name.toLowerCase()) {
            case "pidgey" -> "Normal";
            case "rattata" -> "Normal";
            case "caterpie" -> "Bug";
            case "weedle" -> "Bug";
            case "spearow" -> "Flying";
            default -> "Normal";
        };
        String type2 = (name.equalsIgnoreCase("Pidgey") || name.equalsIgnoreCase("Spearow")) ? "Flying" : null;

        Pokemon wild = new Pokemon(name, type1, type2);
        wild.setMoves(MoveDatabase.getMovesFor(name));
        return wild;
    }
}
