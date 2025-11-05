import java.util.Random;
import java.util.Scanner;

public class BattleSystem {
    private static final Random rand = new Random();
    private static final Scanner sc = new Scanner(System.in);

    public static int calculateDamage(Pokemon attacker, Pokemon defender, int power) {
        double levelFactor = (2.0 * attacker.getLevel()) / 5 + 2;
        double baseDamage = (levelFactor * power * (attacker.getAttack() / (double) defender.getDefense())) / 50 + 2;
        double random = 0.85 + (rand.nextDouble() * 0.15);
        baseDamage *= random;
        return (int) baseDamage;
    }
    
    public static void performAttack(Pokemon attacker, Pokemon defender, int moveIndex) {
        String[] moves = attacker.getMoves();
        if (moves == null || moveIndex < 0 || moveIndex >= moves.length) {
            System.out.println("Invalid move choice!");
            return;
        }

        String moveName = moves[moveIndex];
        int power;

        switch (moveName.toLowerCase()) {
            case "tackle": power = 90; break;
            case "ember": power = 40; break;
            case "vine whip": power = 45; break;
            case "water gun": power = 40; break;
            case "quick attack": power = 40; break;
            case "scratch": power = 40; break;
            case "growl":
                System.out.println(attacker.getName() + " used Growl!");
                System.out.println(defender.getName() + "'s Attack fell!");
                return;
            default:
                power = 35;
        }

        System.out.println(attacker.getName() + " used " + moveName + "!");
        int damage = calculateDamage(attacker, defender, power);
        defender.setCurrentHP(defender.getCurrentHP() - damage);

        System.out.println(defender.getName() + " took " + damage + " damage!");
        System.out.println(defender.getName() + " HP: " + defender.getCurrentHP() + "/" + defender.getMaxHP());

        if (defender.isFainted()) {
            System.out.println(defender.getName() + " has fainted!");
        }
    }

    public static void startBattle(Pokemon player) throws InterruptedException {
        Pokemon wild = new Pokemon("Pidgey", "Normal", "Flying");
        wild.setMoves(MoveManager.getMovesFor("Pidgey"));
        startBattle(player, wild);
    }

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

            System.out.print("Enter move number: ");
            int choice = sc.nextInt() - 1;

            performAttack(player, enemy, choice);
            if (enemy.isFainted()) break;

            int enemyMove = rand.nextInt(enemy.getMoves().length);
            performAttack(enemy, player, enemyMove);
            Thread.sleep(3000);
            poke.clearConsole();
        }

        System.out.println("\nBattle ended!");
        if (player.isFainted()) 
            System.out.println(player.getName() + " fainted... You lost!");
        else 
            System.out.println("You defeated " + enemy.getName() + "!");
    }
}
