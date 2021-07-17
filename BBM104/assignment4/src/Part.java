import java.util.ArrayList;

public class Part {

    // Fields

    private final String NAME;
    private Stack<Item> itemStack = new Stack<>();

    private static ArrayList<Part> partsArray = new ArrayList<>();

    // Constructor

    public Part(String name) {
        NAME = name;
    }

    // Overridden toString method that returns a String containing needed information about the Part object
    public String toString() {
        String buildOnThis = NAME + ":\n";

        int stackSize = itemStack.size();

        if (stackSize == 0) {
            return buildOnThis.concat("\n---------------\n");
        }

        else {
            for (int i = 0; i < stackSize; i++) {
                Item poppedItem = itemStack.pop();
                if (poppedItem != null) {
                    buildOnThis = buildOnThis.concat(poppedItem.getID() + "\n");
                }
            }
            return buildOnThis.concat("---------------\n");
        }
    }

    // Getters and Setters

    protected String getNAME() {
        return NAME;
    }

    protected void pushToItemStack(Item item) {
        itemStack.push(item);
    }

    protected Item popFromItemStack() {
        return itemStack.pop();
    }

    protected static ArrayList<Part> getPartsArray() {
        return partsArray;
    }

    protected static void setPartsArray(Part part) {
        partsArray.add(part);
    }
}
