package solutions;

import java.util.List;
import java.util.ArrayList;

import util.FileReader;

public class SolutionDay15 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day15.txt");

        String[] stringInputs = input.get(0).split(",");
        int[] inputNumbers = new int[stringInputs.length];
        for(int i = 0; i < stringInputs.length; i++) {
            inputNumbers[i] = Integer.parseInt(stringInputs[i]);
        }

        long startTime = System.currentTimeMillis();

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

    private static long calcNthNumber(int[] input, int nthNumber) {
        int turn = 0;
        int currentNumber = 0;
        int nextNumber = 0;

        int[] lastOccurences = new int[nthNumber];
        for(int i = 0; i < input.length; i++) {
            turn++;
            lastOccurences[input[i]] = turn;
            nextNumber = input[i];
        }

        for (int i = turn; i < nthNumber; i++) {
            currentNumber = nextNumber;
            nextNumber = lastOccurences[currentNumber] == 0 ? 0 : i - lastOccurences[currentNumber];
            lastOccurences[currentNumber] = i;
        }

        return nextNumber;
    }
}
