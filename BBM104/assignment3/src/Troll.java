import java.util.ArrayList;

public class Troll extends Zorde implements FinalStepAttacker {

    // Constructor

    public Troll(String ID, int HP, ArrayList<Integer> coordinates, int AP, int maximumMove) {
        super(ID, HP, coordinates, AP, maximumMove);
    }

    // Implement the method in the interface FinalStepAttacker
    @Override
    public void attackAfterFinalStep(int[] allIncrements, GameBoard currentBoard, String outputFile) {
        callAttackingMethods(allIncrements[0], allIncrements[1], currentBoard, getMAXIMUM_MOVE(), outputFile);
    }
}
