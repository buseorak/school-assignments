import java.util.ArrayList;

public class PeopleFileReader {

    // reads the people file passed as parameter and creates People objects according to the file content
    protected static void readPeopleFile(String filePath) {
        ArrayList<String> peopleLines = MyFileIOOperations.readFile(filePath);

        assert peopleLines != null;
        for (String personLine : peopleLines) {

            // create User object
            if (personLine.startsWith("User")) {
                String[] userData = personLine.replace("User:\t", "").split("\t");
                User obj = new User(Integer.parseInt(userData[0]), userData[1], userData[2], userData[3]);
                User.usersArray.add(obj);
            }

            // create Actor object
            else if (personLine.startsWith("Actor")) {
                String[] actorData = personLine.replace("Actor:\t", "").split("\t");
                Actor obj = new Actor(Integer.parseInt(actorData[0]), actorData[1], actorData[2],
                        actorData[3], Double.parseDouble(CommandHelper.prepareRating(actorData[4], "to point")));
                Actor.actorsArray.add(obj);
                Performer.performersArray.add(obj);
                Artist.artistsArray.add(obj);
            }

            // create ChildActor object
            else if (personLine.startsWith("ChildActor")) {
                String[] childActorData = personLine.replace("ChildActor:\t", "").split("\t");
                ChildActor obj = new ChildActor(Integer.parseInt(childActorData[0]), childActorData[1],
                        childActorData[2], childActorData[3], Integer.parseInt(childActorData[4]));
                ChildActor.childActorsArray.add(obj);
                Performer.performersArray.add(obj);
                Artist.artistsArray.add(obj);
            }

            // create StuntPerformer object
            else if (personLine.startsWith("StuntPerformer")) {
                String[] stuntPerformerData = personLine.replace("StuntPerformer:\t", "").split("\t");

                String[] realsIDsString = stuntPerformerData[5].split(",");
                ArrayList<Integer> realsIDs = new ArrayList<>();
                for (String realsID : realsIDsString) {
                    realsIDs.add(Integer.parseInt(realsID));
                }

                StuntPerformer obj = new StuntPerformer(Integer.parseInt(stuntPerformerData[0]), stuntPerformerData[1],
                        stuntPerformerData[2], stuntPerformerData[3],
                        Double.parseDouble(CommandHelper.prepareRating(stuntPerformerData[4], "to point")), realsIDs);
                StuntPerformer.stuntPerformersArray.add(obj);
                Performer.performersArray.add(obj);
                Artist.artistsArray.add(obj);
            }

            // create Director object
            else if (personLine.startsWith("Director")) {
                String[] directorData = personLine.replace("Director:\t", "").split("\t");
                Director obj = new Director(Integer.parseInt(directorData[0]), directorData[1], directorData[2],
                        directorData[3], directorData[4]);
                Director.directorsArray.add(obj);
                Artist.artistsArray.add(obj);
            }

            // create Writer object
            else if (personLine.startsWith("Writer")) {
                String[] writerData = personLine.replace("Writer:\t", "").split("\t");
                Writer obj = new Writer(Integer.parseInt(writerData[0]), writerData[1], writerData[2],
                        writerData[3], writerData[4]);
                Writer.writersArray.add(obj);
                Artist.artistsArray.add(obj);
            }
        }
    }
}
