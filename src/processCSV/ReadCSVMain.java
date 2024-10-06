// Purpose of code: Process googleplaystore.csv

// For each app category:
// Display 1. Category name; 2. Highest rated app name and rating;
//         3. Lowest rated app name and rating; 4. Avg rating for category

package processCSV;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Double;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReadCSVMain {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String fileName = args[0];
        char splitBy = args[1].charAt(0);

        // List to store different Apps
        List<App> appList = new ArrayList<>();

        // Set to store all categories
        Set<String> categories = new HashSet<>();

        // Read the CSV File
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);

        String line;

        int count = 0;

        while (true) {
            line = br.readLine();
            count++;
            // null = reached End Of File (EOF)
            if (line == null)
                break;

            // Skip first line of csv (header)
            if (count == 1)
                continue;

            List<String> infoList = parseCsvLine(line, splitBy);
        
            // Add relevant info (app name, category, rating) to App class
            String name = infoList.get(0).trim();
            String category = infoList.get(1).trim();
            Double rating = Double.parseDouble(infoList.get(2).trim());
            if(rating.isNaN()) {    // Apps with NaN rating will not be added to appList
                continue;
            }
            App app = new App(name, category, rating);
            appList.add(app);
        }

        // Obtain a set of all categories
        for(App app : appList) {
            categories.add(app.getCategory());
        }

        // Print highest rated app of each category
        for (String category : categories) {
            highestRating(appList, category);
        }

        // Print lowest rated app of each category
        for (String category : categories) {
            lowestRating(appList, category);
        }

        // Print average rating of each category
        for (String category : categories) {
            averageRating(appList, category);   
        }

        // Close reader
        br.close();
    }

    

    // Create custom method to read CSV line since there are quoted fields with "delimiter" (e.g. "hello, world")
    public static List<String> parseCsvLine(String line, char splitBy) {
        
        StringBuilder currentString = new StringBuilder(0); // StringBuilder is builtin; it is a mutable sequence of chr
        boolean inQuotes = false;
        List<String> splitResult = new ArrayList<>();

        // Iterate through the line by character
        for (char chr : line.toCharArray()) {
            if (chr == '\"') {                                 // Note use of single quote to denote character
                inQuotes = !inQuotes;
                currentString.append(chr);
            } else if (!inQuotes && chr == splitBy) { // Case 1. "," but not in quotes --> delimiter --> add currentString
                                                  // to List + reset CurrentString to empty
                splitResult.add(currentString.toString());
                currentString.setLength(0);
            } else {
                currentString.append(chr);
            }
        }

        // Add the last field (after the loop ends)
        splitResult.add(currentString.toString());
        return splitResult;
    }

    // Create method to get highest rating of category
    //      returns: String "Highest Rating of (category): (app name), (rating)"
    public static void highestRating(List<App> appList, String category) {
        double highest = 0;
        String highestApp = "";

        for (App app : appList) {
            double rating = app.getRating();
            if (app.getCategory().equals(category) && rating >= highest) {
                    highest = rating;
                    highestApp = app.getName();
            }
        }
        System.out.printf("Highest Rating of %s: %s, %f\n", category, highestApp, highest);
    }

    // Create method to get highest rating of category
    //      returns: String "Highest Rating of (category): (app name), (rating)"
    public static void lowestRating(List<App> appList, String category) {
        double lowest = 1000000;
        String lowestApp = "";

        for (App app : appList) {
            double rating = app.getRating();
            if (app.getCategory().equals(category) && rating <= lowest) {
                    lowest = rating;
                    lowestApp = app.getName();
            }
        }
        System.out.printf("Lowest Rating of %s: %s, %f\n", category, lowestApp, lowest);
    }

    public static void averageRating(List<App> appList, String category) {
        double total = 0;
        double appCount = 0;
        
        for (App app : appList) {
            double rating = app.getRating();
            if (app.getCategory().equals(category)) {
                total += rating;
                appCount ++;
            }
        }
        double average = total / appCount;
        System.out.printf("Average Rating of %s: %.3f\n", category, average);
    }
}