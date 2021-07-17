import java.util.ArrayList;

public class ItemsFileReader {

    // Reads the items file, creates the Item objects and places them to the related Stack.
    protected static void readItemsFile(String fileName) {
        ArrayList<String> lines = MyFileIOOperations.readFile(fileName);

        if (lines != null) {
            for (String line : lines) {
                if (!(line.equals("") || line.equals("\n"))) {
                    String[] lineData = line.split(" ");
                    Item newItemObject = new Item(lineData[0]);

                    for (Part part : Part.getPartsArray()) {
                        if (part.getNAME().equals(lineData[1])) {
                            part.pushToItemStack(newItemObject);
                            break;
                        }
                    }
                }
            }
        }
    }
}
