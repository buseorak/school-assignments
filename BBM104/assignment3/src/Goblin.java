import java.util.ArrayList;

public class Goblin extends Zorde implements EveryStepAttacker {

    // Constructor

    public Goblin(String ID, int HP, ArrayList<Integer> coordinates, int AP, int maximumMove) {
        super(ID, HP, coordinates, AP, maximumMove);
    }

    // Implement the method in the interface EveryStepAttacker
    @Override
    public void attackAtEveryStep(int[] allIncrements, GameBoard currentBoard, String outputFile) {
        int counter = 1;
        for (int i = 0; i < allIncrements.length; i += 2) {
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
}
