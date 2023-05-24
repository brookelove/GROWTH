import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainMenu {
    static BufferedReader read;
    static Scanner input = new Scanner(System.in);

    public static ArrayList<String> findPlant(String commonName) {
        ArrayList <String> plantRes = new ArrayList<String>();
        Integer choice = 1;
        try {
            read = new BufferedReader(new FileReader("/Users/brookelove/code/bostonU/MET_CS_622/GROWTH/GROWTH/src/PlantDictionary.txt"));
            String line = read.readLine();
            while (true) {
                if (line == null) {
                    break;
                }
                line = read.readLine();
                if(line.contains(commonName)){
                    plantRes.add(commonName);
                    for(int i=1;i < 8;i++){
                        plantRes.add(read.readLine());
//                        System.out.printf(read.readLine());
                    }
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
        return plantRes;
    }
    public static void addPlant(){
        ArrayList <String> plantChoiceArr = new ArrayList<String>();
        Integer choice = 1;
        System.out.println("Lets find the plant. Please give the first letter of the plant that you would like to add:");
        String letterRes = input.nextLine();
        try {
            read = new BufferedReader(new FileReader("/Users/brookelove/code/bostonU/MET_CS_622/GROWTH/GROWTH/src/PlantDictionary.txt"));
            String line = read.readLine();
            while (true) {
                if (line == null) {
                    break;
                }
                line = read.readLine();
                if(line.contains("Common_Name:")){
                    String splitLine [] = line.split("Common_Name: ");
                    String namePlant = Array.get(splitLine,1).toString();
                    if(namePlant.startsWith(letterRes.toUpperCase())){
                        namePlant = namePlant.replace(",", "");
                        plantChoiceArr.add(namePlant);
                        System.out.printf("%s - %s\n", choice.toString(), namePlant);
                        choice += 1;
                    }
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
        System.out.println("Please choose from the list what plant you want by entering from the given numbers above:");
        Integer plantChoice = input.nextInt();
        String plant = plantChoiceArr.get(plantChoice-1);
        System.out.printf("Adding Plant: %s\n", plant);
        System.out.println(findPlant(plant));
    }
}
