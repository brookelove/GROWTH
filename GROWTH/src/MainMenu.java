import java.util.*;
import java.util.Scanner;
//import Plant.Foliage;

public class MainMenu {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Foliage> foliageList = new ArrayList<Foliage>();
    static ArrayList<Succulent> succulentList = new ArrayList<Succulent>();

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
        System.out.println("What is the Common name of your Plant?");
        commonName = input.nextLine();
        // need to locate the plant!
        System.out.println("Great you've found a foliage! Lets get some information about your new plant so we can get it settled and growing!\n" +
                "How long have you owned this plant?");
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
        // need to use the common name vs the actual name option
        // System.out.println("You choose: " + mainMenu);
        do {
            System.out.println(
                    "Here are the options to check out your virtual greenhouse:\n1.Succulents\n2.Foliage\n3.Plants\n4.Go Back to Main Menu");
            response = input.nextInt();
            switch (response) {
                case 1:
                    System.out.printf("Total Succulents: %s\n", succulentList.size());
                    break;
                case 2:
                    System.out.printf("Total Foliage: %s\n", foliageList.size());
                    for (Integer i = 0; i < foliageList.size(); i ++) {
                        System.out.println("List of names:");
                        System.out.println(foliageList.get(i).getCName());
                    }
                    break;
                case 3:
                    System.out.printf("Total Plants: %s\n", succulentList.size() + foliageList.size());
                    break;
                case 4:
                    System.out.println("Great lets go back!");
                    return; // makes it return and go back rather than end the entire system
                default:
                    System.out.printf("%s is not valid please try again. Make sure you don't follow your response with spaces.", response);
            }
        } while(response != 4);
    }
    public static void plantDictionary(){
        System.out.println("Welcome to Plantary! Your go to resource for the secrets of plants with our dictionary");
    }

}

