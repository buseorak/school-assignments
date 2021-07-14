import java.util.ArrayList;

public class Documentary extends Film {

    // Fields

    private final String RELEASE_DATE;

    public static ArrayList<Documentary> documentariesArray = new ArrayList<>();

    // Constructor

    public Documentary(int id, String title, String language, ArrayList<Director> directors, int runtime,
                       String country, ArrayList<Performer> cast, String releaseDate) {
        super(id, title, language, runtime, country, directors, cast);
        RELEASE_DATE = releaseDate;
    }

    // Getter

    protected String getRELEASE_DATE() {
        return RELEASE_DATE;
    }
}
