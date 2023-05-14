import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println(
                "Welcome to GROWTH! This is a place where you can categorize your plants as well as learn about your plants health!");

        String mainMenu;
        String response;
        // need to use the common name vs the actual name option
        // System.out.println("You choose: " + mainMenu);
        do {
            System.out.println(
                    "Here are the options to check out your virtual greenhouse:\nA.Add a New Plant\nB.Check out Greenhouse\nC.Update a Plant\nD. Delete A Plant\nE. Get Info\nF.Exit");
            response = input.nextLine().toUpperCase();
            // response.toUpperCase();
            switch (response) {
                case "A":
                    System.out.println("Great lets add a new plant!");
                    break;
                case "B":
                    System.out.println("Onto the GreenHouse!");
                    break;
                case "C":
                    System.out.println("Lets update that Plant!");
                    break;
                case "D":
                    System.out.println("Lets remove one of you plants from the inventory!");
                    break;
                case "E":
                    System.out.println("Lets update that Plant!");
                    break;
                case "F":
                    System.out.println("Ok we will see you later!");
                    System.exit(0);
                    break;
                default:
                    System.out.printf("%s is not valid please try again.", response);
            }
        } while (response != "F");
    }
}
