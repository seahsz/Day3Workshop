package wordDistribution;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

// import java.io.FileWriter;
// import java.io.IOException;
// import java.io.BufferedWriter;


public class WordDistributionMain {

    public static void main(String[] args) {
        
        String inputFile = args[0];
        String stopWordFile = args[1];

        StopWord sw = new StopWord(stopWordFile);
        Set<String> stopList = sw.getStopWordList();

        // Reads file and returns a list of Lines
        ReadFile rf = new ReadFile(inputFile);
        List<String> inputLines = rf.readFile(true);

        // Convert the List of lines to a List of words
        List<String> allWords = new ArrayList<>();

        for (String line : inputLines) {
            String[] temp = line.split(" ");
            for (String word : temp) {
                if (word.isEmpty())
                    continue;
                allWords.add(word);
            }
        }

        // Remove the stop words from the list of all words
        allWords.removeAll(stopList);

        // Create a set of unique words 
        Set<String> uniqueWords = new HashSet<>();

        for (String word : allWords)
            uniqueWords.add(word);

        // Create a map for Word Distribution <-- used to store final result
        Map<String, Map<String, Integer>> wordDist = new HashMap<>();

        // Create empty maps for each unique word
        for (String word : uniqueWords) {
            Map<String, Integer> InitMap = new HashMap<>();
            wordDist.put(word, InitMap);
        }
        
        // Populate the word distribution map
        for (int idx = 0; idx < allWords.size(); idx++) {
            String word = allWords.get(idx);
            if (idx == allWords.size() - 1)     // to avoid error when we reach the last word
                break;
            String nextWord = allWords.get(idx+1);
            for (Map.Entry<String, Map<String, Integer>> entrySet : wordDist.entrySet()) {
                String key = entrySet.getKey();
                if (key.equals(word)) {  //***** use .equals instead of == when comparing Strings.
                    Map<String, Integer> value = entrySet.getValue();
                    int currentCount = 0;
                    if (value.containsKey(nextWord))
                        currentCount = value.get(nextWord);

                    currentCount++;

                    value.put(nextWord, currentCount);

                    wordDist.put(word, value); // Reassigns word distribution for current word back into the word distribution map
                }
            }

        }

        // // Only to print out the word distribution of a few keys
        // Set<String> keys = wordDist.keySet();

        // int count = 0;

        // for (String key : keys) {
        //     if (count > 4)
        //         break;
        //     Map<String, Integer> temp1 = wordDist.get(key);
        //     System.out.println("Current word is: " + key);
        //     System.out.println("Size of word dictionary for " + key + " is " + temp1.size());
        //     for (Map.Entry<String, Integer> entry : temp1.entrySet())
        //         System.out.println("Next word: " + entry.getKey() + " , Count is: " + entry.getValue());
        //     count++;
        // }

        // String writePath = args[2];
        // writeFile(writePath, allWords);
    }

    // // To write out the allWords file --> for manual checking of Word Distribution accuracy
    // public static void writeFile(String writePath, List<String> words) throws IOException {
    //     FileWriter fw = new FileWriter(writePath);
    //     BufferedWriter bw = new BufferedWriter(fw);

    //     for (String word : words) {
    //         bw.write(word);
    //         bw.newLine();
    //     }

    //     bw.close();
    // }
    
}
