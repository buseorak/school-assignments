import java.util.ArrayList;

public class FeatureFilmAdder {

    // adds a new FeatureFilm object to the system if the conditions are met
    protected static void addFeatureFilm(String[] command, String fileName) {
        ArrayList<Integer> filmIDs = CommandHelper.createArrayList("Film");
        boolean canCreate = true;

        if (filmIDs.contains(Integer.parseInt(command[2]))) {
            canCreate = false;
        }
        canCreate = (canCreate && CommandHelper.checkArtistExistence("Director", command[5]) &&
                CommandHelper.checkArtistExistence("Performer", command[8]) &&
                CommandHelper.checkArtistExistence("Writer", command[11]));

        if (!canCreate) {
            MyFileIOOperations.writeFile("Command Failed\nFilm ID: " + command[2] + "\nFilm title: " +
                    command[3] + "\n", fileName);
        }
        else {
            FeatureFilm obj = new FeatureFilm(Integer.parseInt(command[2]), command[3], command[4],
                    FilmsFileReader.prepareDirectorsOfFilm(command[5]), Integer.parseInt(command[6]), command[7],
                    FilmsFileReader.preparePerformersOfFilm(command[8]), FilmsFileReader.prepareGenreOfFilm(command[9]),
                    command[10], FilmsFileReader.prepareWritersOfFilm(command[11]), Long.parseLong(command[12]));
            FeatureFilm.featureFilmsArray.add(obj);
            Film.filmsArray.add(obj);
            MyFileIOOperations.writeFile("FeatureFilm added successfully\nFilm ID: " + command[2] +
                    "\nFilm title: " + command[3] + "\n", fileName);
        }
    }
}
