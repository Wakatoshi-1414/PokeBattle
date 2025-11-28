public class MoveManager {
    // central place for move lists
    public static String[] getMovesFor(String pokemonName) {
        if (pokemonName == null) return new String[] {"Tackle", "Struggle", "Struggle", "Struggle"};
        switch (pokemonName.toLowerCase()) {
            case "charmander":
            case "charmeleon":
            case "charizard":
                return new String[] {"Scratch", "Growl", "Ember", "Flamethrower"};
            case "squirtle":
            case "wartortle":
            case "blastoise":
                return new String[] {"Tackle", "Tail Whip", "Water Gun", "Withdraw"};
            case "bulbasaur":
            case "ivysaur":
            case "venusaur":
                return new String[] {"Tackle", "Growl", "Vine Whip", "Leech Seed"};
            case "pidgey":
                return new String[] {"Tackle", "Sand Attack", "Gust", "Quick Attack"};
            case "rattata":
                return new String[] {"Tackle", "Tail Whip", "Quick Attack", "Bite"};
            case "caterpie":
            case "weedle":
                return new String[] {"Tackle", "String Shot", "Tackle", "Tackle"};
            case "spearow":
                return new String[] {"Peck", "Leer", "Fury Attack", "Quick Attack"};
            default:
                return new String[] {"Tackle", "Growl", "Struggle", "Struggle"};
        }
    }
}
