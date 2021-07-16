import java.util.ArrayList;

public class CommandsFileReader {

    // Reads the commands file and performs the operations for the movements according to the commands
    protected static void readCommandsFile(String commandFile, GameBoard currentBoard, String outputFile) {
        // Create the output file
        MyFileIOOperations.createOutputFile(outputFile);

        // Print the initial board and the HP values of characters
        MyFileIOOperations.writeFile(currentBoard.printBoard() + HelperMethods.printCharacterInformation(), outputFile);

        // Read the command input file
        ArrayList<String> lines = MyFileIOOperations.readFile(commandFile);

        assert lines != null;
        for (String line : lines) {
            String characterID = line.replaceAll(";$", "").split(" ")[0];
            String[] allIncrementsString = line.replaceAll(";$", "").split(" ")[1].split(";");

            int[] allIncrements = new int[allIncrementsString.length];
            for (int i = 0; i < allIncrementsString.length; i++) {
                allIncrements[i] = Integer.parseInt(allIncrementsString[i]);
            }

            GameCharacter movingCharacter = null;

            for (GameCharacter character : GameCharacter.charactersArray) {
                if (character.getCHARACTER_ID().equals(characterID)) {
                    movingCharacter = character;
                }
            }

            if (movingCharacter == null) {
                continue;
            }

            try {
                if ((2 * movingCharacter.getMAXIMUM_MOVE()) != allIncrements.length) {
                    throw new IncorrectNumberOfMovesException();
                }
                else {
                    if (movingCharacter instanceof Ork) {
                        Ork ork = (Ork) movingCharacter;
                        ork.attackAfterFinalStep(allIncrements, currentBoard, outputFile);
                    }

                    else if (movingCharacter instanceof Troll) {
                        Troll troll = (Troll) movingCharacter;
                        troll.attackAfterFinalStep(allIncrements, currentBoard, outputFile);
                    }

                    else if (movingCharacter instanceof Goblin) {
                        Goblin goblin = (Goblin) movingCharacter;
                        goblin.attackAtEveryStep(allIncrements, currentBoard, outputFile);
                    }

                    else if (movingCharacter instanceof Human) {
                        Human human = (Human) movingCharacter;
                        human.attackAfterFinalStep(allIncrements, currentBoard, outputFile);
                    }

                    else if (movingCharacter instanceof Elf) {
                        Elf elf = (Elf) movingCharacter;
                        elf.attackAtEveryStep(allIncrements, currentBoard, outputFile);
                    }

                    else if (movingCharacter instanceof Dwarf) {
                        Dwarf dwarf = (Dwarf) movingCharacter;
                        dwarf.attackAtEveryStep(allIncrements, currentBoard, outputFile);
                    }
                }
            }
            catch (IncorrectNumberOfMovesException e) {
                MyFileIOOperations.writeFile("Error : Move sequence contains wrong number of move steps. " +
                        "Input line ignored.\n\n", outputFile);
            }
        }

        // Print the winner
        MyFileIOOperations.writeFile("\nGame Finished\n" + currentBoard.getWinner() + " Wins\n", outputFile);
        System.exit(0);
    }
}
