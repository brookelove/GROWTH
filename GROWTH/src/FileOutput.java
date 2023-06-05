import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FileOutput {
    /*
    * DESCRIPTION: Allows an object to be written to a predetermined file
    * PRE-CONDITIONS: an Object from another java file and a predetermined file that has been created
    * POST-CONDITIONS: writes object to another file.
    */
    public static void writePlantsToFile(Object plant,String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream plantObj = new ObjectOutputStream(fileOut);
            plantObj.writeObject(plant);
            plantObj.close();

        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
