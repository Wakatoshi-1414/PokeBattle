import java.util.Random;
import java.util.Scanner;

public class BattleSystem {
    public static final Scanner sc = new Scanner(System.in);
    private static final Random rand = new Random();

    public static void startBattle(Pokemon player, Pokemon enemy) throws InterruptedException {
        System.out.println("\nA wild " + enemy.getName() + " appeared!");

        while (!player.isFainted() && !enemy.isFainted()) {

            System.out.println("\nYour HP: " + player.getCurrentHP() + "/" + player.getMaxHP());
            System.out.println(enemy.getName() + " HP: " + enemy.getCurrentHP() + "/" + enemy.getMaxHP());

            System.out.println("\nChoose your move:");
            String[] moves = player.getMoves();
            for (int i = 0; i < moves.length; i++) {
                System.out.println((i + 1) + ". " + moves[i]);
            }

            int choice;
            while (true) {
                System.out.print("Enter move number: ");
                choice = sc.nextInt() - 1;
                if (choice >= 0 && choice < moves.length) break;
                System.out.println("Invalid choice, try again.");
            }

            performAttack(player, enemy, choice);
            if (enemy.isFainted()) break;

            int enemyMove = rand.nextInt(enemy.getMoves().length);
            performAttack(enemy, player, enemyMove);

            Thread.sleep(2000);
            Utils.clearConsole();
        }

        
        System.out.println("\nBattle ended!");

        if (player.isFainted())
            System.out.println(player.getName() + " fainted... You lost!");
        else
            System.out.println("You defeated " + enemy.getName() + "!");

        
        showPostBattleStats(player);
    }

    // Damage calc
    private static int calculateDamage(Pokemon attacker, Pokemon defender, int power) {
        double levelFactor = (2.0 * attacker.getLevel()) / 5 + 2;
        double baseDamage = (levelFactor * power * (attacker.attack / (double) defender.defense)) / 50 + 2;
        double random = 0.85 + (rand.nextDouble() * 0.15);
        baseDamage *= random;
        return (int) baseDamage;
    }

    
    private static void performAttack(Pokemon attacker, Pokemon defender, int moveIndex) {
        String[] moves = attacker.getMoves();
        String moveName = moves[moveIndex];
        int power;

        switch (moveName.toLowerCase()) {
            case "tackle": power = 90; break;
            case "scratch": power = 40; break;
            case "ember": power = 40; break;
            case "vine whip": power = 45; break;
            case "water gun": power = 40; break;
            case "quick attack": power = 40; break;
            case "growl":
                System.out.println(attacker.getName() + " used Growl! " + defender.getName() + "'s attack fell!");
                return;
            default: power = 35;
        }

        System.out.println(attacker.getName() + " used " + moveName + "!");
        int damage = calculateDamage(attacker, defender, power);
        defender.setCurrentHP(defender.getCurrentHP() - damage);

        System.out.println(defender.getName() + " took " + damage + " damage!");
        System.out.println(defender.getName() + " HP: " + defender.getCurrentHP() + "/" + defender.getMaxHP());

        if (defender.isFainted())
            System.out.println(defender.getName() + " has fainted!");
    }

    
    private static void showPostBattleStats(Pokemon p) {
        System.out.println("\n=== Pokémon Stats After Battle ===");
        System.out.println("Name: " + p.getName());
        System.out.println("Level: " + p.getLevel());
        System.out.println("HP: " + p.getCurrentHP() + "/" + p.getMaxHP());
        System.out.println("Attack: " + p.attack);
        System.out.println("Defense: " + p.defense);

        System.out.println("Moves:");
        for (String m : p.getMoves()) {
            System.out.println(" - " + m);
        }

        System.out.println("==================================\n");
    }
}
