import java.util.ArrayList;
import java.util.Collections;

public class CommandOperations {

    public static void executeCommand(ArrayList<String> commands) {

        String previousCommand = "";

        for (String command : commands) {
            if (command.startsWith("read")) {
                read(command);
            }
            else if (command.equals("completeAll")) {
                completeAll();
            }
            else if (command.equals("sortedAll")) {
                sortedAll();
            }
            else if (command.startsWith("del")) {
                del(command);
            }
            else if (command.equals("list")) {
                list(previousCommand);
            }

            previousCommand = command;
        }
    }

    public static void read(String command) {
        String[] commandAndFileName = command.split(" ");
        ArticleFileOperations.readArticleFile(commandAndFileName[1]);
    }

    public static void completeAll() {
        for (Author authorObject : Author.authorsArray) {
            if (authorObject.getAuthorArticleIDs().size() < 5) {
                ArrayList<Article> addThese = new ArrayList<>();
                for (Article articleObject : Article.articleArray) {
                    if ((Integer.toString(articleObject.getARTICLE_ID()).startsWith(Integer.toString(authorObject.getAUTHOR_ID()))) &&
                            !(authorObject.getAuthorArticleIDs().contains(articleObject.getARTICLE_ID()))) {
                        addThese.add(articleObject);
                    }
                }
                for (Article art : addThese) {
                    authorObject.setAuthorArticleIDs(art.getARTICLE_ID());
                }
            }
        }
    }

    public static void sortedAll() {
        for (Author authorObject : Author.authorsArray) {
            Collections.sort(authorObject.getAuthorArticleIDs());
        }
    }

    public static void del(String command) {
        String[] commandAndAuthorID = command.split(" ");
        int authorToBeDeletedID = Integer.parseInt(commandAndAuthorID[1]);
        Author deleting = new Author(authorToBeDeletedID, "name", "university", "department", "email");
        for (Author deleteThis : Author.authorsArray) {
            if (deleteThis.getAUTHOR_ID() == authorToBeDeletedID) {
                deleting = deleteThis;
                break;
            }
        }
        Author.authorsArray.remove(deleting);
        deleting = null;
        System.gc();
    }

    public static void list(String previousCommand) {
        // operations for command "list" after command "completeAll"
        if (previousCommand.equals("completeAll")) {
            MyFileIOOperations.writeFile("*****************************" +
                    "completeAll Successful****************************\n");
        }

        // operations for command "list" after command "sortedAll"
        else if (previousCommand.equals("sortedAll")) {
            MyFileIOOperations.writeFile("******************************" +
                    "SortedAll Successful*****************************\n");
        }

        // operations for command "list" after command "del"
        else if (previousCommand.startsWith("del")) {
            MyFileIOOperations.writeFile("*********************************" +
                    "del Successful********************************\n");
        }

        // file writing operations
        MyFileIOOperations.writeFile("--------------------------------------" +
                "List-------------------------------------");

        for (Author authorObj : Author.authorsArray) {
            AuthorFileOperations.writeAuthorLine(authorObj);
            ArticleFileOperations.writeArticleLines(authorObj);
        }

        MyFileIOOperations.writeFile("--------------------------------------" +
                "End--------------------------------------\n");
    }
}
