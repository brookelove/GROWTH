import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class MainMenu {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Foliage> foliageList = new ArrayList<Foliage>();
    static ArrayList<Succulent> succulentList = new ArrayList<Succulent>();
    static ArrayList <String> choiceList = new ArrayList<String>();
    static BufferedReader reader;
    public static void newPlantInfo() {
        String uuid;
        String commonName = null;
        String howLong;
        String hasVariegation;
        String planType = null;
        Integer potSize = null;
        Float price = null;
        String shapeOfLeafRes;
        String colorOfLeaf;
        Foliage newFoliage;
        Succulent newSucculent;

        uuid = UUID.randomUUID().toString();
        plantIndex();
        System.out.println("Please choose one of the numbers below that correspond to these plants: ");
        for (int i = 0; i < choiceList.size(); i++) {
            System.out.printf("%s - %s\n",i+1, choiceList.get(i));
        }
        Integer userInput = input.nextInt();
        commonName = choiceList.get(userInput-1);
        System.out.println("How long have you owned this plant?");
        howLong = input.nextLine();
        System.out.println("What is the size of your pot in inches (please only use numbers)?");
        potSize = input.nextInt();
        System.out.println("How much did your plant cost?");
        price = input.nextFloat();

        if ( planType.equals("foliage")) {
        System.out.println("Does your plant have variegation to it(colors or characteristics that you would not typically see).\nIf so what type is it or what are the color that you can see?");
        hasVariegation = input.nextLine();
        System.out.println("Since you have a foliage plant what type of leaf shape do you have?");
        shapeOfLeafRes = input.nextLine();
        System.out.println("What's the colors of your leaves?");
        colorOfLeaf = input.nextLine();
        newFoliage = new Foliage(uuid, commonName, howLong, hasVariegation, potSize, price, shapeOfLeafRes, colorOfLeaf);
        foliageList.add(newFoliage);
        } else {
            newSucculent = new Succulent(uuid,commonName,howLong, potSize, price);
            succulentList.add(newSucculent);
        }
    }
    public static void getGreenHouse() {
        Integer response;
        do {
            System.out.println(
                    "Here are the options to check out your virtual greenhouse:\n1.Succulents\n2.Foliage\n3.Plants\n4.Plantary\n5.Go Back to Main Menu");
            response = input.nextInt();
            switch (response) {
                case 1:
                    System.out.printf("Total Succulents Added: %s\n", succulentList.size());
                    break;
                case 2:
                    System.out.printf("Total Foliage Added: %s\n", foliageList.size());
                    for (Integer i = 0; i < foliageList.size(); i ++) {
                        System.out.println("List of names:");
                        System.out.println(foliageList.get(i).getCName());
                    }
                    break;
                case 3:
                    System.out.printf("Total Plants: %s\n", succulentList.size() + foliageList.size());
                    break;
                case 4:
                    System.out.println("Your go to resource for the secrets about your plants");
                    plantary();
                    break;
                case 5:
                    System.out.println("Great lets go back!");
                    return; // makes it return and go back rather than end the entire system
                default:
                    System.out.printf("%s is not valid please try again. Make sure you don't follow your response with spaces.", response);
            }
        } while(response != 4);
    }
    public static void plantIndex(){
        System.out.println("Welcome to Plantary! Your go to resource for the secrets of plants with our index.\nPlease type in a letter of the common name to see the plants that start with that letter.");
        String getGloss = input.nextLine();
        getGloss = getGloss.toUpperCase();
        System.out.printf("Here is that list of plants that start with the letter %s!\n", getGloss);
        try{
            reader = new BufferedReader(new FileReader("/Users/brookelove/code/bostonU/MET_CS_622/GROWTH/GROWTH/src/PlantDictionary.txt"));
            String line = reader.readLine();
            while (true) {
                if(line == null){
                    break;
                }
                line = reader.readLine();
                if(line.contains("Common_Name:")){
                    String splitLine [] =  line.split("Common_Name: ");
                    String namePlant = Array.get(splitLine,1).toString();
                    if(namePlant.startsWith(getGloss)){
                        namePlant = namePlant.replace(",", "");
                        choiceList.add(namePlant);
                    }
                }
            }
            reader.close();
            System.out.println(choiceList);
        }catch (NullPointerException e){
            System.out.printf("");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void plantary (){
        //place where they can get information on their plants
        System.out.println("Let's get some information on your plants:\nWould you like to get either 1-Foliage\n2-Succulent\n3-All. Please choose one of the three numbers given");
        Integer plantChoiceNum = input.nextInt();
        try{
            reader = new BufferedReader(new FileReader("/Users/brookelove/code/bostonU/MET_CS_622/GROWTH/GROWTH/src/PlantDictionary.txt"));
            String line = reader.readLine();
            while (true) {
                if(line == null){
                    break;
                }
                line = reader.readLine();
                if(plantChoiceNum.equals(1)) {
                    //find all plants that are in the group and are equal to foliage
                } else if (plantChoiceNum.equals(2)) {
                    //find all plants that are in the group and are equal to succulent
                } else {
                    //find all plants that are in the group
                }
            }
            reader.close();
            System.out.println(choiceList);
        }catch (NullPointerException e){
            System.out.printf("");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


