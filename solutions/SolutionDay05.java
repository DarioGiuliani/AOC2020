package solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.FileReader;

public class SolutionDay05 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day05.txt");

        List<Double> resultList = calculatePartOne(input);
        System.out.println(Collections.max(resultList));

        Double result = calculatePartTwo(resultList);
        System.out.println(result);
    }

    private static List<Double> calculatePartOne(List<String> input) {
        char[] rowIdentifiers = {'F', 'B'};
        char[] columnIdentifiers = {'L', 'R'};
        List<Double> seatIds = new ArrayList<>();

        for (String sequenceString : input) {
            Double row = calculateByBoundsAndIdentifiers(0.0, 127.0, rowIdentifiers, sequenceString.substring(0,7));
            Double column = calculateByBoundsAndIdentifiers(0.0, 7.0, columnIdentifiers, sequenceString.substring(7));
            seatIds.add(row * 8 + column);
        }

        return seatIds;
    }

    private static Double calculatePartTwo(List<Double> resultList) {
        for(double d = Collections.min(resultList); d < Collections.max(resultList); d++) {
            if(!resultList.contains(d)) {
                return d;
            }
        }

        return -1.0;
    }

    private static Double calculateByBoundsAndIdentifiers(Double lower, Double upper, char[] identifiers, String sequence) {
        char[] sequenceChars = sequence.toCharArray();

        for (int i = 0; i < sequenceChars.length - 1; i++) {
            if(sequenceChars[i] == identifiers[0]) {
                upper = Math.floor(getAverage(lower, upper));
            } else {
                lower = Math.ceil(getAverage(lower, upper));
            }
        }

        return sequenceChars[sequenceChars.length - 1] == identifiers[0] ? lower : upper;
    }

    private static Double getAverage(Double x, Double y) {
        return (x + y) / 2;
    }
}
