import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Parser {
    static final List<String> PRIMARIES = Arrays.asList("movie", "director");
    static final List<String> SECONDARIES_MOVIE =
        Arrays.asList("director", "title", "release_year", "nominations", "rating", "duration", "genre");
    static final List<String> SECONDARIES_DIRECTOR =
        Arrays.asList("year", "director", "birth_year", "gender");
    static final String USER = "root";
    static String PASSWORD = "";

    // Eadoin and Zach need to be involved with this, but we can create a dummy table in the meantime.
    public static boolean loadTables() {
        // This method connects to the database and populates the tables
        try{
            //Establish connection to database
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", USER, PASSWORD);

            //Creates Database
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE DATABASE moviedirectors");

            //Establishes Connection to created database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedirectors", USER, PASSWORD);
            stmt = con.createStatement();

            //Creates table
            stmt.executeUpdate("CREATE TABLE movies(title varchar(100),release_year int,nominations int,rating double,duration int,genre varchar(20),PRIMARY KEY (release_year));");
            stmt.executeUpdate("CREATE TABLE directors(id int,year int,name varchar(100),birth_year int,gender char(1),PRIMARY KEY (ID),FOREIGN KEY (year) REFERENCES movies(release_year));");

            stmt.close();
            con.close();
            populateTableMovies();
            populateTableDirectors();
            System.out.println("Tables Loaded!");
        } catch (Exception e){
            System.out.println(e);
            System.out.println("Tables not able to be loaded.");
        }
        
        
        return false;
    }

    // Eadoin and Zach
    public static boolean populateTableDirectors() {
        final String DELIMITER = ",";
            try {
                //Initialize reader and connection to database
                File file = new File("MovieDirectors.csv");
                Scanner fr = new Scanner(file);
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedirectors", USER, PASSWORD);
                Statement stmt = con.createStatement();
                //Initialize Strings for line input and sql statement
                String line = "";
                String sql = "";
                String[] tempArr;
                //Skipping the first line of headers
                fr.nextLine();
                //Looping and adding csv content to table
                while(fr.hasNextLine()) {
                    line = fr.nextLine();
                    tempArr = line.split(DELIMITER);
                    sql = "INSERT INTO directors (id, year, name, birth_year, gender) VALUES (";
                    sql = sql + "'" + Integer.parseInt(tempArr[0]) + "', '" + Integer.parseInt(tempArr[1]) + "', '"+ tempArr[2] + "', '"+ Integer.parseInt(tempArr[3]) + "', '"+ tempArr[4] + "');"; 
                    stmt.executeUpdate(sql);
                    sql = "";
                }
                //Closing connections
                fr.close();
                stmt.close();
                con.close();
            } catch(FileNotFoundException ioe) {
                ioe.printStackTrace();
            } catch(Exception e){
                System.out.println(e);
            }
        
        // This method adds the data from the MovieDirectors csv into the table, returns boolean for
        // whether it was successful or not.
        return false;
    }

    public static boolean populateTableMovies() {
        final String DELIMITER = ",";
            try {
                //Initialize reader and connection to database
                File file = new File("BestMovies.csv");
                Scanner fr = new Scanner(file);
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedirectors", USER, PASSWORD);
                Statement stmt = con.createStatement();
                //Initialize Strings for line input and sql statement
                String line = "";
                String sql = "";
                String[] tempArr;
                //Skipping the first line of headers
                fr.nextLine();
                //Looping and adding csv content to table
                while(fr.hasNextLine()) {
                    line = fr.nextLine();
                    tempArr = line.split(DELIMITER);
                    sql = "INSERT INTO movies (title, release_year, nominations, rating, duration, genre) VALUES (";
                    sql = sql + "\"" + tempArr[0] + "\", '" + Integer.parseInt(tempArr[1]) + "', '"+ Integer.parseInt(tempArr[2]) + "', '"+ tempArr[3] + "', '"+ Integer.parseInt(tempArr[4]) + "', '"+ tempArr[5] +"');"; 
                    stmt.executeUpdate(sql);
                    sql = "";
                }
                //Closing connections
                fr.close();
                stmt.close();
                con.close();
            } catch(FileNotFoundException ioe) {
                ioe.printStackTrace();
            } catch (Exception e) {
                System.out.println(e);
            }
        
        // This method adds the data from the MovieDirectors csv into the table, returns boolean for
        // whether it was successful or not.
        return false;
    }

    // All of us
    public static String findData(String group1, String group2, String group3) {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedirectors", USER, PASSWORD);
            Statement stmt = con.createStatement();
            //Correcting "Director" when looking for the director of a movie
            if(group1.equals("director")){
                group1 = "name";
            }
            //Starts SQL statement
            String sql = "SELECT " + group1 + " FROM movies JOIN directors ON movies.release_year = directors.year WHERE ";
            if(group2.equals("movie")){ //If looking for something based on the movie title
                sql = sql + "movies.title LIKE \"%" + group3 + "%\";";
            } else if(group2.equals("director")){ //If looking for something based on the directors name
                sql = sql + "directors.name LIKE \"%" + group3 + "%\";";
            } else { //Looking cross tables
                String[] tempGroup;
                tempGroup = group2.split(" ");
                if(tempGroup[1].equals("movie")){
                    sql = sql + "movies.title LIKE \"%" + group3 + "%\";";
                } else {
                    sql = sql + "directors.name LIKE \"%" + group3 + "%\";";
                }
            }
            String result = "No data found for " + group3;
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                result = rs.getString(group1);
            }
            printData(result);
            rs.close();
            stmt.close();
            con.close();
            return result;
            
        } catch (Exception e){
            System.out.println(e);
        }

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
        System.out.println("To request data about a movie, enter a command of the form [category] movie \"[movie name]\"");
        System.out.println("Alternatively, you can request data about a movie based on the director with a command of the form [category] \"movie director\" \"[director name]\"");
        System.out.println("Category options for movies are: name, release_year, nominations, rating, duration, genre");
        System.out.println("To request data about a director, enter a command of the form [category] director \"[director name]\"");
        System.out.println("Category options for directors are: year, movie, birth_year, gender");
        System.out.println("*** PLEASE NOTE *** that any use of quotes in the example above are required to group together the parts of the query.\n");
    }

    // Isabelle
    public static void printData(String data) {
        // uses output from findData to print the requested data
        if(data.equals("M")){
            System.out.println("Male");
        }
        else if(data.equals("F")){
            System.out.println("Female");
        }
        else{
            System.out.println(data);
        }
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
        String pattern = "(?:(\\w+) \"?([\\w\\s]*)\"? \"?([\\w\\s]*)\"?)";

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
        // first, split the second group (index 1) and check whether its length is 1 or 2
        String[] splitGroup1 = queryData[1].split(" ");
        // if length is one, check if it is either "director" or "movie"
        if (splitGroup1.length == 1 && !PRIMARIES.contains(queryData[1])) {
            System.out.println("Your primary keyword (the second word) is not valid.\n"+
            "Here are the valid primary keywords (use one or a combo in quotes):\n"+
            PRIMARIES+"\n");
            return new String[0];
        }
        // if length is two, check if it is either "director movie" or "movie director"
        if (splitGroup1.length == 2 &&
            (!PRIMARIES.contains(splitGroup1[0]) || !PRIMARIES.contains(splitGroup1[1]) || splitGroup1[0].equals(splitGroup1[1]))) {    
            System.out.println("Your primary keyword combination is not valid\n"+
            "Here are the valid primary keywords (use one or a combo in quotes):"+
            PRIMARIES+"\n");
            return new String[0];
        }
        
        else {
            // validate the secondary characteristic for a movie query
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
            if (queryData[1].equals("movie director") && !SECONDARIES_MOVIE.contains(queryData[0])) {
                System.out.println("Your secondary keyword (the first one) is not valid.\n"+
                                   "Here are the valid secondary keywords for 'movie':\n"+
                                    SECONDARIES_MOVIE+"\n");
                return new String[0];
            }
            if (queryData[1].equals("director movie") && !SECONDARIES_DIRECTOR.contains(queryData[0])) {
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

        //Password here
        System.out.println("Enter your SQL password. If you don't have one, press ENTER: ");
        PASSWORD = input.nextLine();
        //Check to see if databases have been loaded
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedirectors", USER, PASSWORD);
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT title FROM movies WHERE release_year = 2014");
            
        } catch (Exception e){
            System.out.println("Loading tables...");
            loadTables();
        }

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
