import java.util.ArrayList;

public class Author {

    // Fields

    private final int AUTHOR_ID;
    private final String AUTHOR_NAME;
    private final String AUTHOR_UNIVERSITY;
    private final String AUTHOR_DEPARTMENT;
    private final String AUTHOR_EMAIL;

    private ArrayList<Integer> authorArticleIDs = new ArrayList<>();

    public static ArrayList<Author> authorsArray = new ArrayList<>();

    // Constructor

    public Author(int id, String name, String university, String department, String email) {
        AUTHOR_ID = id;
        AUTHOR_NAME = name;
        AUTHOR_UNIVERSITY = university;
        AUTHOR_DEPARTMENT = department;
        AUTHOR_EMAIL = email;
    }

    // Getters

    public int getAUTHOR_ID() {
        return AUTHOR_ID;
    }

    public String getAUTHOR_NAME() {
        return AUTHOR_NAME;
    }

    public String getAUTHOR_UNIVERSITY() {
        return AUTHOR_UNIVERSITY;
    }

    public String getAUTHOR_DEPARTMENT() {
        return AUTHOR_DEPARTMENT;
    }

    public String getAUTHOR_EMAIL() {
        return AUTHOR_EMAIL;
    }

    public ArrayList<Integer> getAuthorArticleIDs() {
        return authorArticleIDs;
    }

    // Setter

    public void setAuthorArticleIDs(int authorArticleID) {
        authorArticleIDs.add(authorArticleID);
    }
}
