import java.util.ArrayList;

public class TokensFileReader {

    // Reads the tokens file, creates Token objects, enqueues them to the TokenBox created and returns that box.
    protected static TokenBox readTokensFile(String fileName) {
        ArrayList<String> lines = MyFileIOOperations.readFile(fileName);

        TokenBox tokenBox = new TokenBox();

        if (lines != null) {
            for (String line : lines) {
                if (!(line.equals("") || line.equals("\n"))) {
                    String[] lineData = line.split(" ");
                    Token newTokenObj = new Token(lineData[0], lineData[1], Integer.parseInt(lineData[2]));
                    tokenBox.enqueueToTokenQueue(newTokenObj);
                }
            }
        }

        return tokenBox;
    }
}
