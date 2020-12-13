package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Map.Entry;

import util.FileReader;

public class SolutionDay13 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day13.txt");

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
        int time = Integer.parseInt(input.get(0));
        List<Integer> busIds = Arrays.asList(input.get(1).split(",")).stream().filter(string -> !string.contains("x")).map(string -> Integer.parseInt(string)).collect(Collectors.toList());
        Map<Integer, Integer> busTimesAfterArrival = new HashMap<>();

        for (Integer busId : busIds) {
            int departTime = 0;
            while (departTime < time) {
                departTime += busId;
            }

            busTimesAfterArrival.put(busId, departTime);
        }

        Entry<Integer, Integer> closestTimeEntry = null; 
        for (Entry<Integer, Integer> entry : busTimesAfterArrival.entrySet()) {
            if(closestTimeEntry == null || entry.getValue() < closestTimeEntry.getValue()) {
                closestTimeEntry = entry;
            }
        }

        return closestTimeEntry.getKey() * (closestTimeEntry.getValue() - time);
    }

    private static int calculatePartTwo(List<String> input) {
        return 0;
    }
}
