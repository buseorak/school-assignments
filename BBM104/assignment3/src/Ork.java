import java.util.ArrayList;

public class Ork extends Zorde implements FinalStepAttacker {

    // Field

    private final int HEAL_POINTS;

    // Constructor

    public Ork(String ID, int HP, ArrayList<Integer> coordinates, int AP, int maximumMove, int healPoints) {
        super(ID, HP, coordinates, AP, maximumMove);
        HEAL_POINTS = healPoints;
    }

    // Overridden move method inherited from GameCharacter, in order to make Ork heal Zorde characters
    @Override
    public int move(int xIncrement, int yIncrement, GameBoard currentBoard) throws GameBoardBoundariesExceededException {
        ArrayList<Integer> targetCoordinates = HelperMethods.createArrayList(getCurrentPosition().get(0) + xIncrement,
                getCurrentPosition().get(1) + yIncrement);

        if (!currentBoard.getCoordinatesAndCellContent().containsKey(targetCoordinates)) {
            throw new GameBoardBoundariesExceededException();
        }
        else {
            increaseAndSetCurrentHP(HEAL_POINTS);

            ArrayList<ArrayList<Integer>> coordinatesOfCellsAround = getCoordinatesOfNeighboringCells(currentBoard);
            for (ArrayList<Integer> coordinates : coordinatesOfCellsAround) {
                String cellContent = currentBoard.getCoordinatesAndCellContent().get(coordinates);
                if (!cellContent.equals("  ")) {
                    for (GameCharacter ally : GameCharacter.charactersArray) {
                        if ((ally.getCHARACTER_ID().equals(cellContent)) && (ally instanceof Zorde)) {
                            ally.increaseAndSetCurrentHP(HEAL_POINTS);
                        }
                    }
                }
            }
            return super.move(xIncrement, yIncrement, currentBoard);
        }
    }

    // Implement the method in the interface FinalStepAttacker
    @Override
    public void attackAfterFinalStep(int[] allIncrements, GameBoard currentBoard, String outputFile) {
        callAttackingMethods(allIncrements[0], allIncrements[1], currentBoard, getMAXIMUM_MOVE(), outputFile);
    }
}
