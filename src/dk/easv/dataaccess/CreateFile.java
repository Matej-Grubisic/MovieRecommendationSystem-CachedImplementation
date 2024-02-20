package dk.easv.dataaccess;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;
public class CreateFile {
    public static void create(String title) {
        try {
            File myObj = new File("src\\dk\\easv\\data\\" + title +".json");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void write(String title, String json){
        try {
            FileWriter myWriter = new FileWriter("src\\dk\\easv\\data\\"+title+".json");
            myWriter.write(json);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

