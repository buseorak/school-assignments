import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Read input files taken in as command-line arguments
        PeopleFileReader.readPeopleFile(args[0]);
        FilmsFileReader.readFilmsFile(args[1]);
        ArrayList<String> commands = MyFileIOOperations.readFile(args[2]);

        // Create output file
        String outputFile = args[3];
        MyFileIOOperations.createOutputFile(outputFile);

        // Execute the commands
        assert commands != null;
        CommandRunner.execute(commands, outputFile);
    }
}
