import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class MainMenu {
    Exception NoPlant;
    static BufferedReader read;
    static Scanner input = new Scanner(System.in);
    static PlantConatinaer<Foliage> foliagePlantContainer = new PlantConatinaer<>();
    static PlantConatinaer<Succulent> succulentPlantConatinaer = new PlantConatinaer<>();
    public static ArrayList<String> findPlant(String commonName) {
        ArrayList <String> plantRes = new ArrayList<String>();
//        Integer choice = 1;
        try {
            read = new BufferedReader(new FileReader("/Users/brookelove/code/bostonU/MET_CS_622/GROWTH/GROWTH/src/PlantDictionary.txt"));
            String line;
            while ((line = read.readLine()) != null) {
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
        String plant;
        Boolean spikes;
        String letterRes = null;
        ArrayList<String> plantInfo = new ArrayList<>();
        ArrayList<String> plantChoiceArr = new ArrayList<>();
        plantChoiceArr.clear();

        Integer choice = 1;
        try {
            System.out.println("Lets find the plant. Please give the first letter of the plant that you would like to add:");
            letterRes = input.nextLine();
            if (letterRes.matches("[a-zA-Z]+") == false){
                throw new PlantExceptions.NotAChar("Not a Char");
            } else {
                letterRes = String.valueOf(letterRes.charAt(0));
            };
        } catch (PlantExceptions.NotAChar e){
            System.out.println("Please inter a character from A-Z or a-z");
            return;
        }

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
        // TRY_CATCH FOR CUSTOM NUM
        try{
            System.out.println("Please choose from the list what plant you want by entering from the given numbers above:");
            Integer plantChoice = input.nextInt();
            plant = plantChoiceArr.get(plantChoice-1);
            System.out.printf("Before we add the plant we need to answer some questions for our records for: %s\nWhat size is your plant in now?\nSize in inches: ", plant);
            potSize = input.nextInt();
            System.out.printf("Great! How much did your plant cost? Please enter just the numbers and decimal, if needed: Example 5.00\nPrice: $ ");
            price = input.nextFloat();
            System.out.printf("If your plant has variation, a different color, than the typical plant species then?\n");
            input.nextLine();
            variation = input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Please input a valid number ");
            letterRes = null;
            return;
        }
        // TRY CATCH ENDS
        ArrayList plantResults = findPlant(plant); //an arraylist
        for (int i = 1; i < plantResults.size()-1; i++) {
            plantInfo.add(plantResults.get(i).toString());
        }
        uuid = UUID.randomUUID().toString();
        String commonName = plantResults.get(0).toString();
        if(plantResults.get(7).toString().contains("F")){
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
            succulentPlantConatinaer.addPlant(new Succulent(uuid, commonName,potSize,price,variation,spikes,plantInfo));
        }
    }
    public static void plantary () {
        List <Foliage> foliageList = foliagePlantContainer.getAllPlants();
        List <Succulent> succulentList = succulentPlantConatinaer.getAllPlants();
        if(foliageList.size() == 0 && succulentList.size() == 0) {
            System.out.println("Please add a plants to your greenhouse its empty!");
            return;
        }
        System.out.println("This is the list of plants in your greenhouse\nList of Foliage:\n");
        if(foliageList.size() == 0){
            System.out.println("0");
        } else {
            for (int i = 0; i < foliageList.size(); i++) {
                System.out.printf("%s - %s",i+1,foliageList.get(i).getName());
                System.out.printf("%s - %s",i+1,foliageList.get(i).getInfo());
            }
        }
        System.out.println("\nList of Succulents:\n");
        if(succulentList.size() == 0){
            System.out.println("0");
        } else {
            for (int i = 0; i < succulentList.size(); i++) {
                System.out.printf("%s - %s",i+1,succulentList.get(i).getName());
                System.out.printf("%s - %s",i+1,succulentList.get(i).getInfo());
            }
        }
        System.out.printf("Total Plants: %s\n",succulentList.size() + foliageList.size());
    }
    public static void lookAtPlants() throws PlantExceptions.NoPlantFound {
        System.out.println("This is the GreenHouse! A place where you can see the total list of items in you");
        Integer response = 0;
        do {
            try {
            System.out.println(
                    "Here are the options to check out your virtual greenhouse:\n1.Succulents\n2.Foliage\n3.Plantary: Learn more info about your plants\n4.Go Back to Main Menu");
            response = input.nextInt();
            switch (response) {
                case 1:
                    if (succulentPlantConatinaer.getAllPlants().size() == 0) {
                        throw new PlantExceptions.NoPlantFound("There is no plants in your greenhouse! Go add a plant first, then come back!");
                    } else {
                        System.out.printf("Total Succulents: %s\n", succulentPlantConatinaer.getAllPlants().size());
                        System.out.println(succulentPlantConatinaer.getAllPlants().get(0).getName());
                    }
                    //for loop to list all foli by common name and botanical name
                    break;
                case 2:
                    if (foliagePlantContainer.getAllPlants().size() == 0) {
                        throw new PlantExceptions.NoPlantFound("No plants");
                    } else {
                        System.out.printf("Total Foliage: %s\n", foliagePlantContainer.getAllPlants().size());
                        System.out.println(foliagePlantContainer.getAllPlants().get(0).getName());
                    }
                    //for loop to list all succ by common name and botanical name
                    break;
                case 3:
                    plantary();
                    break;
                case 4:
                    System.out.println("Great lets go back!");
                    return; // makes it return and go back rather than end the entire system
                default:
                    System.out.printf("%s is not valid please try again. Make sure you don't follow your response with spaces.", response);
            } 
            } catch (PlantExceptions.NoPlantFound e) {
                System.out.println("There are no plants in your greenhouse! Go add a plant first, then come back!");
                continue;
            }
        } while(response != 4);

    }
}
