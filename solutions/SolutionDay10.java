package solutions;

import util.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SolutionDay10 {
    static Map<Integer, Long> resultSet = new HashMap<>();
    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>();
        input = FileReader.readFile("input/day10.txt").stream().map(string -> Integer.parseInt(string)).sorted().collect(Collectors.toList());

        long startTime = System.currentTimeMillis();
        int resultPartOne = calculatePartOne(input);
        System.out.println(resultPartOne);

        long afterPartOne = System.currentTimeMillis();
        System.out.println("Execution time: " +  (afterPartOne - startTime) + "ms");

        LinkedList<Integer> partTwoInput = new LinkedList<>();
        partTwoInput.addAll(input);
        partTwoInput.addFirst(0);
        partTwoInput.addLast(input.get(input.size() - 1) + 3);
        
        long resultPartTwo = calculatePartTwo(partTwoInput);
        System.out.println(resultPartTwo);

        long afterPartTwo = System.currentTimeMillis();
        System.out.println("Execution time: " + (afterPartTwo - afterPartOne) + "ms");
    }

    private static int calculatePartOne(List<Integer> input) {
        int oneJoltDifferences = 1;
        int threeJoltDifferences = 1;
        int index = 0;
        int difference = 0;

        while(difference <= 3 && index < input.size() - 1) {
            difference = input.get(index + 1) - input.get(index);

            if(difference == 1) {
                oneJoltDifferences++;
            }

            if(difference == 3) {
                threeJoltDifferences++;
            }

            index++;
        }

        return oneJoltDifferences * threeJoltDifferences;
    }

    private static long calculatePartTwo(LinkedList<Integer> input) {
        if(resultSet.containsKey(input.size())) {
            return resultSet.get(input.size());
        }

        if(input.size() == 1) {
            return 1;
        }

        long total = 0;
        long currentNumber = input.get(0);

        for (int i = 1; i < input.size(); i++) {
            if (input.get(i) - currentNumber <= 3) {
                LinkedList<Integer> nextPath = new LinkedList<>();
                nextPath.addAll(input.subList(i, input.size()));
                total += calculatePartTwo(nextPath);
            }
        }

        resultSet.put(input.size(), total);

        return total;
    }
}
