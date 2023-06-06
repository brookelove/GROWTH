import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        /*
        DESCRIPTION: Sets the main menu on user response asking for where the user would link to be located
        PRECONDITION: String response can not be null or integer that is 0;
        POSTCONDITION: returns the string method that is fit of the user response dependent on what the user provides;
        */
        System.out.println(
                "Welcome to GROWTH! This is a place where you can categorize your plants as well as learn about your plants health!");
        Integer response;
        do {
            System.out.println(
                    "\nHere are the options to check out your virtual greenhouse:\n1.New Plant\n2.Greenhouse\n3.Update a Plant\n4.Delete A Plant\n5.Exit");
            response = input.nextInt();
            // response.toUpperCase();
            switch (response) {
                case 1:
//                    System.out.println("Add a Plant");
                    MainMenu.addPlant();
                    break;
                case 2:
//                    System.out.println("GreenHouse");
                    MainMenu.lookAtPlants();
                    break;
                case 3:
//                    System.out.println("Update Plant");
                    break;
                case 4:
//                    System.out.println("Remove Plant");
                    MainMenu.deletePlant();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.printf("%s is not valid please try again.", response);
            }
        } while (response != 6);
    }
}
