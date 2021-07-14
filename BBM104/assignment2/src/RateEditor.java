public class RateEditor {

    // rates the Film and stores the rating information in related User and Film objects if condition are met
    protected static void rate(String[] command, String fileName) {
        if (CommandHelper.checkExistence(Integer.parseInt(command[1]), Integer.parseInt(command[2]), fileName)) {
            userLoop:
            for (User user : User.usersArray) {
                if (user.getID() == Integer.parseInt(command[1])) {
                    if (user.getFilmIDsAndRates().containsKey(Integer.parseInt(command[2]))) {
                        MyFileIOOperations.writeFile("This film was earlier rated\n", fileName);
                        break;
                    }
                    else {
                        for (Film film : Film.filmsArray) {
                            if (film.getID() == Integer.parseInt(command[2])) {
                                user.setFilmIDsAndRates(film.getID(),
                                        Double.parseDouble(CommandHelper.prepareRating(command[3], "to point")));
                                film.setRating(Double.parseDouble(CommandHelper.prepareRating(command[3], "to point")));
                                MyFileIOOperations.writeFile("Film rated successfully\nFilm type: " +
                                        film.getClass().getName() + "\nFilm title: " + film.getTITLE() + "\n", fileName);
                                break userLoop;
                            }
                        }
                    }
                }
            }
        }
    }

    // edits the Film rating and stores the new rating information in related User and Film objects if conditions are met
    protected static void editRate(String[] command, String fileName) {
        if (CommandHelper.checkExistence(Integer.parseInt(command[2]), Integer.parseInt(command[3]), fileName)) {
            userLoop:
            for (User user : User.usersArray) {
                if (user.getID() == Integer.parseInt(command[2])) {
                    if (!user.getFilmIDsAndRates().containsKey(Integer.parseInt(command[3]))) {
                        MyFileIOOperations.writeFile("Command Failed\nUser ID: " + command[2] + "\nFilm ID: " +
                                command[3] + "\n", fileName);
                        break;
                    }
                    else {
                        double previousRating = user.getFilmIDsAndRates().get(Integer.parseInt(command[3]));
                        for (Film film : Film.filmsArray) {
                            if (film.getID() == Integer.parseInt(command[3])) {
                                film.removeRating(previousRating);
                                double newRating = Double.parseDouble(CommandHelper.prepareRating(command[4], "to point"));
                                film.setRating(newRating);
                                user.setFilmIDsAndRates(film.getID(), newRating);
                                MyFileIOOperations.writeFile("New ratings done successfully\nFilm title: " +
                                        film.getTITLE() + "\nYour rating: " + command[4] + "\n", fileName);
                                break userLoop;
                            }
                        }
                    }
                }
            }
        }
    }

    // removes the Film rating and removes the rating information in related User and Film objects if the conditions are met
    protected static void removeRate(String[] command, String fileName) {
        if (CommandHelper.checkExistence(Integer.parseInt(command[2]), Integer.parseInt(command[3]), fileName)) {
            userLoop:
            for (User user : User.usersArray) {
                if (user.getID() == Integer.parseInt(command[2])) {
                    if (!user.getFilmIDsAndRates().containsKey(Integer.parseInt(command[3]))) {
                        MyFileIOOperations.writeFile("Command Failed\nUser ID: " + command[2] + "\nFilm ID: " +
                                command[3] + "\n", fileName);
                        break;
                    }
                    else {
                        double previousRating = user.getFilmIDsAndRates().get(Integer.parseInt(command[3]));
                        for (Film film : Film.filmsArray) {
                            if (film.getID() == Integer.parseInt(command[3])) {
                                film.removeRating(previousRating);
                                user.getFilmIDsAndRates().remove(film.getID(), previousRating);
                                MyFileIOOperations.writeFile("Your film rating was removed successfully\nFilm title: " +
                                        film.getTITLE() + "\n", fileName);
                                break userLoop;
                            }
                        }
                    }
                }
            }
        }
    }
}
