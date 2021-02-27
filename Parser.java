import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.util.ElementScanner14;

import java.util.Scanner;

public class Parser {
    static final List<String> PRIMARIES = Arrays.asList("movie", "director");
    static final List<String> SECONDARIES_MOVIE =
        Arrays.asList("director", "name", "release_year", "nominations", "rating", "duration", "genre");
    static final List<String> SECONDARIES_DIRECTOR =
        Arrays.asList("year", "director", "birth_year", "gender");

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

    // Isabelle
    public static void printData(String data) {
        // uses output from findData to print the requested data
    }

    // Landon
    public static String[] parseCommand(String input) {
        // uses if statements and regex to determine what data the user is requesting,
        // then calls findData and printData to output the result
        if (input.equals("exit")) {
            // Return empty array if there is an issue
            return new String[0];
        }

        if (input.equals("help")) {
            printHelp();
            return new String[0];
        }

        String[] queryData = new String[3];

        // enter regex pattern as a string
        String pattern = "(?:(\\w+) (\\w+) \"?([\\w\\s]*)\"?)";

        // create Pattern object
        Pattern r = Pattern.compile(pattern);
        
        // create Matcher object
        Matcher m = r.matcher(input);
        if (m.find()) {
            // Group 0 is the full match, i.e. the entire input string. The three after that (1, 2, 3) are the query parts
            queryData[0] = m.group(1);
            queryData[1] = m.group(2);
            queryData[2] = m.group(3);
        } else {
            System.out.println("ERROR: You have entered a command that does not match the required format.\n"+
                "Please ensure that you enter in the format:\n"+
                "[secondary characteristic] [primary characteristic] (\")search query(\")");
            return new String[0];
        }

        // validate the primary characteristic
        if (!PRIMARIES.contains(queryData[1])) {
            System.out.println("Your primary keyword (the second one) is not valid.\n"+
                "Here are the valid primary keywords:\n" +
                PRIMARIES+"\n");
            return new String[0];
        } else {
            if (queryData[1].equals("movie") && !SECONDARIES_MOVIE.contains(queryData[0])) {
                System.out.println("Your secondary keyword (the first one) is not valid.\n"+
                    "Here are the valid secondary keywords for 'movie':\n" +
                    SECONDARIES_MOVIE+"\n");
                return new String[0];
            }
            if (queryData[1].equals("director") && !SECONDARIES_DIRECTOR.contains(queryData[0])) {
                System.out.println("Your secondary keyword (the first one) is not valid.\n"+
                    "Here are the valid secondary keywords for 'director':\n" +
                    SECONDARIES_DIRECTOR+"\n");
                return new String[0];
            }
        }
        return queryData;
    }

    // main includes a while loop that constantly asks for and validates input until exit command
    // main also loads the table a single time

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String userInput = "";

        //menu and input loop
        System.out.println("Welcome to the Best Picture data helper");
        printHelp();
        while(!userInput.toLowerCase().equals("exit")){
            userInput = input.nextLine();
            System.out.println();
            String[] queryData = parseCommand(userInput);
            if (!(queryData.length == 0)) {
                // If queryData is empty, then userInput does not contain a query
                // and we should loop again.
                // Otherwise, send to findData to do the SQL query.
                findData(queryData[0], queryData[1], queryData[2]);
                System.out.println("\nReady for next command.\n");
            }
        }

        input.close();
    }
}
