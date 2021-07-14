import java.util.ArrayList;

public class Performer extends Artist {

    // Field

    public static ArrayList<Performer> performersArray = new ArrayList<>();

    // Constructor

    public Performer(int id, String name, String surname, String country) {
        super(id, name, surname, country);
    }
}
