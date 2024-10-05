// Instructions:
// Read from file using FileReader --> BufferedReader
// Next, convert text file to upper case
// Write into a new file using BufferedWriter --> FileWriter

package readWrite;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class CapitalizeMainChuk {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String inputFile = args[0];
        String outputFIle = args[1];

        // Open inputFile for reading
        Reader reader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(reader);

        // Open outputFile for writing
        Writer writer = new FileWriter(outputFIle);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        String line = "x"; // just to make sure line is not empty
        while(null != line) {
            // Read a line
            line = bufferedReader.readLine();

            // If line is null, we have reaced the EOF
            if (null == line) {
                break;
            }
            bufferedWriter.write(line.toUpperCase() + "\n");
        }

        bufferedReader.close();

        bufferedWriter.flush(); // Force everything from write buffer to the file
        bufferedWriter.close();
        
    }
}
