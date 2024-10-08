package wordDistribution;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;

public class ReadFile {

    private String filePath;

    public ReadFile(String fp) {
        this.filePath = fp;
    }

    public String getFilePath() {
        return this.filePath;
    }


    // Method to read File --> returns List of lines
    public List<String> readFile() {
        
        List<String> lines = new ArrayList<>();

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(this.filePath));

            String line;
            while(true) {
                line = br.readLine();
                if (line != null) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException closException) {
                    closException.printStackTrace();
                }
            }
        }

        return lines;
    }
    
}
