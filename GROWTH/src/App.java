import java.util.Scanner;

public class App {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println(
                "Welcome to GROWTH! This is a place where you can categorize your plants as well as learn about your plants health!");

        String mainMenu;
        Integer response;
        // need to use the common name vs the actual name option
        // System.out.println("You choose: " + mainMenu);
        do {
            System.out.println(
                    "Here are the options to check out your virtual greenhouse:\n1.Add a New Plant\n2.Check out Greenhouse\n3.Update a Plant\n4. Delete A Plant\n5. Get Info\n6.Exit");
            response = input.nextInt();
            // response.toUpperCase();
            switch (response) {
                case 1:
                    System.out.println("Great lets add a new plant!");
                    MainMenu.newPlantInfo();
                    break;
                case 2:
                    System.out.println("Onto the GreenHouse!");
                    MainMenu.getGreenHouse();
                    break;
                case 3:
                    System.out.println("Lets update that Plant!");
                    break;
                case 4:
                    System.out.println("Lets remove one of you plants from the inventory!");
                    break;
                case 5:
                    System.out.println("Plantary");
                    MainMenu.plantDictionary();
                    break;
                case 6:
                    System.out.println("Ok we will see you later!");
                    System.exit(0);
                    break;
                default:
                    System.out.printf("%s is not valid please try again.", response);
            }
        } while (response != 6);
    }
}
