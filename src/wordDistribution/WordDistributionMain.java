package wordDistribution;

import java.io.FileReader;
import java.util.Set;
import java.io.BufferedReader;


public class WordDistributionMain {

    public static void main(String[] args) {
        
        String inputFile = args[0];
        String stopWordFile = args[1];

        StopWord sw = new StopWord(stopWordFile);
        Set<String> stopList = sw.getStopWordList();




    }
    
}
