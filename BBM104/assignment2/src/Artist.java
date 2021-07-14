import java.util.ArrayList;

public class Artist extends Person {

    // Field

    public static ArrayList<Artist> artistsArray = new ArrayList<>();

    // Constructor

    public Artist(int id, String name, String surname, String country) {
        super(id, name, surname, country);
    }
}
