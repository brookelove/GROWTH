import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
public class PlantIndex {
    /*
       DESCRIPTION: view list current list of items within PlantDictionary.txt
       PRE-CONDITION: receiving genus name from the user input ;
       POST-CONDITION: returns list of all common names with the same family that is in the PlantDictionary.txt;
       */
    static BufferedReader read;
    static String fileName = "/Users/brookelove/code/bostonU/MET_CS_622/GROWTH/GROWTH/src/PlantDictionary.txt";
    public static void plantIndex (String genus) {
        System.out.printf("Here is our known list of species that have the genus of: %s\n", genus);
        try {
            read = new BufferedReader(new FileReader(fileName));
            String line = read.readLine();
            while (true) {
                if (line == null) {
                    break;
                }
                line = read.readLine();
                if(line.contains("Common_Name:") && read.readLine().contains(genus)){
                    line.replace("Common_Name: ", "");
                    System.out.println(line);
                }
            }

            read.close();
        } catch (NullPointerException e) {
            System.out.printf("");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
