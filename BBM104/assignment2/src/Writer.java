import java.util.ArrayList;

public class Writer extends Artist {

    // Fields

    private final String WRITING_STYLE;

    public static ArrayList<Writer> writersArray = new ArrayList<>();

    // Constructor

    public Writer(int id, String name, String surname, String country, String style) {
        super(id, name, surname, country);
        WRITING_STYLE = style;
    }

    // Getter

    protected String getWRITING_STYLE() {
        return WRITING_STYLE;
    }
}
