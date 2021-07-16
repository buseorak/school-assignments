public interface Attacker {

    int move(int xIncrement, int yIncrement, GameBoard currentBoard) throws GameBoardBoundariesExceededException;

    int callAttackingMethods(int xIncrement, int yIncrement, GameBoard currentBoard, int stepCount, String outputFile);

    boolean attackCellsAround(GameBoard currentBoard);

    int fightToDeath(int xIncrement, int yIncrement, GameBoard currentBoard);
}
