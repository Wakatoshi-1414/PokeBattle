import java.util.Random;

public class PokemonManager {
    private static final Random rand = new Random();

    public static void startAdventure(Pokemon player) throws InterruptedException {
        Utils.clearConsole();  
        Utils.TypeEffect("Your adventure begins!", 50);
        Thread.sleep(1000);

        int battleCount = 1;
        while (true) {
            Utils.clearConsole();  
            System.out.println("\n--- Battle " + battleCount + " ---");
            Pokemon wild = generateWildPokemon();
            BattleSystem.startBattle(player, wild);

            if (player.isFainted()) {
                Utils.clearConsole();  
                System.out.println("\nYour Pokémon has fainted...");
                System.out.println("Game Over!");
                break;
            }

            int expGain = 50 + player.getLevel() * 10; 
            player.gainExp(expGain);

            Utils.TypeEffect("\nYou take a short rest", 50);
            Utils.TypeEffect(". . .", 100);
            Thread.sleep(2000);

            int healAmount = 35;
            player.setCurrentHP(player.getCurrentHP() + healAmount);
            System.out.println("\n" + player.getName() + " recovered " + healAmount + " HP!");
            Thread.sleep(1500);

            battleCount++;
            System.out.println("\nDo you want to continue your adventure? (Y/N)");
            char cont = BattleSystem.sc.next().toUpperCase().charAt(0);
            if(cont != 'Y') break;
        }
    }

    private static Pokemon generateWildPokemon() {
        String[] possibleNames = {"Pidgey", "Rattata", "Caterpie", "Weedle", "Spearow"};
        String name = possibleNames[rand.nextInt(possibleNames.length)];
        String type1 = switch (name.toLowerCase()) {
            case "pidgey", "spearow" -> "Flying";
            case "caterpie", "weedle" -> "Bug";
            default -> "Normal";
        };
        String type2 = null;
        if(name.equalsIgnoreCase("pidgey") || name.equalsIgnoreCase("spearow")) type2 = "Normal";

        Pokemon wild = new Pokemon(name, type1, type2, rand.nextInt(3)+1);
        wild.setMoves(MoveDatabase.getMovesFor(name));
        return wild;
    }
}
