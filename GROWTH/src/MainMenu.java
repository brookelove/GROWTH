import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainMenu {
    Exception NoPlant;
    static BufferedReader read;
    static Scanner input = new Scanner(System.in);
    static PlantConatinaer<Foliage> foliagePlantContainer = new PlantConatinaer<>();
    static PlantConatinaer<Succulent> succulentPlantConatinaer = new PlantConatinaer<>();
    static String fileName = "/Users/brookelove/code/bostonU/MET_CS_622/GROWTH/GROWTH/src/PlantDictionary.txt"; // throws an error if I dont include the absolute path?
    public static ArrayList<String> findPlant(String commonName) {
        /*
        DESCRIPTION: Locates the plant common name based on the file
        PRE-CONDITION: user has to choose from a common name list;
        POST-CONDITION: returns plant that corresponds to the user input common name below;
        */
        ArrayList <String> plantRes = new ArrayList<String>();
//        Integer choice = 1;
        try {
            read = new BufferedReader(new FileReader(fileName));
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
        /*
        DESCRIPTION: Add a new dependant on the name of the plant
        PRE-CONDITION: user has to choose from a common name list after inputing the first letter of their already known common list;
        POST-CONDITION: adds a either a new Foliage or Succulent to the list of plants the person has in their greenhouse;
        */
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
            read = new BufferedReader(new FileReader(fileName));
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
            System.out.println("Great! How much did your plant cost? Please enter just the numbers and decimal, if needed: Example 5.00\nPrice: $ ");
            price = input.nextFloat();
            System.out.println("If your plant has variation, a different color, than the typical plant species then?\n");
            input.nextLine();
            variation = input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Please input a valid number.");
            letterRes = null;
            return;
        }
        // TRY CATCH ENDS
        ArrayList plantResults = findPlant(plant);
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
         /*
        DESCRIPTION: view all plants that the user has input
        PRE-CONDITION: after receiving an input fom the user switch case;
        POST-CONDITION: returns total number of each type of plant as well as the list of names of the plants they currently have if there are no plants it will return 0;
        */
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
//                System.out.printf("%s - %s",i+1,foliageList.get(i).getInfo());
            }
        }
        System.out.println("\nList of Succulents:\n");
        if(succulentList.size() == 0){
            System.out.println("0");
        } else {
            for (int i = 0; i < succulentList.size(); i++) {
                System.out.printf("%s - %s",i+1,succulentList.get(i).getName());
//                System.out.printf("%s - %s",i+1,succulentList.get(i).getInfo());
            }
        }
        System.out.printf("Total Plants: %s\n",succulentList.size() + foliageList.size());
    }
    public static void leafLink() {
         /*
        DESCRIPTION: view list current list of items within PlantDictionary.txt
        PRE-CONDITION: receiving user input from switch case regarding the use of leafLink  ;
        POST-CONDITION: returns list of genus species of plants as well as if the user inputs that they would like the list of all common names of that family they can get it;
        */
        List <String> genusList = new ArrayList<>();
        System.out.println("Welcome of LeafLink the place where you can see GROWTH's current index of plants!");
        try {
            read = new BufferedReader(new FileReader(fileName));
            String line = read.readLine();
            while (line != null) {
                line = read.readLine();
                if (line.contains("Common_Name:")) {
                    String genus = read.readLine();
                    genus.replace(',', ' ');
                    genus = genus.split(" ")[0];
                    genus.trim();
                    if (!genusList.contains(genus)) {
                        genusList.add(genus);
                    }
                }
            }
            read.close();

            // Print the genus list instead of a for loop
            genusList.forEach(System.out::println);
        } catch (NullPointerException e) {
            System.out.printf("");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(int i = 0; i < genusList.size(); i++) {
            System.out.printf("%s - %s\n", i+1, genusList.get(i));
        }
        System.out.println("Would you like to know more information on the plant?(Y/N)");
        input.nextLine();
        String respose = input.nextLine();
        if(respose.toUpperCase().startsWith("Y") || respose.toUpperCase().startsWith("T")) {
            System.out.printf("Please choose one of the families above by number of 1-%s:\n",genusList.size());
            respose = input.nextLine();
            PlantIndex.plantIndex(genusList.get(Integer.parseInt(respose)-1));
        }
    }
    public static void lookAtPlants() throws PlantExceptions.NoPlantFound {
         /*
        DESCRIPTION: switch case to give more options to users
        PRE-CONDITION: receiving user input from switch case to see what user would like to check  ;
        POST-CONDITION: returns total number succulents or foliage if choosing 1 or 2 and if choosing 3, 4, or 5 will either receive another method or break the loop. If the user does not have a plant added in case 1 or 2 it will throw a custom error
        */
        System.out.println("This is the GreenHouse! A place where you can see the total list of items in you");
        Integer response = 0;
        do {
            try {
            System.out.println(
                    "Here are the options to check out your virtual greenhouse:\n1.Succulents\n2.Foliage\n3.Plantary: Learn more info about your plants\n4.LeafLink: index of all plants\n5.Go Back to Main Menu");
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
                    break;
                case 3:
                    plantary();
                    break;
                case 4:
                    leafLink();
                    break;
                case 5:
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
    public static void deletePlant () {
        System.out.println("Which plant would you like to delete?");

        List <Foliage> allFoliage = foliagePlantContainer.getAllPlants();
        List <Succulent> allSucculent = succulentPlantConatinaer.getAllPlants();
        for (int i = 0; i < allFoliage.size(); i++){
            System.out.printf("%s ---> %s", allFoliage.get(i).getId(),allFoliage.get(i).getName());
        }
        for (int i = 0; i < allSucculent.size(); i++){
            System.out.printf("%s ---> %s", allSucculent.get(i).getId(),allSucculent.get(i).getName());
        }
        System.out.println("\nPlease copy the id of the plant you would like to remove?\nPlant ID:");
        String uuidResponse = input.nextLine();
        Optional <Foliage> foundPlant = allFoliage.stream().filter(plant-> plant.getId().equals(uuidResponse.trim())).findFirst();
        //distinguish between foliage and succulent
        if (foundPlant.isPresent()) {
           System.out.printf("Found plant: %s", foundPlant.get().getName());
           allFoliage.remove(foundPlant);
        } else if (foundPlant.isPresent()) {

        } else {
            System.out.println("Plant not found");
        }
        //edge case for if there are more than one of the same item



    }
}
