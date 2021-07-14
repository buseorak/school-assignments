import java.util.ArrayList;

public class CommandHelper {

    // creates a list of IDs of objects of the class passed as parameter
    protected static ArrayList<Integer> createArrayList(String className) {
        ArrayList<Integer> idList = new ArrayList<>();
        switch (className) {
            case "User":
                for (User user : User.usersArray) {
                    idList.add(user.getID());
                }
                break;
            case "Film":
                for (Film film : Film.filmsArray) {
                    idList.add(film.getID());
                }
                break;
            case "Director":
                for (Director director : Director.directorsArray) {
                    idList.add(director.getID());
                }
                break;
            case "Writer":
                for (Writer writer : Writer.writersArray) {
                    idList.add(writer.getID());
                }
                break;
            case "Performer":
                for (Performer performer : Performer.performersArray) {
                    idList.add(performer.getID());
                }
                break;
        }
        return idList;
    }

    // returns true if both userID and filmID passed as parameters exist, false otherwise
    protected static boolean checkExistence(int userID, int filmID, String fileName) {
        ArrayList<Integer> userIDs = createArrayList("User");
        ArrayList<Integer> filmIDs = createArrayList("Film");
        if ((!userIDs.contains(userID)) || (!filmIDs.contains(filmID))) {
            MyFileIOOperations.writeFile("Command Failed\nUser ID: " + userID + "\nFilm ID: " + filmID +
                    "\n", fileName);
            return false;
        }
        return true;
    }

    // returns the string rating passed as first parameter converted into the form passed as second parameter
    protected static String prepareRating(String rating, String conversionType) {
        if (conversionType.equals("to point")) {
            if (rating.contains(",")) {
                rating = rating.replace(',', '.');
            }
        }
        else if (conversionType.equals("to comma")) {
            if (rating.contains(".")) {
                if (Double.parseDouble(rating) % 1 == 0) {
                    rating = rating.substring(0, rating.indexOf('.'));
                }
                else if (rating.substring(rating.indexOf('.')).length() > 5) {
                    int infiniteDecimal = Integer.parseInt(String.valueOf(rating.charAt(rating.indexOf('.') + 1)));
                    if (infiniteDecimal == 9) {
                        int wholeNumber = Integer.parseInt(rating.substring(0, rating.indexOf('.')));
                        rating = String.valueOf(wholeNumber + 1);
                    }
                    else {
                        if (infiniteDecimal >= 5) {
                            infiniteDecimal++;
                        }
                        rating = rating.replace(rating.substring(rating.indexOf('.') + 1),
                                String.valueOf(infiniteDecimal)).replace('.', ',');
                    }
                }
                else {
                    rating = rating.replace('.', ',');
                }
            }
        }
        return rating;
    }

    // returns true if the Artist passed as parameter exists, false otherwise
    protected static boolean checkArtistExistence(String className, String commandArtists) {
        boolean contains = true;

        ArrayList<Integer> artistIDs = createArrayList(className);
        String[] artistsToBeChecked = commandArtists.split(",");

        for (String artistToBeChecked : artistsToBeChecked) {
            if (!artistIDs.contains(Integer.parseInt(artistToBeChecked))) {
                contains = false;
            }
        }
        return contains;
    }

    // returns genre in a concatenated form, separated by commas
    protected static String concatenateGenre(ArrayList<String> genres) {
        String str = "";
        for (String genre : genres) {
            str = str.concat(genre + ", ");
        }
        str = str.replaceAll(", $", "\n");
        return str;
    }

    // returns Writer names in a concatenated form, separated by commas
    protected static String concatenateWriters(ArrayList<Writer> writers) {
        String str = "Writers: ";
        for (Writer writer : writers) {
            str = str.concat(writer.getNAME() + " " + writer.getSURNAME() + ", ");
        }
        str = str.replaceAll(", $", "\n");
        return str;
    }

    // prints the output of methods listFeatureFilmsBefore and listFeatureFilmsAfter
    protected static void listFeatureFilmsHelper(ArrayList<FeatureFilm> featureFilms, String fileName) {
        if (featureFilms.size() == 0) {
            MyFileIOOperations.writeFile("No result\n", fileName);
        }
        else {
            for (FeatureFilm ff : featureFilms) {
                MyFileIOOperations.writeFile("Film title: " + ff.getTITLE() + " (" +
                        ff.getRELEASE_DATE().substring(ff.getRELEASE_DATE().lastIndexOf('.') + 1) + ")\n" +
                        ff.getRuntime() + " min\n" + "Language: " + ff.getLANGUAGE() + "\n", fileName);
                if (featureFilms.indexOf(ff) != featureFilms.size() - 1) {
                    MyFileIOOperations.writeFile("\n", fileName);
                }
            }
        }
    }
}
