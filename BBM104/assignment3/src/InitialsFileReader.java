import java.util.ArrayList;

public class InitialsFileReader {

    /* Reads the initials file, creates GameCharacter objects and appends them to the charactersArray,
     * and returns the GameBoard object created */
    protected static GameBoard readInitialsFile(String fileName) {
        ArrayList<String> lines = MyFileIOOperations.readFile(fileName);

        // Create the board
        assert lines != null;
        int boardSize = Integer.parseInt(lines.get(1).substring(0, lines.get(1).indexOf("x")));
        GameBoard board = new GameBoard(boardSize);

        for (String line : lines) {
            String[] lineData = line.split(" ");

            if (lineData.length == 4) {
                GameCharacter character;

                switch (lineData[0]) {

                    case "ORK":
                        character = new Ork(lineData[1], 200, HelperMethods.createArrayList(lineData[2], lineData[3]),
                                Constants.orkAP, Constants.orkMaxMove, Constants.orkHealPoints);
                        GameCharacter.charactersArray.add(character);
                        break;

                    case "TROLL":
                        character = new Troll(lineData[1], 150, HelperMethods.createArrayList(lineData[2], lineData[3]),
                                Constants.trollAP, Constants.trollMaxMove);
                        GameCharacter.charactersArray.add(character);
                        break;

                    case "GOBLIN":
                        character = new Goblin(lineData[1], 80, HelperMethods.createArrayList(lineData[2], lineData[3]),
                                Constants.goblinAP, Constants.goblinMaxMove);
                        GameCharacter.charactersArray.add(character);
                        break;

                    case "HUMAN":
                        character = new Human(lineData[1], 100, HelperMethods.createArrayList(lineData[2], lineData[3]),
                                Constants.humanAP,Constants.humanMaxMove);
                        GameCharacter.charactersArray.add(character);
                        break;

                    case "ELF":
                        character = new Elf(lineData[1], 70, HelperMethods.createArrayList(lineData[2], lineData[3]),
                                Constants.elfAP, Constants.elfMaxMove, Constants.elfRangedAP);
                        GameCharacter.charactersArray.add(character);
                        break;

                    case "DWARF":
                        character = new Dwarf(lineData[1], 120, HelperMethods.createArrayList(lineData[2], lineData[3]),
                                Constants.dwarfAP, Constants.dwarfMaxMove);
                        GameCharacter.charactersArray.add(character);
                        break;

                    default:
                        continue;
                }
                board.setCoordinatesAndCellContent(character.getCurrentPosition(), character.getCHARACTER_ID());
            }
        }
        return board;
    }
}
