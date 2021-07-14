import java.util.ArrayList;

public class StuntPerformer extends Performer {

    // Fields

    private final double HEIGHT;
    private final ArrayList<Integer> REAL_ACTOR_IDS;

    public static ArrayList<StuntPerformer> stuntPerformersArray = new ArrayList<>();

    // Constructor

    public StuntPerformer(int id, String name, String surname, String country, double height, ArrayList<Integer> reals) {
        super(id, name, surname, country);
        HEIGHT = height;
        REAL_ACTOR_IDS = reals;
    }

    // Getter

    protected double getHEIGHT() {
        return HEIGHT;
    }
}
