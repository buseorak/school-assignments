import java.util.ArrayList;

public class Actor extends Performer {

    // Fields

    private final double HEIGHT;

    public static ArrayList<Actor> actorsArray = new ArrayList<>();

    // Constructor

    public Actor(int id, String name, String surname, String country, double height) {
        super(id, name, surname, country);
        HEIGHT = height;
    }

    // Getter

    protected double getHEIGHT() {
        return HEIGHT;
    }
}
