// Helper class to create a set of stop words

package wordDistribution;

import java.util.HashSet;
import java.util.Set;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class StopWord {

    private String filePath;
    private final Set<String> stopWordList = new HashSet<>();

    public StopWord(String filePath) {
        this.filePath = filePath;

        // Read file
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            while(true) {
                line = br.readLine();
                if(line == null)
                    break;
                stopWordList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) { // checks if the reader is initialized --> if not, then dont need to close
                try {
                    br.close();
                } catch (IOException closException) {
                    closException.printStackTrace(); // Handles any exception during closing
                }
            }
        }


    }

    public String getFilePath() {
        return filePath;
    }

    public Set<String> getStopWordList() {
        return stopWordList;
    }   
    
}
