public class ProgramRunner {

    // Reads the input files by making calls to related functions, prints the output to the output file.
    protected static void execute(String partsFile, String itemsFile, String tokensFile, String tasksFile, String outputFile) {

        // Create the output file
        MyFileIOOperations.createOutputFile(outputFile);

        // Read the input files
        PartsFileReader.readPartsFile(partsFile);
        ItemsFileReader.readItemsFile(itemsFile);
        TokenBox tokenBox = TokensFileReader.readTokensFile(tokensFile);
        TasksFileReader.readTasksFile(tasksFile, tokenBox);

        // Write to the output file

        String output = "";
        for (Part part : Part.getPartsArray()) {
            if (part != null) {
                output = output.concat(part.toString());
            }
        }
        output = output.concat(tokenBox.toString());

        MyFileIOOperations.writeFile(output, outputFile);
    }
}
