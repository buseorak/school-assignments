public class Person {

    // Fields

    private final int ID;
    private final String NAME;
    private final String SURNAME;
    private final String COUNTRY;

    // Constructor

    public Person(int id, String name, String surname, String country) {
        ID = id;
        NAME = name;
        SURNAME = surname;
        COUNTRY = country;
    }

    // Getters

    protected int getID() {
        return ID;
    }

    protected String getNAME() {
        return NAME;
    }

    protected String getSURNAME() {
        return SURNAME;
    }

    protected String getCOUNTRY() {
        return COUNTRY;
    }
}
