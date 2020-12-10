package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.Appender;
import util.ContainIdentifier;
import util.FileReader;

public class SolutionDay04 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day04.txt");
        List<String> appendedInputs = Appender.appendInputs(input, " ");
        List<String> fields = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

        long startTime = System.currentTimeMillis();

        int validsPartOne = calculatePartOne(appendedInputs, fields);
        System.out.println(validsPartOne);

        long afterPartOne = System.currentTimeMillis();
        System.out.println("Execution time: " +  (afterPartOne - startTime) + "ms");

        int validsPartTwo = calculatePartTwo(appendedInputs, fields);
        System.out.println(validsPartTwo);

        System.out.println("Execution time: " +  (System.currentTimeMillis() - afterPartOne) + "ms");
    }

    private static int calculatePartOne(List<String> input, List<String> fields) {
        int count = 0;

        for (String string : input) {
            if(ContainIdentifier.containsAllIdentifiers(string, fields)) {
                count++;
            }
        }

        return count;
    }

    private static int calculatePartTwo(List<String> input, List<String> fields) {
        int valids = 0;
        List<Boolean> validity;
        
        for (String string : input) {
            validity = new ArrayList<>();
            if(ContainIdentifier.containsAllIdentifiers(string, fields)) {
                String[] splitString = string.split(" ");
                for (String part : splitString) {
                    String[] splitPart = part.split(":");
                    validity.add(checkValidPartTwo(splitPart[0], splitPart[1]));
                }

                if(!validity.contains(false)) {
                    valids++;
                }
            }
        }

        return valids;
    }

    private static boolean checkValidPartTwo(String identifier, String value) {
        List<String> eclValues = Arrays.asList("amb", "blu", "brn", "gry", "grn","hzl", "oth");
        List<String> hgtValues = Arrays.asList("in", "cm");

        switch(identifier) {
            case "byr":
                return value.length() == 4 && (Integer.parseInt(value) >= 1920 && Integer.parseInt(value) <= 2002);
            case "iyr":
                return value.length() == 4 && (Integer.parseInt(value) >= 2010 && Integer.parseInt(value) <= 2020);
            case "eyr":
                return value.length() == 4 && (Integer.parseInt(value) >= 2020 && Integer.parseInt(value) <= 2030);
            case "hgt":
                return ContainIdentifier.containsOneIdentifier(value, hgtValues) && checkHeight(value);
            case "hcl":
                return value.contains("#") && value.substring(1).length() == 6 && !value.matches("[g-zA-Z]+");
            case "ecl":
                return ContainIdentifier.containsOneIdentifier(value, eclValues);
            case "pid":
                return value.length() == 9 ? true : false;
            default:
                return true;
        }
    }

    private static boolean checkHeight(String value) {
        int parsedValue = Integer.parseInt(value.split("[A-Za-z]+")[0]);
        if(value.contains("cm")) {
            return parsedValue >= 150 && parsedValue <= 193;
        }

        return parsedValue >= 59 && parsedValue <= 76;
    }
}
