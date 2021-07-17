import java.util.ArrayList;

public class TasksFileReader {

    // Reads the tasks file, expects two types of commands (either PUT or BUY), performs the necessary operations for them.
    protected static void readTasksFile(String fileName, TokenBox tokenBox) {
        ArrayList<String> lines = MyFileIOOperations.readFile(fileName);

        if (lines != null) {
            for (String line : lines) {

                if (line.startsWith("PUT")) {
                    String[] lineData = line.replace("PUT\t", "").split("\t");

                    for (String operation : lineData) {
                        String[] operationData = operation.split(",");

                        for (Part part : Part.getPartsArray()) {
                            if (part.getNAME().equals(operationData[0])) {

                                for (int i = 0; i < operationData.length; i++) {
                                    if (i != 0) {
                                        Item newItemObject = new Item(operationData[i]);
                                        part.pushToItemStack(newItemObject);
                                    }
                                }
                            }
                        }
                    }
                }

                else if (line.startsWith("BUY")) {
                    String[] lineData = line.replace("BUY\t", "").split("\t");

                    for (String operation : lineData) {
                        String[] operationData = operation.split(",");

                        for (Part part : Part.getPartsArray()) {
                            if (part.getNAME().equals(operationData[0])) {
                                tokenBox.spendToken(operationData[0], Integer.parseInt(operationData[1]));

                                for (int i = 0; i < Integer.parseInt(operationData[1]); i++) {
                                    part.popFromItemStack();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
