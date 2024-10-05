// Instructions:
// Read from file using FileReader --> BufferedReader
// Determine the number of unique words in the text file

package readWrite;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

public class UniqueWordsMain {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String inputFile = args[0];

        // Open inputFile for reading
        Reader reader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(reader);

        // Create a set of string
        Set<String> uniqueWords = new HashSet<String>();

        while(true) {
            // Read a line
            String line = bufferedReader.readLine();

            // If line is null, we have reaced the EOF
            if (null == line) {
                break;
            }

            String transformed = line.replaceAll("\\p{Punct}", "").toLowerCase().trim();
            for (String word: transformed.split(" ")) {
                uniqueWords.add(word);
            }
        }

        bufferedReader.close();

        System.out.printf("Unique words in %s: %d\n", inputFile, uniqueWords.size());
    }
}
