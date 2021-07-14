import java.util.ArrayList;
import java.util.LinkedHashMap;

public class User extends Person {

    // Fields

    private LinkedHashMap<Integer, Double> filmIDsAndRates = new LinkedHashMap<>();

    public static ArrayList<User> usersArray = new ArrayList<>();

    // Constructor

    public User(int id, String name, String surname, String country) {
        super(id, name, surname, country);
    }

    // Getter and Setter

    protected LinkedHashMap<Integer, Double> getFilmIDsAndRates() {
        return filmIDsAndRates;
    }

    protected void setFilmIDsAndRates(int filmID, double rating) {
        filmIDsAndRates.put(filmID, rating);
    }
}
