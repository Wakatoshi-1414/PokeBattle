public class MoveDatabase {
    public static String[] getMovesFor(String pokemonName) {
        return switch (pokemonName.toLowerCase()) {
            case "charmander" -> new String[]{"Scratch", "Growl", "Ember", "Smokescreen"};
            case "squirtle" -> new String[]{"Tackle", "Tail Whip", "Water Gun", "Withdraw"};
            case "bulbasaur" -> new String[]{"Tackle", "Growl", "Vine Whip", "Leech Seed"};
            case "pidgey" -> new String[]{"Tackle", "Gust", "Quick Attack", "Growl"};
            case "rattata" -> new String[]{"Tackle", "Tail Whip", "Quick Attack", "Bite"};
            case "caterpie" -> new String[]{"Tackle", "String Shot"};
            case "weedle" -> new String[]{"Poison Sting", "String Shot"};
            case "spearow" -> new String[]{"Peck", "Growl", "Leer", "Fury Attack"};
            default -> new String[]{"Tackle"};
        };
    }
}
