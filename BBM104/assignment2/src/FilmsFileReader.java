import java.util.ArrayList;
import java.util.Arrays;

public class FilmsFileReader {

    // helper method that returns list of Director objects for film's DIRECTORS field
    protected static ArrayList<Director> prepareDirectorsOfFilm(String dataWithComma) {
        String[] splitDirectorsArray = dataWithComma.split(",");
        ArrayList<Director> directorsList = new ArrayList<>();

        for (String directorID : splitDirectorsArray) {
            for (Director director : Director.directorsArray) {
                if (Integer.parseInt(directorID) == director.getID()) {
                    directorsList.add(director);
                }
            }
        }
        return directorsList;
    }

    // helper method that returns list of Performer objects for film's PERFORMERS field
    protected static ArrayList<Performer> preparePerformersOfFilm(String dataWithComma) {
        String[] splitPerformersArray = dataWithComma.split(",");
        ArrayList<Performer> performersList = new ArrayList<>();

        for (String performerID : splitPerformersArray) {
            for (Performer performer : Performer.performersArray) {
                if (Integer.parseInt(performerID) == performer.getID()) {
                    performersList.add(performer);
                }
            }
        }
        return performersList;
    }

    // helper method that returns list of Writer objects for film's WRITERS field
    protected static ArrayList<Writer> prepareWritersOfFilm(String dataWithComma) {
        String[] splitWritersArray = dataWithComma.split(",");
        ArrayList<Writer> writersList = new ArrayList<>();

        for (String writerID : splitWritersArray) {
            for (Writer writer : Writer.writersArray) {
                if (Integer.parseInt(writerID) == writer.getID()) {
                    writersList.add(writer);
                }
            }
        }
        return writersList;
    }

    // helper method that returns list of genre list for film's GENRE field
    protected static ArrayList<String> prepareGenreOfFilm(String dataWithComma) {
        String[] genreArray = dataWithComma.split(",");
        ArrayList<String> genreList = new ArrayList<>();
        for (String genre : genreArray) {
            genreList.add(genre);
        }
        return genreList;
    }

    // reads the films file passed as parameter and creates Film objects according to the file content
    protected static void readFilmsFile(String filePath) {
        ArrayList<String> filmsLines = MyFileIOOperations.readFile(filePath);

        assert filmsLines != null;
        for (String filmLine : filmsLines) {
            String[] tempFilmData = filmLine.split("\t");
            String[] filmData = Arrays.copyOfRange(tempFilmData, 1, tempFilmData.length);

            // create FeatureFilm object
            if (filmLine.startsWith("FeatureFilm")) {
                FeatureFilm obj = new FeatureFilm(Integer.parseInt(filmData[0]), filmData[1], filmData[2],
                        prepareDirectorsOfFilm(filmData[3]), Integer.parseInt(filmData[4]), filmData[5],
                        preparePerformersOfFilm(filmData[6]), prepareGenreOfFilm(filmData[7]), filmData[8],
                        prepareWritersOfFilm(filmData[9]), Long.parseLong(filmData[10]));
                FeatureFilm.featureFilmsArray.add(obj);
                Film.filmsArray.add(obj);
            }

            // create ShortFilm object
            else if (filmLine.startsWith("ShortFilm")) {
                if (Integer.parseInt(filmData[4]) > 40) {
                    System.out.println("A short film cannot be longer than 40 minutes!");
                }
                else {
                    ShortFilm obj = new ShortFilm(Integer.parseInt(filmData[0]), filmData[1], filmData[2],
                            prepareDirectorsOfFilm(filmData[3]), Integer.parseInt(filmData[4]), filmData[5],
                            preparePerformersOfFilm(filmData[6]), prepareGenreOfFilm(filmData[7]), filmData[8],
                            prepareWritersOfFilm(filmData[9]));
                    ShortFilm.shortFilmsArray.add(obj);
                    Film.filmsArray.add(obj);
                }
            }

            // create Documentary object
            else if (filmLine.startsWith("Documentary")) {
                Documentary obj = new Documentary(Integer.parseInt(filmData[0]), filmData[1], filmData[2],
                        prepareDirectorsOfFilm(filmData[3]), Integer.parseInt(filmData[4]), filmData[5],
                        preparePerformersOfFilm(filmData[6]), filmData[7]);
                Documentary.documentariesArray.add(obj);
                Film.filmsArray.add(obj);
            }

            // create TVSeries object
            else if (filmLine.startsWith("TVSeries")) {
                TVSeries obj = new TVSeries(Integer.parseInt(filmData[0]), filmData[1], filmData[2],
                        prepareDirectorsOfFilm(filmData[3]), Integer.parseInt(filmData[4]), filmData[5],
                        preparePerformersOfFilm(filmData[6]), prepareGenreOfFilm(filmData[7]), prepareWritersOfFilm(filmData[8]),
                        filmData[9], filmData[10], Integer.parseInt(filmData[11]), Integer.parseInt(filmData[12]));
                TVSeries.TVSeriesArray.add(obj);
                Film.filmsArray.add(obj);
            }
        }
    }
}
