// Purpose: Read text file, remove stop words, obtain word count of all words (except stop words),
//          arrange them in order

package readWrite;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

// https://gist.github.com/sebleier/554280 - Stop Word List

public class WordCountMain {

   public static void main(String[] args) throws FileNotFoundException, IOException {
    
      // Open stopWordFile for reading
      String stopWordFile = args[0];

      Reader stopWordReader = new FileReader(stopWordFile);
      BufferedReader stopWordBr = new BufferedReader(stopWordReader);

      // Create a set for the stop words (since they are unique)
      Set<String> stopWords = new HashSet<>();

      while (true) {
         // Read a line
         String line = stopWordBr.readLine();

         // If line is null, we have reach the EOF
         if (null == line)
            break;

         // Transform stop words to lower + trim (prevent formatting issues if any)
         String transformed = line.toLowerCase().trim();

         // Add the stop words to the set
         stopWords.add(transformed);
      }

      // Open inputFile for reading
      String inputFile = args[1];
      
      Reader reader = new FileReader(inputFile);
      BufferedReader bufferedReader = new BufferedReader(reader);

      // Create a map
      Map<String, Integer> uniqueWords = new HashMap<>();

      while (true) {
         // Read a line
         String line = bufferedReader.readLine();
         
         // If line is null, we have reach the EOF
         if (null == line)
            break;

         String transformed = line.replaceAll("\\p{Punct}", "").toLowerCase().trim();

         for (String word: transformed.split(" ")) {
            int currentCount = 0;
            if (uniqueWords.containsKey(word))
               currentCount = uniqueWords.get(word);

            currentCount++;

            uniqueWords.put(word, currentCount);
         }

      }

      // Remove key-value pair from uniqueWords map if it matches the stop words
      uniqueWords.keySet().removeAll(stopWords);

      // !!!! Remove the "" (empty string) in the Map (not sure how to remove before it was added)
      uniqueWords.keySet().remove("");

      // Close the files
      bufferedReader.close();
      stopWordBr.close();

      // Print the keys set in alphabetical order

      // Create a tree map object (sorted map)
      TreeMap<String, Integer> uniqueWordsSorted = new TreeMap<>(uniqueWords);

      for (String word: uniqueWordsSorted.keySet())
         System.out.printf("{%s: %d}\n", word, uniqueWordsSorted.get(word));
   }

}