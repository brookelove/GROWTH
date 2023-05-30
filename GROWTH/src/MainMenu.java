import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class MainMenu {
    static BufferedReader read;
    static Scanner input = new Scanner(System.in);
    static PlantConatinaer<Foliage> foliagePlantContainer = new PlantConatinaer<>();
    static PlantConatinaer<Succulent> succulentPlantConatinaer = new PlantConatinaer<>();
//    ArrayList<Foliage> foliageList = new ArrayList<>();
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
        String uuid;
        Integer potSize;
        Float price;
        String variation;
        String response;
        Boolean spikes;
        ArrayList<String> plantInfo = new ArrayList<>();
        ArrayList<String> plantChoiceArr = new ArrayList<>();

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
        System.out.printf("Before we add the plant we need to answer some questions for our records for: %s\nWhat size is your plant in now?", plant);
        System.out.println("Size in inches:");
        potSize = input.nextInt();
//        System.out.println("\n");
        System.out.printf("Great! How much did your plant cost? Please enter just the numbers and decimal, if needed: Example 5.00");
        System.out.println("Price: $");
        price = input.nextFloat();
        System.out.printf("If your plant has variation, a different color, than the typical plant species then?");
        input.nextLine();
        System.out.println("Variegation:");
        variation = input.nextLine();
        ArrayList plantResults = findPlant(plant); //an arraylist
        for (int i = 1; i < plantResults.size()-1; i++) {
            plantInfo.add(plantResults.get(i).toString());
        }
        uuid = UUID.randomUUID().toString();
        String commonName = plantResults.get(0).toString();
        if(plantResults.get(7) == "Foliage"){
            foliagePlantContainer.addPlant(new Foliage(uuid, commonName,potSize,variation,price,plantInfo));
        } else {
            System.out.println("Does your plant have spikes?");
            System.out.println("Spikes?");
            response = input.nextLine();
            response.toUpperCase();
            if(response.startsWith("Y") || response.startsWith("T")) {
                spikes = true;
            } else {
                spikes = false;
            }
            succulentPlantConatinaer.addPlant(new Succulent(uuid, commonName,potSize,price, variation,spikes,plantInfo));
        }
    // need to reset the plant list choice back to being empty?
    }
    public static void lookAtPlants() {
        System.out.println("This is the GreenHouse! A place where you can see the total list of items in you");
        System.out.printf("Total Succulents: %s\n", succulentPlantConatinaer.getAllPlants().size());
        System.out.printf("Total Foliage: %s", foliagePlantContainer.getAllPlants().size());

    }
}
