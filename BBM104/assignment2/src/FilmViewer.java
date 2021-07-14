import java.util.ArrayList;

public class FilmViewer {

    // displays the data of the Film
    protected static void viewFilm(String[] command, String fileName) {
        ArrayList<Integer> filmIDs = CommandHelper.createArrayList("Film");

        if (!filmIDs.contains(Integer.parseInt(command[1]))) {
            MyFileIOOperations.writeFile("Command Failed\nFilm ID: " + command[1] + "\n", fileName);
        }
        else {
            String buildOnThis = "";
            for (Film film : Film.filmsArray) {
                if (film.getID() == Integer.parseInt(command[1])) {
                    buildOnThis = buildOnThis.concat(film.getTITLE() + " (");

                    // view FeatureFilm
                    if (film instanceof FeatureFilm) {
                        FeatureFilm ff = (FeatureFilm) film;
                        buildOnThis = buildOnThis.concat(ff.getRELEASE_DATE().substring(ff.getRELEASE_DATE().lastIndexOf('.') + 1) +
                                ")\n" + CommandHelper.concatenateGenre(ff.getGENRE()) + CommandHelper.concatenateWriters(ff.getWRITERS()));
                    }

                    // view ShortFilm
                    else if (film instanceof ShortFilm) {
                        ShortFilm sf = (ShortFilm) film;
                        buildOnThis = buildOnThis.concat(sf.getRELEASE_DATE().substring(sf.getRELEASE_DATE().lastIndexOf('.') + 1) +
                                ")\n" + CommandHelper.concatenateGenre(sf.getGENRE()) + CommandHelper.concatenateWriters(sf.getWRITERS()));
                    }

                    // view Documentary
                    else if (film instanceof Documentary) {
                        Documentary d = (Documentary) film;
                        buildOnThis = buildOnThis.concat(d.getRELEASE_DATE().substring(d.getRELEASE_DATE().lastIndexOf('.') + 1) + ")\n\n");
                    }

                    // view TVSeries
                    else if (film instanceof TVSeries) {
                        TVSeries tvs = (TVSeries) film;
                        buildOnThis = buildOnThis.concat(tvs.getSTART_DATE().substring(tvs.getSTART_DATE().lastIndexOf('.') + 1) +
                                '-' + tvs.getEND_DATE().substring(tvs.getEND_DATE().lastIndexOf('.') + 1) + ")\n" +
                                tvs.getNUMBER_OF_SEASONS() + " seasons, " + tvs.getNUMBER_OF_EPISODES() + " episodes\n" +
                                CommandHelper.concatenateGenre(tvs.getGENRE()) + CommandHelper.concatenateWriters(tvs.getWRITERS()));
                    }

                    buildOnThis = buildOnThis.concat("Directors: ");
                    for (Director director : film.getDIRECTORS()) {
                        buildOnThis = buildOnThis.concat(director.getNAME() + " " + director.getSURNAME() + ", ");
                    }

                    buildOnThis = buildOnThis.replaceAll(", $", "\nStars: ");
                    for (Performer performer : film.getCAST()) {
                        buildOnThis = buildOnThis.concat(performer.getNAME() + " " + performer.getSURNAME() + ", ");
                    }
                    buildOnThis = buildOnThis.replaceAll(", $", "\n");

                    if (film.getRaters() == 0) {
                        buildOnThis = buildOnThis.concat("Awaiting for votes\n");
                    }
                    else {
                        buildOnThis = buildOnThis.concat("Ratings: " +
                                CommandHelper.prepareRating(Double.toString(film.getRating()), "to comma") +
                                "/10 from " + film.getRaters() + " users\n");
                    }
                    MyFileIOOperations.writeFile(buildOnThis, fileName);
                }
            }
        }
    }
}
