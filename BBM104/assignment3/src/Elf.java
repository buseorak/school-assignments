import java.util.ArrayList;

public class Elf extends Calliance implements EveryStepAttacker {

    // Fields

    private final int RANGED_AP;
    private boolean isAtFinalStep;

    // Constructor

    public Elf(String ID, int HP, ArrayList<Integer> coordinates, int AP, int maximumMove, int rangedAP) {
        super(ID, HP, coordinates, AP, maximumMove);
        RANGED_AP = rangedAP;
    }

    // Override method in GameCharacter for ranged-attack step
    @Override
    protected ArrayList<ArrayList<Integer>> getCoordinatesOfNeighboringCells(GameBoard currentBoard) {
        if (!isAtFinalStep) {
            return super.getCoordinatesOfNeighboringCells(currentBoard);
        }
        int currentX = getCurrentPosition().get(0);
        int currentY = getCurrentPosition().get(1);

        ArrayList<ArrayList<Integer>> coordinatesAround = new ArrayList<>();

        int[] increments = new int[] {-2, -1, 0, 1, 2};

        for (int i : increments) {
            for (int j : increments) {
                if ((i == 0) && (j == 0)) {
                    continue;
                }
                if (currentBoard.getCoordinatesAndCellContent().containsKey(HelperMethods.createArrayList(currentX + i, currentY + j))) {
                    coordinatesAround.add(HelperMethods.createArrayList(currentX + i, currentY + j));
                }
            }
        }

        return coordinatesAround;
    }

    // Implement the method in the interface EveryStepAttacker
    @Override
    public void attackAtEveryStep(int[] allIncrements, GameBoard currentBoard, String outputFile) {
        int counter = 1;
        for (int i = 0; i < allIncrements.length; i += 2) {
            isAtFinalStep = (counter == getMAXIMUM_MOVE());

            int returnValue = callAttackingMethods(allIncrements[i], allIncrements[i + 1], currentBoard, counter, outputFile);

            if (returnValue == 1) {
                break;
            }

            else if (returnValue == 2) {
                counter++;
                continue;
            }

            else if (returnValue == 3) {
                break;
            }


            counter++;
        }
    }

    // Getters

    protected int getRANGED_AP() {
        return RANGED_AP;
    }

    protected boolean isAtFinalStep() {
        return isAtFinalStep;
    }
}
