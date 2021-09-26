public class Main {

    public static void main(String[] args) {

        // Read initials file and create a GameBoard object
        GameBoard board = InitialsFileReader.readInitialsFile(args[0]);

        // Read the commands file
        CommandsFileReader.readCommandsFile(args[1], board, args[2]);
    }
}
