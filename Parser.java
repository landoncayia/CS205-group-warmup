import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private int number;

    public static boolean loadTables() {
        // This method connects to the database and populates the tables
        return false;
    }

    public static boolean populateTable(String table, String csv) {
        // This method adds the data from the given csv into the table, returns boolean for
        // whether it was successful or not.
        return false;
    }

    public static String findData(String group1, String group2, String group3) {
        // Takes the parameters from the regEx and inputs them into an SQL query. Returns
        // the information returned by the SQL statement
        return "a";
    }

    public static ArrayList findMultiple(String table, String param, String operation, double value) {
        // Stretch goal function, takes in which table is being searched, the column being searched,
        // the operation ( < / > / = ), and the value. This works like findData, but it uses an
        // ArrayList to return multiple data points from the table that fit the description.
        ArrayList placeHolder = new ArrayList<String>(3);
        return placeHolder;
    }

    public static boolean validateInput(String input) {
        // checks that the user has only entered valid commands and prints an error
        // message otherwise
        return false;
    }

    public static void printHelp() {
        //prints the help menu
        System.out.println("This is help");
    }

    public static void printData(String data) {
        // uses output from findData to print the requested data
    }

    public static void parseCommand(String input) {
        // uses if statements and regex to determine what data the user is requesting,
        // then calls findData and printData to output the result
    }

    // main includes a while loop that constantly asks for and validates input until exit command
    // main also loads the table a single time

    public static void main(String[] args) {
        System.out.println("This is main");
    }
}
