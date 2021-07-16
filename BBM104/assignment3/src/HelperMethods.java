import java.util.ArrayList;
import java.util.Collections;

public class HelperMethods {

    // Returns an ArrayList of 2 Integers (converted from the two Strings passed as parameters), representing the x-y coordinates
    protected static ArrayList<Integer> createArrayList(String xCoordinate, String yCoordinate) {
        ArrayList<Integer> coordinates = new ArrayList<>();
        coordinates.add(Integer.parseInt(xCoordinate));
        coordinates.add(Integer.parseInt(yCoordinate));
        return coordinates;
    }

    // Overloaded createArrayList method that takes integer parameters instead of String parameters
    protected static ArrayList<Integer> createArrayList(int xCoordinate, int yCoordinate) {
        ArrayList<Integer> coordinates = new ArrayList<>();
        coordinates.add(xCoordinate);
        coordinates.add(yCoordinate);
        return coordinates;
    }

    // Returns a String containing the characters' current and default HP information
    protected static String printCharacterInformation() {
        String buildOnThis = "";

        ArrayList<String> characterIDs = new ArrayList<>();

        for (GameCharacter character : GameCharacter.charactersArray) {
            characterIDs.add(character.getCHARACTER_ID());
        }

        Collections.sort(characterIDs);

        ArrayList<GameCharacter> sortedCharacters = new ArrayList<>();

        for (String characterID : characterIDs) {
            for (GameCharacter character :GameCharacter.charactersArray) {
                if (characterID.equals(character.getCHARACTER_ID())) {
                    sortedCharacters.add(character);
                    break;
                }
            }
        }

        for (GameCharacter character : sortedCharacters) {
            buildOnThis = buildOnThis.concat(character.getCHARACTER_ID() + "\t" + character.getCurrentHP() + "\t(" +
                    character.getDEFAULT_HP() + ")\n");
        }

        return buildOnThis.concat("\n");
    }
}
