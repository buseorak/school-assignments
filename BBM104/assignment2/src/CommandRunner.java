import java.util.ArrayList;

public class CommandRunner {

    // makes calls to the related methods according to the commands file
    protected static void execute(ArrayList<String> commands, String fileName) {
        for (String command : commands) {
            String[] commandData = command.split("\t");

            if (commandData.length <= 1) {
                continue;
            }

            if (command.startsWith("RATE") &&
                    ((Double.parseDouble(CommandHelper.prepareRating(commandData[3], "to point")) > 10) ||
                            (Double.parseDouble(CommandHelper.prepareRating(commandData[3], "to point")) < 1))) {
                System.out.println("Rating is not in range 1-10!");
                continue;
            }
            if (command.startsWith("EDIT\tRATE") &&
                    ((Double.parseDouble(CommandHelper.prepareRating(commandData[4], "to point")) > 10) ||
                            (Double.parseDouble(CommandHelper.prepareRating(commandData[4], "to point")) < 0))) {
                System.out.println("Rating is not in range 1-10!");
                continue;
            }

            MyFileIOOperations.writeFile(command + "\n\n", fileName);

            if (command.startsWith("RATE")) {
                RateEditor.rate(commandData, fileName);
            }

            else if (command.startsWith("EDIT\tRATE")) {
                RateEditor.editRate(commandData, fileName);
            }

            else if (command.startsWith("REMOVE\tRATE")) {
                RateEditor.removeRate(commandData, fileName);
            }

            else if (command.startsWith("ADD\tFEATUREFILM")) {
                FeatureFilmAdder.addFeatureFilm(commandData, fileName);
            }

            else if (command.startsWith("VIEWFILM")) {
                FilmViewer.viewFilm(commandData, fileName);
            }

            else if (command.startsWith("LIST\tUSER")) {
                FilmLister.listUserRates(commandData, fileName);
            }

            else if (command.startsWith("LIST\tFILM\tSERIES")) {
                FilmLister.listTVSeries(fileName);
            }

            else if (command.startsWith("LIST\tFILMS\tBY\tCOUNTRY")) {
                FilmLister.listFilmsByCountry(commandData, fileName);
            }

            else if (command.startsWith("LIST\tFEATUREFILMS\tBEFORE")) {
                FilmLister.listFeatureFilmsBefore(commandData, fileName);
            }

            else if (command.startsWith("LIST\tFEATUREFILMS\tAFTER")) {
                FilmLister.listFeatureFilmsAfter(commandData, fileName);
            }

            else if (command.startsWith("LIST\tFILMS\tBY\tRATE\tDEGREE")) {
                FilmLister.listFilmsByRate(fileName);
            }

            else if (command.startsWith("LIST\tARTISTS\tFROM")) {
                FilmLister.listArtistsFromCountry(commandData, fileName);
            }

            MyFileIOOperations.writeFile("\n-----------------------------------------------" +
                    "------------------------------------------------------\n", fileName);
        }
    }
}
