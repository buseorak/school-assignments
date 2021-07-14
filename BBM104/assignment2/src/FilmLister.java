import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class FilmLister {

    // lists the Films the User has rated so far
    protected static void listUserRates(String[] command, String fileName) {
        ArrayList<Integer> userIDs = CommandHelper.createArrayList("User");

        if (!userIDs.contains(Integer.parseInt(command[2]))) {
            MyFileIOOperations.writeFile("Command Failed\nUser ID: " + command[2] + "\n", fileName);
        }
        else {
            for (User user : User.usersArray) {
                if (user.getID() == Integer.parseInt(command[2])) {
                    if (user.getFilmIDsAndRates().size() == 0) {
                        MyFileIOOperations.writeFile("There is not any ratings so far\n", fileName);
                    }
                    else {
                        for (int filmIDFromUser : user.getFilmIDsAndRates().keySet()) {
                            for (Film film : Film.filmsArray) {
                                if (film.getID() == filmIDFromUser) {
                                    double score = user.getFilmIDsAndRates().get(film.getID());
                                    MyFileIOOperations.writeFile(film.getTITLE() + ": " +
                                            CommandHelper.prepareRating(Double.toString(score), "to comma") + "\n", fileName);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // lists all the TVSeries in the system
    protected static void listTVSeries(String fileName) {
        if (TVSeries.TVSeriesArray.size() == 0) {
            MyFileIOOperations.writeFile("No result\n", fileName);
        }
        else {
            for (TVSeries tvs : TVSeries.TVSeriesArray) {
                MyFileIOOperations.writeFile(tvs.getTITLE() + " (" +
                        tvs.getSTART_DATE().substring(tvs.getSTART_DATE().lastIndexOf('.') + 1) + '-' +
                        tvs.getEND_DATE().substring(tvs.getEND_DATE().lastIndexOf('.') + 1) + ")\n" +
                        tvs.getNUMBER_OF_SEASONS() + " seasons and " + tvs.getNUMBER_OF_EPISODES() + " episodes\n", fileName);
                if (TVSeries.TVSeriesArray.indexOf(tvs) != TVSeries.TVSeriesArray.size() - 1) {
                    MyFileIOOperations.writeFile("\n", fileName);
                }
            }
        }
    }

    // lists the Films whose country is the same as the country in the command
    protected static void listFilmsByCountry(String[] command, String fileName) {
        LinkedHashMap<String, ArrayList<Film>> countryAndFilms = new LinkedHashMap<>();

        for (Film film : Film.filmsArray) {
            if (countryAndFilms.containsKey(film.getCOUNTRY())) {
                ArrayList<Film> existingFilms = countryAndFilms.get(film.getCOUNTRY());
                existingFilms.add(film);
                countryAndFilms.put(film.getCOUNTRY(), existingFilms);
            }
            else {
                ArrayList<Film> willAdd = new ArrayList<>();
                willAdd.add(film);
                countryAndFilms.put(film.getCOUNTRY(), willAdd);
            }
        }

        if (!countryAndFilms.containsKey(command[4])) {
            MyFileIOOperations.writeFile("No result\n", fileName);
        }
        else {
            int counter = 0;
            for (Film film : countryAndFilms.get(command[4])) {
                MyFileIOOperations.writeFile("Film title: " + film.getTITLE() + "\n" + film.getRuntime() +
                        " min\n" + "Language: " + film.getLANGUAGE() + "\n", fileName);
                if (counter != countryAndFilms.get(command[4]).size() - 1) {
                    MyFileIOOperations.writeFile("\n", fileName);
                }
                counter++;
            }
        }
    }

    // lists the FeatureFilms before a specified year
    protected static void listFeatureFilmsBefore(String[] command, String fileName) {
        ArrayList<FeatureFilm> featureFilmsBeforeYear = new ArrayList<>();
        for (FeatureFilm ff : FeatureFilm.featureFilmsArray) {
            if (Integer.parseInt(ff.getRELEASE_DATE().substring(ff.getRELEASE_DATE().lastIndexOf('.') + 1)) < Integer.parseInt(command[3])) {
                featureFilmsBeforeYear.add(ff);
            }
        }
        CommandHelper.listFeatureFilmsHelper(featureFilmsBeforeYear, fileName);
    }

    // lists the FeatureFilms after a specified year
    protected static void listFeatureFilmsAfter(String[] command, String fileName) {
        ArrayList<FeatureFilm> featureFilmsAfterYear = new ArrayList<>();
        for (FeatureFilm ff : FeatureFilm.featureFilmsArray) {
            if (Integer.parseInt(ff.getRELEASE_DATE().substring(ff.getRELEASE_DATE().lastIndexOf('.') + 1)) >= Integer.parseInt(command[3])) {
                featureFilmsAfterYear.add(ff);
            }
        }
        CommandHelper.listFeatureFilmsHelper(featureFilmsAfterYear, fileName);
    }

    // lists the Films according to their rating in descending order, grouped by their class name
    protected static void listFilmsByRate(String fileName) {
        LinkedHashMap<Double, ArrayList<Film>> unsortedLinkedHashMap = new LinkedHashMap<>();
        for (Film film : Film.filmsArray) {
            if (unsortedLinkedHashMap.containsKey(film.getRating())) {
                ArrayList<Film> existingFilms = unsortedLinkedHashMap.get(film.getRating());
                existingFilms.add(film);
                unsortedLinkedHashMap.put(film.getRating(), existingFilms);
            }
            else {
                ArrayList<Film> filmToPut = new ArrayList<>();
                filmToPut.add(film);
                unsortedLinkedHashMap.put(film.getRating(), filmToPut);
            }
        }

        ArrayList<Double> linkedHashMapKeys = new ArrayList<>();
        for (Double rating : unsortedLinkedHashMap.keySet()) {
            linkedHashMapKeys.add(rating);
        }

        Collections.sort(linkedHashMapKeys, Collections.reverseOrder());

        LinkedHashMap<Double, ArrayList<Film>> sortedLinkedHashMap = new LinkedHashMap<>();

        for (Double key : linkedHashMapKeys) {
            ArrayList<Film> values = unsortedLinkedHashMap.get(key);
            sortedLinkedHashMap.put(key, values);
        }

        String featureFilm = "FeatureFilm:\n";
        String shortFilm = "ShortFilm:\n";
        String documentary = "Documentary:\n";
        String TVSeries = "TVSeries:\n";

        for (Double key : sortedLinkedHashMap.keySet()) {
            for (Film film : sortedLinkedHashMap.get(key)) {
                if (film instanceof FeatureFilm) {
                    FeatureFilm ff = (FeatureFilm) film;
                    featureFilm = featureFilm.concat(ff.getTITLE() + " (" +
                            ff.getRELEASE_DATE().substring(ff.getRELEASE_DATE().lastIndexOf('.') + 1) +
                            ") Ratings: " + CommandHelper.prepareRating(Double.toString(ff.getRating()), "to comma") +
                            "/10 from " + ff.getRaters() + " users\n");
                }
                else if (film instanceof ShortFilm) {
                    ShortFilm sf = (ShortFilm) film;
                    shortFilm = shortFilm.concat(sf.getTITLE() + " (" +
                            sf.getRELEASE_DATE().substring(sf.getRELEASE_DATE().lastIndexOf('.') + 1) +
                            ") Ratings: " + CommandHelper.prepareRating(Double.toString(sf.getRating()), "to comma") +
                            "/10 from " + sf.getRaters() + " users\n");
                }
                else if (film instanceof Documentary) {
                    Documentary d = (Documentary) film;
                    documentary = documentary.concat(d.getTITLE() + " (" +
                            d.getRELEASE_DATE().substring(d.getRELEASE_DATE().lastIndexOf('.') + 1) +
                            ") Ratings: " + CommandHelper.prepareRating(Double.toString(d.getRating()), "to comma") +
                            "/10 from " + d.getRaters() + " users\n");
                }
                else if (film instanceof TVSeries) {
                    TVSeries tvs = (TVSeries) film;
                    TVSeries = TVSeries.concat(tvs.getTITLE() + " (" +
                            tvs.getSTART_DATE().substring(tvs.getSTART_DATE().lastIndexOf('.') + 1) +
                            '-' + tvs.getEND_DATE().substring(tvs.getEND_DATE().lastIndexOf('.') + 1) + ") Ratings: " +
                            CommandHelper.prepareRating(Double.toString(tvs.getRating()), "to comma") + "/10 from " + tvs.getRaters() +
                            " users\n");
                }
            }
        }

        if (featureFilm.equals("FeatureFilm:\n")) {
            featureFilm = featureFilm.concat("No result\n");
        }
        if (shortFilm.equals("ShortFilm:\n")) {
            shortFilm = shortFilm.concat("No result\n");
        }
        if (documentary.equals("Documentary:\n")) {
            documentary = documentary.concat("No result\n");
        }
        if (TVSeries.equals("TVSeries:\n")) {
            TVSeries = TVSeries.concat("No result\n");
        }

        MyFileIOOperations.writeFile(featureFilm + "\n" + shortFilm + "\n" + documentary + "\n" + TVSeries, fileName);
    }

    // lists all the Artists from a specified country, grouped by their class name
    protected static void listArtistsFromCountry(String[] command, String fileName) {
        ArrayList<Artist> artistsFromCountry = new ArrayList<>();
        for (Artist artist : Artist.artistsArray) {
            if (artist.getCOUNTRY().equals(command[3])) {
                artistsFromCountry.add(artist);
            }
        }

        String directors = "Directors:\n";
        String writers = "Writers:\n";
        String actors = "Actors:\n";
        String childActors = "ChildActors:\n";
        String stuntPerformers = "StuntPerformers:\n";

        for (Artist artist : artistsFromCountry) {
            if (artist instanceof Director) {
                Director d = (Director) artist;
                directors = directors.concat(d.getNAME() + " " + d.getSURNAME() + " " + d.getAGENT() + "\n");
            }
            else if (artist instanceof Writer) {
                Writer w = (Writer) artist;
                writers = writers.concat(w.getNAME() + " " + w.getSURNAME() + " " + w.getWRITING_STYLE() + "\n");
            }
            else if (artist instanceof Actor) {
                Actor a = (Actor) artist;
                actors = actors.concat(a.getNAME() + " " + a.getSURNAME() + " " +
                        CommandHelper.prepareRating(Double.toString(a.getHEIGHT()), "to comma") + " cm\n");
            }
            else if (artist instanceof ChildActor) {
                ChildActor ca = (ChildActor) artist;
                childActors = childActors.concat(ca.getNAME() + " " + ca.getSURNAME() + " " + ca.getAGE() + "\n");
            }
            else if (artist instanceof StuntPerformer) {
                StuntPerformer sp = (StuntPerformer) artist;
                stuntPerformers = stuntPerformers.concat(sp.getNAME() + " " + sp.getSURNAME() + " " +
                        CommandHelper.prepareRating(Double.toString(sp.getHEIGHT()), "to comma") + " cm\n");
            }
        }

        if (directors.equals("Directors:\n")) {
            directors = directors.concat("No result\n");
        }
        if (writers.equals("Writers:\n")) {
            writers = writers.concat("No result\n");
        }
        if (actors.equals("Actors:\n")) {
            actors = actors.concat("No result\n");
        }
        if (childActors.equals("ChildActors:\n")) {
            childActors = childActors.concat("No result\n");
        }
        if (stuntPerformers.equals("StuntPerformers:\n")) {
            stuntPerformers = stuntPerformers.concat("No result\n");
        }

        MyFileIOOperations.writeFile(directors + "\n" + writers + "\n" + actors + "\n" + childActors +
                "\n" + stuntPerformers, fileName);
    }
}
