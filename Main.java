import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        Pokemon player = null;

        System.out.println("=== Pokémon Adventure ===");
        System.out.println("1. New Game");
        System.out.println("2. Load Saved Pokémon");
        System.out.print("Choose an option: ");
        int menuChoice = input.nextInt();

        boolean loadedGame = false;

        if (menuChoice == 2) {
            player = new Pokemon("", "", null, 5);
            player.load();
            if (player.getName() == null || player.getName().isEmpty()) {
                System.out.println("No saved Pokémon found. Starting a new game...");
                player = null;
            } else {
                Utils.TypeEffect("Your adventure continues...", 40);
                loadedGame = true;
            }
        }

        // New Game
        if (player == null) {

            Utils.TypeEffect("Your adventure begins...", 40);
            Utils.TypeEffect("Please choose your starter:", 50);
            System.out.println("\n1. Charmander\n2. Squirtle\n3. Bulbasaur");
            System.out.print("> ");
            int choice = input.nextInt();

            switch (choice) {
                case 1 -> player = new Pokemon("Charmander", "Fire", null, 5);
                case 2 -> player = new Pokemon("Squirtle", "Water", null, 5);
                case 3 -> player = new Pokemon("Bulbasaur", "Grass", "Poison", 5);
                default -> {
                    System.out.println("Invalid choice! Defaulting to Charmander.");
                    player = new Pokemon("Charmander", "Fire", null, 5);
                }
            }

            player.setMoves(MoveDatabase.getMovesFor(player.getName()));

            Utils.TypeEffect("Would you like to give your " + player.getName() + " a nickname?", 40);
            System.out.println("(Y/N)");
            System.out.print("> ");
            char ans = input.next().toUpperCase().charAt(0);
            if (ans == 'Y') {
                System.out.print("Enter nickname: ");
                String nickname = input.next();
                String oldName = player.getName();
                player.setName(nickname);
                Utils.TypeEffect(oldName + " is now " + player.getName() + "!", 40);
            }
        }

        Utils.clearConsole();

        boolean keepPlaying = true;
        while (!player.isFainted() && keepPlaying) {

            PokemonManager.startAdventure(player);

            if (player.isFainted()) {
                System.out.println("\nYour Pokémon has fainted! Game Over.");
                break;
            }

            System.out.println("\nDo you want to continue your adventure? (Y/N)");
            char cont = input.next().toUpperCase().charAt(0);
            if (cont != 'Y') keepPlaying = false;
        }

        player.save();
        System.out.println("\nGame saved. Thanks for playing!");
        input.close();
    }
}
