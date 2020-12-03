package solutions;

import java.util.ArrayList;
import java.util.List;
import util.FileReader;

public class SolutionDay03 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day03.txt");

        Long resultPartOne = calculatePartOne(input, 3, 1);
        System.out.println(resultPartOne);

        Long resultPartTwo = calculatePartTwo(input);
        System.out.println(resultPartTwo);
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
        System.out.println("first: " + firstSlope);
        Long secondSlope = calculatePartOne(input, 3, 1);
        System.out.println("second: " + secondSlope);
        Long thirdSlope = calculatePartOne(input, 5, 1);
        System.out.println("third: " + thirdSlope);
        Long fourthSlope = calculatePartOne(input, 7, 1);
        System.out.println("fourth: " + fourthSlope);
        Long fifthSlope = calculatePartOne(input, 1, 2);
        System.out.println("fifth: " + fifthSlope);

        return firstSlope * secondSlope * thirdSlope * fourthSlope * fifthSlope;
    }
}
