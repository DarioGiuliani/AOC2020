package solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.FileReader;

public class SolutionDay05 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day05.txt");

        long startTime = System.currentTimeMillis();

        List<Integer> resultList = calculatePartOne(input);
        System.out.println(Collections.max(resultList));

        long afterPartOne = System.currentTimeMillis();
        System.out.println("Execution time: " +  (afterPartOne - startTime) + "ms");

        int result = calculatePartTwo(resultList);
        System.out.println(result);

        System.out.println("Execution time: " +  (System.currentTimeMillis() - afterPartOne) + "ms");
    }

    private static List<Integer> calculatePartOne(List<String> input) {
        char[] rowIdentifiers = {'F', 'B'};
        char[] columnIdentifiers = {'L', 'R'};
        List<Integer> seatIds = new ArrayList<>();

        for (String sequenceString : input) {
            int row = calculateByBoundsAndIdentifiers(0, 127, rowIdentifiers, sequenceString.substring(0,7));
            int column = calculateByBoundsAndIdentifiers(0, 7, columnIdentifiers, sequenceString.substring(7));
            seatIds.add(row * 8 + column);
        }

        return seatIds;
    }

    private static int calculatePartTwo(List<Integer> resultList) {
        for(int i = Collections.min(resultList); i < Collections.max(resultList); i++) {
            if(!resultList.contains(i)) {
                return i;
            }
        }

        return -1;
    }

    private static int calculateByBoundsAndIdentifiers(int lower, int upper, char[] identifiers, String sequence) {
        char[] sequenceChars = sequence.toCharArray();

        for (int i = 0; i < sequenceChars.length - 1; i++) {
            if(sequenceChars[i] == identifiers[0]) {
                upper = (int)Math.floor(getAverage((double)lower, (double)upper));
            } else {
                lower = (int)Math.ceil(getAverage((double)lower, (double)upper));
            }
        }

        return sequenceChars[sequenceChars.length - 1] == identifiers[0] ? lower : upper;
    }

    private static double getAverage(double x, double y) {
        return (x + y) / 2;
    }
}
