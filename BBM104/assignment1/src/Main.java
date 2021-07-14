import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        AuthorFileOperations.readAuthorFile(args[0]);
        MyFileIOOperations.createOutputFile("output.txt");
        ArrayList<String> commands = MyFileIOOperations.readFile(args[1]);

        assert commands != null;
        CommandOperations.executeCommand(commands);
    }
}
