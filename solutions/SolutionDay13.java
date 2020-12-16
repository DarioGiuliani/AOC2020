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

        long time = Long.parseLong(busInputList.get(0));
        long step = Long.parseLong(busInputList.get(0));

        for (String busId : busInputList) {
            if(!busId.equals("x")) {
                while(true) {
                    if((time + busInputList.indexOf(busId)) % Integer.parseInt(busId) == 0) {
                        break;
                    }
                    time += step;
                }

                step = lcm(step, Integer.parseInt(busId));
            }
        }


        return time;
    }

    private static long lcm(long number1, int number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }

        long absNumber1 = Math.abs(number1);
        int absNumber2 = Math.abs(number2);
        long absHigherNumber = Math.max(absNumber1, absNumber2);
        long absLowerNumber = Math.min(absNumber1, absNumber2);
        long lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }
}
