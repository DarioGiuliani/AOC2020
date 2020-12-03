package util;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner;

public class FileReader {
    public static List<String> readFile(String fileName) {
        List<String> input = new ArrayList<>();

        try {
            File inputFile = new File(fileName);
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
              input.add(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 

        return input;
    }
}