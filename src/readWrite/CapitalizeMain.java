// Instructions:
// Read from file using FileReader --> BufferedReader
// Next, convert text file to upper case
// Write into a new file using BufferedWriter --> FileWriter

package readWrite;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class CapitalizeMain {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Load the file
        Path p = Paths.get(args[0]);
        File f = p.toFile();

        // Read the file
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        // Write the file
        FileWriter fw = new FileWriter(args[1]);
        BufferedWriter bw = new BufferedWriter(fw);

        while (true) {
            String line = br.readLine();
            
            if (line == null) {
                break;
            }
            // wants to write everything to UPPER
            bw.write(line.toUpperCase() + "\n");
        }

        // -- Another way of running the readLine
        // String line;

        // while ((line = br.readLine()) != null) {
        //     // wants to write everything to UPPER
        //     bw.write(line.toUpperCase() + "\n");
        // }

        // Close the reader - avoid resource leak
        br.close();
        bw.close();
        
    }
}
