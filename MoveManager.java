import java.util.HashMap;
import java.util.Map;

public class MoveManager {
    private static final Map<String, String[]> moveSets = new HashMap<>();

    static {
        
        moveSets.put("Charmander", new String[]{"Scratch", "Growl", "Ember", "Smokescreen"});
        moveSets.put("Charmeleon", new String[]{"Scratch", "Flamethrower", "Fire Fang", "Slash"});

        
        moveSets.put("Squirtle", new String[]{"Tackle", "Tail Whip", "Water Gun", "Withdraw"});
        moveSets.put("Wartortle", new String[]{"Bite", "Rapid Spin", "Water Pulse", "Protect"});

       
        moveSets.put("Bulbasaur", new String[]{"Tackle", "Growl", "Vine Whip", "Leech Seed"});
        moveSets.put("Ivysaur", new String[]{"Vine Whip", "Poison Powder", "Razor Leaf", "Sleep Powder"});

        
        moveSets.put("Pidgey", new String[]{"Tackle", "Quick Attack", "Gust", "Growl"});
        moveSets.put("Rattata", new String[]{"Tackle", "Tail Whip", "Quick Attack", "Bite"});
    }

    
    public static String[] getMovesFor(String speciesName) {
        return moveSets.getOrDefault(speciesName, new String[]{"Tackle"});
    }
}
