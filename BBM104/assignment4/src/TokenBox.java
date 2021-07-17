public class TokenBox {

    // Field

    private Queue<Token> tokenQueue = new Queue<>();

    // Empty Constructor

    public TokenBox() {}

    // Calls the enqueue method in the Queue class if the Token passed as parameter is not null.
    protected void enqueueToTokenQueue(Token token) {
        if (token != null) {
            tokenQueue.enqueue(token);
        }
    }

    // Calls the dequeue method in the Queue class, returns its value.
    protected Token dequeueFromTokenQueue(Token token) {
        return tokenQueue.dequeue(token);
    }

    // Spends the Token(s) according to the partName and itemCount that are passed as parameters.
    protected void spendToken(String partName, int itemCount) {
        boolean buyComplete = false;

        while(!buyComplete) {
            Token token = peekAtQueue(partName);

            if (token != null) {
                if (token.getTokenValue() > itemCount) {
                    token.setTokenValue(itemCount);
                    enqueueToTokenQueue(dequeueFromTokenQueue(token));
                    buyComplete = true;
                }
                else if (token.getTokenValue() == itemCount) {
                    token.setTokenValueToZero();
                    dequeueFromTokenQueue(token);
                    buyComplete = true;
                }
                else {
                    itemCount -= token.getTokenValue();
                    token.setTokenValueToZero();
                    dequeueFromTokenQueue(token);
                }
            }
        }
    }

    /* Returns a Token object by peeking at the tokenQueue and by considering the partName passed as parameter
     * or returns null if tokenQueue is empty or no Token that has the partName parameter as field is left in the tokenQueue. */
    private Token peekAtQueue(String partName) {
        if (!tokenQueue.isEmpty()) {
            for (Token token : tokenQueue) {
                if (token != null) {
                    if (token.getPART_TO_BUY_FROM().equals(partName)) {
                        return token;
                    }
                }
            }
        }
        return null;
    }

    // Overrides the toString method and returns a String containing information about the TokenBox object.
    public String toString() {
        String buildOnThis = "Token Box:\n";

        // The Stack of Tokens in order to reverse the priority queue correctly, without Collections.sort method
        Stack<Token> tokenStack = new Stack<>();

        for (Token token : tokenQueue) {
            tokenStack.push(token);
        }

        int stackSize = tokenStack.size();

        if (stackSize == 0) {
            return buildOnThis;
        }

        else {
            for (int i = 0; i < stackSize; i++) {
                Token poppedToken = tokenStack.pop();
                if (poppedToken != null) {
                    buildOnThis = buildOnThis.concat(poppedToken.getID() + " " + poppedToken.getPART_TO_BUY_FROM() + " " +
                            poppedToken.getTokenValue() + "\n");
                }
            }
            return buildOnThis.replaceAll("\n$", "");
        }
    }
}
