import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Creating the output file
        MyFileIOOperations.createOutputFile("output.txt");
        
        // Reading the input files taken as command-line arguments
        AuthorFileOperations.readAuthorFile(args[0]);
        ArrayList<String> commands = MyFileIOOperations.readFile(args[1]);

        // Executing the commands
        assert commands != null;
        CommandOperations.executeCommand(commands);
    }
}
