package solutions;

import java.util.ArrayList;
import java.util.List;
import util.FileReader;

public class SolutionDay02 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day02.txt");

        int resultPartOne = calculatePartOne(input);
        System.out.println(resultPartOne);

        int resultPartTwo = calculatePartTwo(input);
        System.out.println(resultPartTwo);
    }

    private static int calculatePartOne(List<String> input) {
        int count = 0;
        for (String string : input) {
            String[] splitInput = string.split("[\\-: ]+");
            long occurences = splitInput[3].chars().filter(c -> c == splitInput[2].charAt(0)).count();

            if (occurences >= Integer.parseInt(splitInput[0]) && occurences <= Integer.parseInt(splitInput[1])){
                count++;
            }
        }
        return count;
    }

    private static int calculatePartTwo(List<String> input) {
        int count = 0;
        for (String string : input) {
            String[] splitInput = string.split("[\\-: ]+");
            int firstPosition = Integer.parseInt(splitInput[0]) - 1;
            int secondPosition = Integer.parseInt(splitInput[1]) - 1;
            char firstChar = splitInput[3].charAt(firstPosition);
            char secondChar = splitInput[3].charAt(secondPosition);
            char containingChar = splitInput[2].charAt(0);

            if((firstChar != containingChar && secondChar == containingChar) 
                || (firstChar == containingChar && secondChar!= containingChar)) {
                count++;
            }
        }

        return count;
    }
}
