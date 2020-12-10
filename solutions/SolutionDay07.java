package solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import classes.Bag;
import util.ContainIdentifier;
import util.FileReader;

public class SolutionDay07 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day07.txt");

        long startTime = System.currentTimeMillis();

        int resultPartOne = calculatePartOne(input);
        System.out.println(resultPartOne);

        long afterPartOne = System.currentTimeMillis();
        System.out.println("Execution time: " +  (afterPartOne - startTime) + "ms");

        int resultPartTwo = calculatePartTwo(input);
        System.out.println(resultPartTwo);

        System.out.println("Execution time: " +  (System.currentTimeMillis() - afterPartOne) + "ms");
    }

    private static int calculatePartOne(List<String> input) {
        Set<String> allColours = new HashSet<>();
        Set<String> nextColours = new HashSet<>();
        Set<String> previousColours = new HashSet<>();

        input.stream().filter(string -> string.split("contain")[1].contains("shiny gold")).forEachOrdered(string -> nextColours.add(string.split(" ")[0] + " " + string.split(" ")[1]));
        allColours.addAll(nextColours);

        while (nextColours.size() > 0) {
            previousColours.clear();
            previousColours.addAll(nextColours);
            nextColours.clear();
            input.stream().filter(string -> ContainIdentifier.containsOneIdentifier(string.split("contain")[1], previousColours)).forEachOrdered(string -> nextColours.add(string.split(" ")[0] + " " + string.split(" ")[1]));
            allColours.addAll(nextColours);
        }

        return allColours.size();
    }

    private static int calculatePartTwo(List<String> input) {
        final HashMap<String, Bag> allBags = new HashMap<>();
        for (String string : input) {
            final String[] splitString = string.split(" bags contain ");
            final String[] values = splitString[1].substring(0, splitString[1].length() - 1).split(", ");
            if (!allBags.containsKey(splitString[0])) {
                allBags.put(splitString[0], new Bag(splitString[0]));
            }

            for (String value : values) {
                if (!value.equals("no other bags")) {
                    final int count = Integer.parseInt(value.substring(0, value.indexOf(" ")));
                    final String name = value.substring(value.indexOf(" ") + 1, value.lastIndexOf(" "));
                    if (!allBags.containsKey(name)) {
                        allBags.put(name, new Bag(name));
                    }

                    allBags.get(splitString[0]).addBags(allBags.get(name), count);
                } 
            }
        }

        return allBags.get("shiny gold").countBags();
    }
}
