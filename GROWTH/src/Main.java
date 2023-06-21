
// import java.sql.Connection;
// import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class Main {
    ArrayList<SecondPlant> plantlist;
    // define the connection of SQLite
    static Connection conn;
    ResultSet rs;
    int row, col;

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // creates connection with the database
        String url = "jdbc:sqlite:plant.db";
        conn = DriverManager.getConnection(url);
        checkTables();

        /*
         * DESCRIPTION: Sets the main menu on user response asking for where the user
         * would link to be located
         * PRECONDITION: String response can not be null or integer that is 0;
         * POSTCONDITION: returns the string method that is fit of the user response
         * dependent on what the user provides;
         */
        System.out.println(
                "Welcome to GROWTH! This is a place where you can categorize your plants as well as learn about your plants health!");
        MainMenu mainMenu = new MainMenu();
        Integer response;
        do {
            System.out.println(
                    "\nHere are the options to check out your virtual greenhouse:\n1.New Plant\n2.Greenhouse\n3.Update a Plant\n4.Delete A Plant\n5.Compress My Data \n6.Exit");
            response = input.nextInt();
            // response.toUpperCase();
            switch (response) {
                case 1:
                    System.out.println(
                            "How many plants would you like to enter (please enter integers greater than or equal to 1).");
                    Integer numOfPlants = input.nextInt();
                    if (numOfPlants == 1) {
                        mainMenu.addPlant(numOfPlants);
                    } else if (numOfPlants > 1) {
                        System.out.println(numOfPlants);
                        mainMenu.addManyPlants(numOfPlants);
                    } else {
                        System.out.println("Please only use numbers greater than 1");
                    }
                    break;
                case 2:
                    MainMenu.lookAtPlants();
                    break;
                case 3:
                    break;
                case 4:
                    MainMenu.deletePlant();
                    break;
                case 5:
                    MainMenu.printPlants();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.printf("%s is not valid please try again.", response);
            }
        } while (response != 7);
    }

    private static void checkTables() {
        System.out.println("Check Tables");
        // common name, type (Foliage or Succulent), pot size(), price varregation
        String sql = "CREATE TABLE IF NOT EXISTS tbl_plants(" + "id integer PRIMARY KEY AUTOINCREMENT,"
                + "common_name text NOT NULL," + "plant_type text NOT NULL" + "pot_size integer NOT NULL"
                + "plant_price integer NOT NULL" + "varregation_type text NOT NULL" + ");";
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql);
        } catch (Exception e) {
            // handle error
            System.out.println(e);
        }
    }

    private void loadData() throws SQLException {
        System.out.println("Load Data");
        plantlist = new ArrayList<>();
        Statement state = conn.createStatement();
        rs = state.executeQuery("select * from tbl_plants");
        plantlist.clear();
        while (rs.next()) {
            plantlist.add(new SecondPlant(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4), rs.getString(5),
                    rs.getInt(6)));
        }
    }
}
