package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

        long resultPartTwo = calculatePartTwo(input);
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

    private static long calculatePartTwo(List<String> input) {
        List<String> busInputList = Arrays.asList(input.get(1).split(","));
        boolean found = false;
        int loops = 1;
        long earliestTimestamp = 0;

        while(!found) {
            long originTime = 0;
            Map<Integer, Long> times = new LinkedHashMap<>();
            for(int i = 0; i < busInputList.size(); i++) {
                long busTime = 0;
                
                if(!busInputList.get(i).equals("x")) {
                    for(int j = 0; j < loops; j++) {
                        busTime += Long.parseLong(busInputList.get(i));
                    }
                } else {
                    busTime = originTime + i;
                }

                
                times.put(i, busTime);
                if(i == 0) originTime = busTime;
            }

            if(check(times)) {
                found = true;
                earliestTimestamp = originTime;
            } 

            loops++;
        }

        return earliestTimestamp;
    }

    private static boolean check(Map<Integer, Long> times) {
        for(int i = 0; i < times.size(); i++) {
            if(times.get(i) != times.get(0) + i) {
                return false;
            }
        }

        return true;
    }
}
