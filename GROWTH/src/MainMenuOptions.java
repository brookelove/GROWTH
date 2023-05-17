import java.util.*;
import java.util.Scanner;
//import Plant.Foliage;

public class MainMenuOptions {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Foliage> foliageList = new ArrayList<Foliage>();
    static ArrayList<Succulent> succulentList = new ArrayList<Succulent>();

    public static void newPlantInfo() {
        String commonName;
        String Age;
        Boolean hasVarrigation;
        Integer potSize;
        Float price;
        String shapeOfLeafRes;
        String colorOfLeaf;

        System.out.println("Do you know the common name of your plant?(Y/N)");
        String commonNameResp = input.nextLine();
        // if statement adding in a existing plant with the common name
        if (commonNameResp.toUpperCase().startsWith("Y")) {
            System.out.println("What is the common name of your plant?");
            commonName = input.nextLine();
            // search database for common name and if the name is in there tell the user
            // that it is either a succulent/cacti or foliage
            // add the plant to be a new foliage or succulent depending on what it returns
            // if it is a foliage category then we would add a new foliage
            System.out.println(
                    "Great you've found a foliage! Lets get some information about your new plant so we can get it settled and growing!\nHow old is your plant(you can be specific as months or just say its a sapling to mature)? ");
            // turn this into a template literal so then I can then use it for more cases
            Age = input.nextLine();
            System.out.println(
                    "Does your plant have varigation to it(colors or characteristics that you would not typically see)?");
            String varResponse = input.nextLine();
            hasVarrigation = varResponse.toUpperCase().startsWith("T") || varResponse.toUpperCase().startsWith("Y");
            System.out.println("What is the size of your pot(please only use numbers)?");
            potSize = input.nextInt();

            System.out.println("How much did you pay for the plant?");
            price = input.nextFloat();
            System.out.println("Since you have a foliage plant what type of leaf shape do you have?");
            shapeOfLeafRes = input.nextLine();
            System.out.println("What's the colors of your leaves?");
            colorOfLeaf = input.nextLine();
            // create a new object with the implementation of a plant
            Foliage newFoliage;
            newFoliage = new Foliage(commonName, Age, hasVarrigation, potSize, price, shapeOfLeafRes, colorOfLeaf);
            newFoliage.waterPlant();
            foliageList.add(newFoliage);
            // then add the new plant to the arrayList of foliage
        } else {
            //have to first find the common name then ask the original questions so this needs to be restructured
            commonName = "Jose Buono";
            System.out.println(
                    "Great you've found a foliage! Lets get some information about your new plant so we can get it settled and growing!\nHow old is your plant(you can be specific as months or just say its a sapling to mature)? ");
            // turn this into a template literal so then I can then use it for more cases
            Age = input.nextLine();
            System.out.println(
                    "Does your plant have varigation to it(colors or characteristics that you would not typically see)?");
            String varResponse = input.nextLine();
            hasVarrigation = varResponse.toUpperCase().startsWith("T") || varResponse.toUpperCase().startsWith("Y");
            System.out.println("What is the size of your pot(please only use numbers)?");
            potSize = input.nextInt();

            System.out.println("How much did you pay for the plant?");
            price = input.nextFloat();
            System.out.println("Since you have a foliage plant what type of leaf shape do you have?");
            shapeOfLeafRes = input.nextLine();
            System.out.println("What's the colors of your leaves?");
            colorOfLeaf = input.nextLine();
            Plant unkownPlant = new Foliage(commonName, Age, hasVarrigation, potSize, price, shapeOfLeafRes, colorOfLeaf);
            if (unkownPlant instanceof Foliage) {
                Foliage newFoliage = (Foliage) unkownPlant;
                newFoliage.checkRoots();
            }

        }

    }
    public static void getGreenHouse() {
        String response;
        // need to use the common name vs the actual name option
        // System.out.println("You choose: " + mainMenu);
        do {
            System.out.println(
                    "Here are the options to check out your virtual greenhouse:\nA.Succulents\nB.Foliage\nC.Plants\nD.Go Back to Main Menu");
            response = input.nextLine().toUpperCase();
            switch (response) {
                case "A":
                    System.out.printf("Total Succulents: %s\n", succulentList.size());
                    break;
                case "B":
                    System.out.printf("Total Foliage: %s\n", foliageList.size());
                    for (Integer i = 0; i < foliageList.size(); i ++) {
                        System.out.println("List of names:");
                        System.out.println(foliageList.get(i).getCName());

                    }
                    break;
                case "C":
                    System.out.printf("Total Plants: %s\n", succulentList.size() + foliageList.size());
                    break;
                case "D":
                    System.out.println("Great lets go back!");
                    return; // makes it return and go back rather than end the entire system
                default:
                    System.out.printf("%s is not valid please try again.", response);
            }
        } while(response != "D");
    }

}
