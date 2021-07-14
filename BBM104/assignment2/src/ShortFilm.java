import java.util.ArrayList;

public class ShortFilm extends Film {

    // Fields

    private final String RELEASE_DATE;
    private final ArrayList<Writer> WRITERS;
    private final ArrayList<String> GENRE;

    public static ArrayList<ShortFilm> shortFilmsArray = new ArrayList<>();

    // Constructor

    public ShortFilm(int id, String title, String language, ArrayList<Director> directors, int runtime,
                     String country, ArrayList<Performer> cast, ArrayList<String> genre, String releaseDate,
                     ArrayList<Writer> writers) {
        super(id, title, language, runtime, country, directors, cast);
        RELEASE_DATE = releaseDate;
        WRITERS = writers;
        GENRE = genre;
    }

    // Getters

    protected String getRELEASE_DATE() {
        return RELEASE_DATE;
    }

    protected ArrayList<Writer> getWRITERS() {
        return WRITERS;
    }

    protected ArrayList<String> getGENRE() {
        return GENRE;
    }
}
