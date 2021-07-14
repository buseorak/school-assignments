import java.util.ArrayList;

public class Film {

    // Fields

    private final int ID;
    private final String TITLE;
    private final String LANGUAGE;
    private final int RUNTIME;
    private final String COUNTRY;
    private final ArrayList<Director> DIRECTORS;
    private final ArrayList<Performer> CAST;
    private double rating = 0.0;
    private int raters = 0;

    public static ArrayList<Film> filmsArray = new ArrayList<>();

    // Constructor

    public Film(int id, String title, String language, int runtime, String country,
                ArrayList<Director> directors, ArrayList<Performer> cast) {
        ID = id;
        TITLE = title;
        LANGUAGE = language;
        RUNTIME = runtime;
        COUNTRY = country;
        DIRECTORS = directors;
        CAST = cast;
    }

    // updates the rating during removal
    protected void removeRating(double rating) {
        if (raters == 1) {
            this.rating = 0.0;
        }
        else {
            this.rating = ((this.rating * raters) - rating) / (raters - 1);
        }
        raters--;
    }

    // Getters and Setters

    protected double getRating() {
        return rating;
    }

    protected void setRating(double rating) {
        this.rating = ((this.rating * raters) + rating) / (raters + 1);
        raters ++;
    }

    protected int getRaters() {
        return raters;
    }

    protected int getID() {
        return ID;
    }

    protected String getTITLE() {
        return TITLE;
    }

    protected String getLANGUAGE() {
        return LANGUAGE;
    }

    protected int getRuntime() {
        return RUNTIME;
    }

    protected String getCOUNTRY() {
        return COUNTRY;
    }

    protected ArrayList<Director> getDIRECTORS() {
        return DIRECTORS;
    }

    protected ArrayList<Performer> getCAST() {
        return CAST;
    }
}
