import java.util.ArrayList;

public class PartsFileReader {

    // Reads the parts file, creates Part objects and stores them in the partsArray.
    protected static void readPartsFile(String fileName) {
        ArrayList<String> lines = MyFileIOOperations.readFile(fileName);

        if (lines != null) {
            for (String line : lines) {
                if (!(line.equals("") || line.equals("\n"))) {
                    Part newPartObject = new Part(line);
                    if (!Part.getPartsArray().contains(newPartObject)) {
                        Part.setPartsArray(newPartObject);
                    }
                }
            }
        }
    }
}
