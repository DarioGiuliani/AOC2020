package solutions;

import java.util.ArrayList;
import java.util.List;
import util.FileReader;

public class SolutionDay01 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day01.txt");

        long startTime = System.currentTimeMillis();

        int resultPartOne = calculatePartOne(input);
        System.out.println(resultPartOne);

        long afterPartOne = System.currentTimeMillis();
        System.out.println("Execution time: " +  (afterPartOne - startTime) + "ms");

        int resultPartTwo = calculatePartTwo(input);
        System.out.println(resultPartTwo);

        System.out.println("Execution time: " +  (System.currentTimeMillis() - afterPartOne) + "ms");
    }

    public static int calculatePartOne(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            for(int j = input.size() - 1; j > i; j--) {
                int inputi = Integer.parseInt(input.get(i));
                int inputj = Integer.parseInt(input.get(j));
                if(inputi + inputj == 2020) {
                   return inputi * inputj;
                }
            }
        }

        return -1;
    }

    public static int calculatePartTwo(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            for(int j = input.size() - 1; j > i; j--) {
                for(int k = i + 1; k < j; k++) {
                    int inputi = Integer.parseInt(input.get(i));
                    int inputj = Integer.parseInt(input.get(j));
                    int inputk = Integer.parseInt(input.get(k));
                    if(inputi + inputj + inputk == 2020) {
                        return inputi * inputj * inputk;
                    }
                }
            }
        }

        return -1;
    }
}