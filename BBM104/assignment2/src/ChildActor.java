import java.util.ArrayList;

public class ChildActor extends Performer {

    // Fields

    private final int AGE;

    public static ArrayList<ChildActor> childActorsArray = new ArrayList<>();

    // Constructor

    public ChildActor(int id, String name, String surname, String country, int age) {
        super(id, name, surname, country);
        AGE = age;
    }

    // Getter

    protected int getAGE() {
        return AGE;
    }
}
