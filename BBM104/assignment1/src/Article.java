import java.util.ArrayList;

public class Article {

    // Fields

    private final int ARTICLE_ID;
    private final String ARTICLE_NAME;
    private final String ARTICLE_PUBLISHER_NAME;
    private final int ARTICLE_PUBLISH_YEAR;

    public static ArrayList<Article> articleArray = new ArrayList<>();

    // Constructor

    public Article(int id, String name, String publisher, int year) {
        ARTICLE_ID = id;
        ARTICLE_NAME = name;
        ARTICLE_PUBLISHER_NAME = publisher;
        ARTICLE_PUBLISH_YEAR = year;
    }

    // Getters

    public int getARTICLE_ID() {
        return ARTICLE_ID;
    }

    public String getARTICLE_NAME() {
        return ARTICLE_NAME;
    }

    public String getARTICLE_PUBLISHER_NAME() {
        return ARTICLE_PUBLISHER_NAME;
    }

    public int getARTICLE_PUBLISH_YEAR() {
        return ARTICLE_PUBLISH_YEAR;
    }
}
