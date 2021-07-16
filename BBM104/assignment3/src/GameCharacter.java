import java.util.ArrayList;

public abstract class GameCharacter implements Attacker {

    // Fields

    private int currentHP;
    private ArrayList<Integer> currentPosition;

    private final String CHARACTER_ID;
    private final int AP;
    private final int DEFAULT_HP;
    private final int MAXIMUM_MOVE;

    protected static ArrayList<GameCharacter> charactersArray = new ArrayList<>();

    // Constructor

    public GameCharacter(String ID, int HP, ArrayList<Integer> coordinates, int AP, int maximumMove) {
        CHARACTER_ID = ID;
        currentHP = HP;
        currentPosition = coordinates;
        this.AP = AP;
        DEFAULT_HP = HP;
        MAXIMUM_MOVE = maximumMove;
    }

    // Implement the methods in the interface Attacker

    /* Moves the GameCharacter according to the parameters x-y increments and current board if the cell is empty.
     * Returns 0 if the character moved to an empty cell,
     * 1 if the character tried to move into an ally's cell, and
     * 2 if the character tried to move into an enemy's cell.
     * Throws GameBoundariesExceededException if the cell the character is trying to move is out of bounds of the game board */
    @Override
    public int move(int xIncrement, int yIncrement, GameBoard currentBoard) throws GameBoardBoundariesExceededException {
        ArrayList<Integer> targetCoordinates = HelperMethods.createArrayList(currentPosition.get(0) + xIncrement,
                currentPosition.get(1) + yIncrement);

        if (!currentBoard.getCoordinatesAndCellContent().containsKey(targetCoordinates)) {
            throw new GameBoardBoundariesExceededException();
        }
        else {
            String targetContent = currentBoard.getCoordinatesAndCellContent().get(targetCoordinates);

            if (targetContent.equals("  ")) {
                currentBoard.setCoordinatesAndCellContent(currentPosition, "  ");
                currentBoard.setCoordinatesAndCellContent(targetCoordinates, CHARACTER_ID);
                currentPosition = targetCoordinates;
                return 0;
            }
            else {
                for (GameCharacter character : GameCharacter.charactersArray) {
                    if (character.CHARACTER_ID.equals(targetContent)) {
                        if (((this instanceof Zorde) && (character instanceof Zorde)) ||
                                ((this instanceof Calliance) && (character instanceof Calliance))) {
                            return 1;
                        }
                        else {
                            return 2;
                        }
                    }
                }
            }
            return -1;
        }
    }

    /* Attacks the neighboring cells and
     * returns true if the attacker's side has won, false if no side has won */
    @Override
    public boolean attackCellsAround(GameBoard currentBoard) {
        ArrayList<ArrayList<Integer>> coordinatesOfCellsAround = getCoordinatesOfNeighboringCells(currentBoard);

        boolean defenderIsDead = false;

        for (ArrayList<Integer> targetCoordinates : coordinatesOfCellsAround) {
            String targetContent = currentBoard.getCoordinatesAndCellContent().get(targetCoordinates);

            if (!targetContent.equals("  ")) {
                GameCharacter defender = null;
                for (GameCharacter character : GameCharacter.charactersArray) {
                    if (character.CHARACTER_ID.equals(targetContent)) {
                        defender = character;
                        if (((this instanceof Zorde) && (defender instanceof Calliance)) ||
                                ((this instanceof Calliance) && (defender instanceof Zorde))) {
                            if ((this instanceof Elf) && (((Elf) this).isAtFinalStep())) {
                                defender.decreaseAndSetCurrentHP((((Elf) this).getRANGED_AP()));
                            }
                            else {
                                defender.decreaseAndSetCurrentHP(AP);
                            }
                            defenderIsDead = defender.checkIfDead();
                            break;
                        }
                    }
                }
                if (defenderIsDead) {
                    assert defender != null;
                    currentBoard.setCoordinatesAndCellContent(defender.currentPosition, "  ");
                    GameCharacter.charactersArray.remove(defender);
                }
            }
        }

        for (GameCharacter character : GameCharacter.charactersArray) {
            if (((this instanceof Zorde) && (character instanceof Calliance)) ||
                    ((this instanceof Calliance) && (character instanceof Zorde))) {
                return false;
            }
        }
        return true;
    }

    /* Performs the necessary operations for fight to death.
     * Returns 0 is no side has won,
     * 1 if the attacker's side has won,
     * 2 if the attacker's side has lost and defender's side has won, and
     * 3 if no character has remained in the board and there is a tie situation */
    @Override
    public int fightToDeath(int xIncrement, int yIncrement, GameBoard currentBoard) {
        ArrayList<Integer> targetCoordinates = HelperMethods.createArrayList(currentPosition.get(0) + xIncrement,
                currentPosition.get(1) + yIncrement);
        String targetContent = currentBoard.getCoordinatesAndCellContent().get(targetCoordinates);

        GameCharacter defender = null;
        for (GameCharacter character : GameCharacter.charactersArray) {
            if (character.CHARACTER_ID.equals(targetContent)) {
                defender = character;
                defender.decreaseAndSetCurrentHP(AP);
            }
        }

        assert defender != null;
        if (currentHP == defender.currentHP) {
            currentBoard.setCoordinatesAndCellContent(currentPosition, "  ");
            currentBoard.setCoordinatesAndCellContent(defender.currentPosition, "  ");
            GameCharacter.charactersArray.remove(this);
            GameCharacter.charactersArray.remove(defender);
        }
        else if (defender.currentHP > currentHP) {
            defender.decreaseAndSetCurrentHP(currentHP);
            currentBoard.setCoordinatesAndCellContent(currentPosition, "  ");
            GameCharacter.charactersArray.remove(this);
        }
        else {
            decreaseAndSetCurrentHP(defender.currentHP);
            currentBoard.setCoordinatesAndCellContent(currentPosition, "  ");
            currentBoard.setCoordinatesAndCellContent(defender.currentPosition, CHARACTER_ID);
            currentPosition = defender.currentPosition;
            GameCharacter.charactersArray.remove(defender);
        }

        boolean otherAllyExists = false;
        boolean otherEnemyExists = false;

        for (GameCharacter character : GameCharacter.charactersArray) {
            if (((this instanceof Zorde) && (character instanceof Zorde)) ||
                    ((this instanceof Calliance) && (character instanceof Calliance))) {
                otherAllyExists = true;
            }
            if (((this instanceof Zorde) && (character instanceof Calliance)) ||
                    ((this instanceof Calliance) && (character instanceof Zorde))) {
                otherEnemyExists = true;
            }
        }

        if (otherAllyExists && otherEnemyExists) {
            return 0;
        }

        else if (otherAllyExists && (!otherEnemyExists)) {
            return 1;
        }

        else if ((!otherAllyExists) && (otherEnemyExists)) {
            if (GameCharacter.charactersArray.contains(this)) {
                return 0;
            }
            else {
                return 2;
            }
        }

        else {
            if (GameCharacter.charactersArray.contains(this)) {
                return 1;
            }
            else {
                return 3;
            }
        }
    }

    /* Calls the related methods according to the return value of method move, catches if an exception raises.
     * Returns 0 if a FinalStepAttacker has moved without its move sequence being interrupted,
     * 1 if the attacker' move sequence is interrupted without any winning situations,
     * 2 if the attacker's side has won by attacking the cells around and has more moves,
     * 3 if some side has won and the attacker has no more moves, and
     * 4 if there is a tie */
    @Override
    public int callAttackingMethods(int xIncrement, int yIncrement, GameBoard currentBoard, int stepCount, String outputFile) {
        try {
            int moveReturnValue = move(xIncrement, yIncrement, currentBoard);

            if (moveReturnValue == 0) {
                if ((this instanceof EveryStepAttacker) || ((this instanceof FinalStepAttacker) && (stepCount == getMAXIMUM_MOVE()))) {
                    boolean attackCellsAroundReturnValue = attackCellsAround(currentBoard);

                    if (attackCellsAroundReturnValue) {
                        if (stepCount == getMAXIMUM_MOVE()) {
                            MyFileIOOperations.writeFile(currentBoard.printBoard() +
                                    HelperMethods.printCharacterInformation(), outputFile);
                            currentBoard.setWinner(this, true);
                            return 3;
                        }
                        else {
                            return 2;
                        }
                    }
                    else {
                        if (stepCount == getMAXIMUM_MOVE()) {
                            MyFileIOOperations.writeFile(currentBoard.printBoard() +
                                    HelperMethods.printCharacterInformation(), outputFile);
                        }
                    }
                }
                return 0;
            }

            else if (moveReturnValue == 1) {
                MyFileIOOperations.writeFile(currentBoard.printBoard() +
                        HelperMethods.printCharacterInformation(), outputFile);
                return 1;
            }

            else {
                int fightToDeathReturnValue = fightToDeath(xIncrement, yIncrement, currentBoard);

                MyFileIOOperations.writeFile(currentBoard.printBoard() +
                        HelperMethods.printCharacterInformation(), outputFile);

                if (fightToDeathReturnValue == 0) {
                    return 1;
                }

                if (fightToDeathReturnValue == 1) {
                    currentBoard.setWinner(this, true);
                    return 3;
                }

                else if (fightToDeathReturnValue == 2) {
                    currentBoard.setWinner(this, false);
                    return 3;
                }

                else {
                    MyFileIOOperations.writeFile("\nGame Finished\nTie between Zorde and Calliance\n", outputFile);
                    System.exit(0);
                    return 4;
                }
            }
        }
        catch (GameBoardBoundariesExceededException e) {
            if (stepCount != 1) {
                MyFileIOOperations.writeFile(currentBoard.printBoard() +
                        HelperMethods.printCharacterInformation(), outputFile);
            }
            MyFileIOOperations.writeFile("Error : Game board boundaries are exceeded. " +
                    "Input line ignored.\n\n", outputFile);
            return 1;
        }
    }

    // End of interface method implementations

    /* Takes a GameBoard object as parameter,
     * returns an ArrayList of coordinates around its current location in the current state of the board */
    protected ArrayList<ArrayList<Integer>> getCoordinatesOfNeighboringCells(GameBoard currentBoard) {
        int currentX = getCurrentPosition().get(0);
        int currentY = getCurrentPosition().get(1);

        ArrayList<ArrayList<Integer>> coordinatesAround = new ArrayList<>();

        int[] increments = new int[] {-1, 0, 1};

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

    // Helper method that returns true if the character is dead
    private boolean checkIfDead() {
        return (currentHP == 0);
    }

    // Returns the name of the character's side, either Zorde or Calliance
    protected String getCharactersSide() {
        if (this instanceof Zorde) {
            return "Zorde";
        }
        else {
            return "Calliance";
        }
    }

    // Returns the name of the character's opponents' side, either Zorde or Calliance
    protected String getEnemysSide() {
        if (getCharactersSide().equals("Zorde")) {
            return "Calliance";
        }
        else {
            return "Zorde";
        }
    }

    // Getters and Setters

    protected String getCHARACTER_ID() {
        return CHARACTER_ID;
    }

    protected int getCurrentHP() {
        return currentHP;
    }

    // Increases the current HP value by restricting it with an upper bound, the default HP
    protected void increaseAndSetCurrentHP(int increaseAmount) {
        currentHP += increaseAmount;
        if (currentHP > DEFAULT_HP) {
            currentHP = DEFAULT_HP;
        }
    }

    // Decreases the current HP value by restricting it with a lower bound, 0
    protected void decreaseAndSetCurrentHP(int decreaseAmount) {
        currentHP -= decreaseAmount;
        if (currentHP < 0) {
            currentHP = 0;
        }
    }

    protected ArrayList<Integer> getCurrentPosition() {
        return currentPosition;
    }

    protected int getDEFAULT_HP() {
        return DEFAULT_HP;
    }

    protected int getMAXIMUM_MOVE() {
        return MAXIMUM_MOVE;
    }
}
