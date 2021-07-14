import java.util.ArrayList;

public class TVSeries extends Film {

    // Fields

    private final String START_DATE;
    private final String END_DATE;
    private final int NUMBER_OF_SEASONS;
    private final int NUMBER_OF_EPISODES;
    private final ArrayList<String> GENRE;
    private final ArrayList<Writer> WRITERS;

    public static ArrayList<TVSeries> TVSeriesArray = new ArrayList<>();

    // Constructor

    public TVSeries(int id, String title, String language, ArrayList<Director> directors, int runtime, String country,
                    ArrayList<Performer> cast, ArrayList<String> genre, ArrayList<Writer> writers, String start,
                    String end, int seasons, int episodes) {
        super(id, title, language, runtime, country, directors, cast);
        START_DATE = start;
        END_DATE = end;
        NUMBER_OF_SEASONS = seasons;
        NUMBER_OF_EPISODES = episodes;
        GENRE = genre;
        WRITERS = writers;
    }

    // Getters

    protected String getSTART_DATE() {
        return START_DATE;
    }

    protected String getEND_DATE() {
        return END_DATE;
    }

    protected int getNUMBER_OF_SEASONS() {
        return NUMBER_OF_SEASONS;
    }

    protected int getNUMBER_OF_EPISODES() {
        return NUMBER_OF_EPISODES;
    }

    protected ArrayList<String> getGENRE() {
        return GENRE;
    }

    protected ArrayList<Writer> getWRITERS() {
        return WRITERS;
    }
}
