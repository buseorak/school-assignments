import java.util.ArrayList;
import java.util.HashMap;

public class GameBoard {

    // Fields

    private final int BOARD_SIZE;
    private HashMap<ArrayList<Integer>, String> coordinatesAndCellContent = new HashMap<>();
    private String winner = "";

    // Constructor

    public GameBoard(int size) {
        BOARD_SIZE = size;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ArrayList<Integer> coordinates = new ArrayList<>();
                coordinates.add(j);
                coordinates.add(i);
                coordinatesAndCellContent.put(coordinates, "  ");
            }
        }
    }

    // Returns a String containing the contents of the current board
    protected String printBoard() {
        String boardString = "";

        for (int i = 0; i < 2 * (BOARD_SIZE + 1); i++) {
            boardString = boardString.concat("*");
        }

        boardString = boardString.concat("\n");

        for (int i = 0; i < BOARD_SIZE; i++) {
            boardString = boardString.concat("*");
            for (int j = 0; j < BOARD_SIZE; j++) {
                ArrayList<Integer> coordinates = new ArrayList<>();
                coordinates.add(j);
                coordinates.add(i);
                String character = coordinatesAndCellContent.get(coordinates);
                boardString = boardString.concat(character);
            }
            boardString = boardString.concat("*\n");
        }

        for (int i = 0; i < 2 * (BOARD_SIZE + 1); i++) {
            boardString = boardString.concat("*");
        }

        return boardString.concat("\n\n");
    }


    // Getters and Setters

    protected HashMap<ArrayList<Integer>, String> getCoordinatesAndCellContent() {
        return coordinatesAndCellContent;
    }

    protected void setCoordinatesAndCellContent(ArrayList<Integer> coordinates, String content) {
        coordinatesAndCellContent.replace(coordinates, content);
    }

    protected String getWinner() {
        return winner;
    }

    protected void setWinner(GameCharacter character, boolean won) {
        if (winner.equals("")) {
            if (won) {
                winner = character.getCharactersSide();
            }
            else {
                winner = character.getEnemysSide();
            }
        }
    }
}
