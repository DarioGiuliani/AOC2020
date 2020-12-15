package solutions;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import util.FileReader;

public class SolutionDay15 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day15.txt");

        long startTime = System.currentTimeMillis();

        String[] stringInputs = input.get(0).split(",");
        int[] inputNumbers = new int[stringInputs.length];
        for(int i = 0; i < stringInputs.length; i++) {
            inputNumbers[i] = Integer.parseInt(stringInputs[i]);
        }

        long resultPartOne = calculatePartOne(inputNumbers);
        System.out.println(resultPartOne);

        long afterPartOne = System.currentTimeMillis();
        System.out.println("Execution time: " +  (afterPartOne - startTime) + "ms");

        long resultPartTwo = calculatePartTwo(inputNumbers);
        System.out.println(resultPartTwo);

        System.out.println("Execution time: " +  (System.currentTimeMillis() - afterPartOne) + "ms");
    }

    private static long calculatePartOne(int[] input) {
        return calcNthNumber(input, 2020);
    }

    private static long calculatePartTwo(int[] input) {
        return calcNthNumber(input, 30000000);
    }

    private static long calcNthNumber(int[] input, long nthNumber) {
        Map<Long, Long> lastOccurences = new HashMap<>();
        long turn = 0;
        long currentNumber = 0;
        long nextNumber = 0;

        while (turn < nthNumber) {
            currentNumber = nextNumber;

            if(turn < input.length) {
                currentNumber = input[(int)turn];
            }

            turn++;

            if(!lastOccurences.containsKey(currentNumber)) {
                nextNumber = 0;
            } else {
                nextNumber = turn - lastOccurences.get(currentNumber);
            }

            lastOccurences.put(currentNumber, turn);
        }

        return currentNumber;
    }
}
