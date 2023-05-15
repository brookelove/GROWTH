import java.util.*;
import java.util.Scanner;

import Plant.Foliage;
import Plant.Succulent;

public class Options {
    Scanner input = new Scanner(System.in);
    ArrayList<Foliage> foliageList = new ArrayList<Foliage>();
    ArrayList<Succulent> succulentList = new ArrayList<Succulent>();

    public void newPlantInfo() {
        String commonName;
        String Age;
        Boolean hasVarigation;
        Integer potSize;
        Float price;

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
                    "Great you've found a foliage! Lets get some informationa about your new plant so we can get it settled and growing!\n How old is your plant(you can be specific as months or just say its a sapling to mature)? ");
            // turn this into a template literal so then I can then use it for more cases
            Age = input.nextLine();
            System.out.println(
                    "Does your plant have varigation to it(colors or characteristics that you would not typically see)?");
            String variResponse = input.nextLine();
            if (variResponse.toUpperCase().startsWith("T") || variResponse.toUpperCase().startsWith("Y")) {
                hasVarigation = true;
            } else {
                hasVarigation = false;
            }
            System.out.println("What is the size of your pot(please ony use numbers)?");
            potSize = input.nextInt();

            System.out.println("How much did you pay for the plant?");
            price = input.nextFloat();

            // create a new object with the implementation of a plant
            Foliage newPlant = new Foliage(commonName, Age, hasVarigation, potSize, price);

            // then add the new plant to the arrayList of foliage
        }

    }
}
