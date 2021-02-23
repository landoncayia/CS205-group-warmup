import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private int number;

    // Eadoin and Zach need to be involved with this, but we can create a dummy table in the meantime.
    public static boolean loadTables() {
        // This method connects to the database and populates the tables
        return false;
    }

    // Eadoin and Zach
    public static boolean populateTable(String table, String csv) {
        // This method adds the data from the given csv into the table, returns boolean for
        // whether it was successful or not.
        return false;
    }

    // All of us
    public static String findData(String group1, String group2, String group3) {
        

        // Takes the parameters from the regEx and inputs them into an SQL query. Returns
        // the information returned by the SQL statement
        return "a";
    }

    // Landon and Isabelle can mess around with this maybe -- stretch goal
    public static ArrayList findMultiple(String table, String param, String operation, double value) {
        // Stretch goal function, takes in which table is being searched, the column being searched,
        // the operation ( < / > / = ), and the value. This works like findData, but it uses an
        // ArrayList to return multiple data points from the table that fit the description.
        ArrayList placeHolder = new ArrayList<String>(3);
        return placeHolder;
    }

    // Isabelle
    public static boolean validateInput(String input) {
        // checks that the user has only entered valid commands and prints an error
        // message otherwise
        return false;
    }

    // Isabelle
    public static void printHelp() {
        //prints the help menu
        System.out.println("-- HELP MENU --");
        System.out.println("To close the program at any time, write 'exit'");
        System.out.println("To request data about a movie, enter a command of the form [category] movie [movie name]");
        System.out.println("Alternatively, you can request data about a movie based on the director with a command of the form [category] movie director [director name]");
        System.out.println("Category options for movies are: name, release_year, nominations, rating, duration, genre");
        System.out.println("To request data about a director, enter a command of the form [category] director [director name]");
        System.out.println("Category options for directors are: year, movie, birth_year, gender");
    }

    // Landon
    public static void printData(String data) {
        // uses output from findData to print the requested data
    }

    // Landon
    public static String[] parseCommand(String input) {
        // uses if statements and regex to determine what data the user is requesting,
        // then calls findData and printData to output the result
        String[] placeHolder = new String[3];
        return placeHolder;
    }

    // main includes a while loop that constantly asks for and validates input until exit command
    // main also loads the table a single time

    public static void main(String[] args) {
        System.out.println("This is main");
    }
}
