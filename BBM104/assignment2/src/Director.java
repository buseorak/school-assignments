import java.util.ArrayList;

public class Director extends Artist {

    // Fields

    private final String AGENT;

    public static ArrayList<Director> directorsArray = new ArrayList<>();

    // Constructor

    public Director(int id, String name, String surname, String country, String agent) {
        super(id, name, surname, country);
        AGENT = agent;
    }

    // Getter

    protected String getAGENT() {
        return AGENT;
    }
}
