import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class MyFileIOOperations {

    // This method takes a file path as parameter and reads the content of that file
    public static ArrayList<String> readFile(String filePath) {
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

    /* This method creates the output file if it does not exist or
       empties the content of it if it already exists to write new output */
    public static void createOutputFile(String fileName) {
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

    // This method appends the content (passed as a parameter) into the output file
    public static void writeFile(String lineToWrite) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("output.txt", true));
            fileWriter.append(lineToWrite);
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
