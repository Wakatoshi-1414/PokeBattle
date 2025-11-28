public class MoveDatabase {
    public static String[] getMovesFor(String name) {
        return switch(name.toLowerCase()) {
            case "charmander" -> new String[]{"Scratch", "Ember", "Growl", "Tackle"};
            case "charmeleon" -> new String[]{"Flamethrower", "Slash", "Growl", "Fire Fang"};
            case "charizard"  -> new String[]{"Flamethrower", "Fly", "Slash", "Fire Spin"};
            case "squirtle"   -> new String[]{"Tackle", "Water Gun", "Tail Whip", "Bite"};
            case "wartortle"  -> new String[]{"Water Gun", "Bite", "Rapid Spin", "Protect"};
            case "blastoise"  -> new String[]{"Hydro Pump", "Bite", "Skull Bash", "Protect"};
            case "bulbasaur"  -> new String[]{"Tackle", "Vine Whip", "Growl", "Leech Seed"};
            case "ivysaur"    -> new String[]{"Vine Whip", "Razor Leaf", "Growl", "Leech Seed"};
            case "venusaur"   -> new String[]{"Vine Whip", "Razor Leaf", "Solar Beam", "Sludge Bomb"};
            case "pidgey"     -> new String[]{"Tackle", "Gust", "Quick Attack", "Sand Attack"};
            default           -> new String[]{"Tackle", "Struggle", "Growl", "Quick Attack"};
        };
    }
}
