import java.util.ArrayList;
import java.util.Arrays;

public class AuthorFileOperations {

    /* This method reads the author.txt file,
       creates Author objects according to the data obtained from the file content,
       and adds the objects to the authorsArray */
    public static void readAuthorFile(String fileName) {
        ArrayList<String> authorDataLines = MyFileIOOperations.readFile(fileName);

        assert authorDataLines != null;
        for (String authorDataLine : authorDataLines) {
            if (authorDataLine.startsWith("AUTHOR")) {
                String[] authorData = authorDataLine.split(" ");

                if (authorData.length == 2) {
                    Author newAuthor = new Author(Integer.parseInt(authorData[1]), "", "", "", "");
                    Author.authorsArray.add(newAuthor);
                }

                else {
                    Author newAuthor = new Author(Integer.parseInt(authorData[1]),
                            authorData[2], authorData[3], authorData[4], authorData[5]);

                    if (authorData.length > 6) {
                        for (String newAuthorArticleID : Arrays.copyOfRange(authorData, 6, (authorData.length))) {
                            newAuthor.setAuthorArticleIDs(Integer.parseInt(newAuthorArticleID));
                        }
                    }

                    Author.authorsArray.add(newAuthor);
                }
            }
        }
    }

    /* This method takes an Author object as parameter and
       prepares the author data to be written to the output file */
    public static void writeAuthorLine(Author theAuthor) {
        MyFileIOOperations.writeFile("\nAuthor:" + theAuthor.getAUTHOR_ID());
        if (!theAuthor.getAUTHOR_NAME().equals("")) {
            MyFileIOOperations.writeFile("\t" + theAuthor.getAUTHOR_NAME() + "\t" + theAuthor.getAUTHOR_UNIVERSITY() + "\t" +
                    theAuthor.getAUTHOR_DEPARTMENT() + "\t" + theAuthor.getAUTHOR_EMAIL());
        }
    }
}
