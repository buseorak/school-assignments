public class Token implements Comparable<Token> {

    // Fields

    private final String ID;
    private final String PART_TO_BUY_FROM;
    private int tokenValue;                       // comparison priority factor

    // Constructor

    public Token(String id, String partToBuyFrom, int tokenValue) {
        ID = id;
        PART_TO_BUY_FROM = partToBuyFrom;
        this.tokenValue = tokenValue;
    }

    /* Compares two Token objects according to tokenValue where greater tokenValue corresponds to higher priority.
     * Returns a positive integer if the object's priority is higher than the object passed as parameter,
     * a negative integer if the object's priority if lower than the object passed as parameter, and
     * the integer 0 if they have equal priority. */
    @Override
    public int compareTo(Token tokenToCompare) {
        return (tokenValue - tokenToCompare.tokenValue);
    }

    // Getters and Setters

    protected String getID() {
        return ID;
    }

    protected String getPART_TO_BUY_FROM() {
        return PART_TO_BUY_FROM;
    }

    // Expects a positive integer and decreases the token value by that amount.
    protected void setTokenValue(int decreaseAmount) {
        tokenValue -= decreaseAmount;
    }

    protected void setTokenValueToZero() {
        tokenValue = 0;
    }

    protected int getTokenValue() {
        return tokenValue;
    }
}
