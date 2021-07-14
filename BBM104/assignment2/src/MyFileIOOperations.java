import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MyFileIOOperations {

    // takes a file name as parameter and scans the content of it
    protected static ArrayList<String> readFile(String filePath) {
        File fileToBeRead = new File(filePath);
        ArrayList<String> fileContent = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(fileToBeRead);
            while (fileScanner.hasNextLine()) {
                fileContent.add(fileScanner.nextLine().trim());
            }
            fileScanner.close();
            return fileContent;
        }
        catch (FileNotFoundException e) {
            return null;
        }
    }

    /* creates the output file if it does not exist or
       empties the content of it if it already exists */
    protected static void createOutputFile(String fileName) {
        File outputFileObject = new File(fileName);
        try {
            if (!outputFileObject.createNewFile()) {
                outputFileObject.delete();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // appends the content (passed as parameter) into the output file
    protected static void writeFile(String lineToWrite, String fileName) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName, true));
            fileWriter.append(lineToWrite);
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
