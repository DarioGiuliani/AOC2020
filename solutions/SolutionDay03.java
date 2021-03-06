package solutions;

import java.util.ArrayList;
import java.util.List;
import util.FileReader;

public class SolutionDay03 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day03.txt");

        long startTime = System.currentTimeMillis();

        Long resultPartOne = calculatePartOne(input, 3, 1);
        System.out.println(resultPartOne);

        long afterPartOne = System.currentTimeMillis();
        System.out.println("Execution time: " +  (afterPartOne - startTime) + "ms");

        Long resultPartTwo = calculatePartTwo(input);
        System.out.println(resultPartTwo);

        System.out.println("Execution time: " +  (System.currentTimeMillis() - afterPartOne) + "ms");
    }

    private static Long calculatePartOne(List<String> input, int xOffset, int yOffset) {
        Long x = Long.valueOf(0);
        Long y = Long.valueOf(0);
        Long trees = Long.valueOf(0);

        while(y < input.size() - 1) {
            x = x >= input.get(y.intValue()).length() ? x + xOffset : (x + xOffset) % input.get(y.intValue()).length();
            y += yOffset;

            if(input.get(y.intValue()).charAt(x.intValue()) == '#') {
                trees++;
            }
        }
        return trees;
    }

    private static Long calculatePartTwo(List<String> input) {
        Long firstSlope = calculatePartOne(input, 1, 1);
        Long secondSlope = calculatePartOne(input, 3, 1);
        Long thirdSlope = calculatePartOne(input, 5, 1);
        Long fourthSlope = calculatePartOne(input, 7, 1);
        Long fifthSlope = calculatePartOne(input, 1, 2);

        return firstSlope * secondSlope * thirdSlope * fourthSlope * fifthSlope;
    }
}
