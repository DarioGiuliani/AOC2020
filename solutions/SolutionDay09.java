package solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import classes.XMASProtocol;
import util.FileReader;

public class SolutionDay09 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day09.txt");

        long resultPartOne = calculatePartOne(input);
        System.out.println(resultPartOne);

        long resultPartTwo = calculatePartTwo(input, resultPartOne);
        System.out.println(resultPartTwo);
    }

    private static long calculatePartOne(List<String> input) {
        XMASProtocol protocol;
        int preambleSize = 25;
        List<Long> cypherList = input.subList(preambleSize, input.size()).stream().map(string -> Long.parseLong(string)).collect(Collectors.toList());

        for (int i = 0; i < cypherList.size(); i++) {
            protocol = convertToXMASProtocol(input, preambleSize, cypherList.get(i), i);

            if(!protocol.isValid()) {
                return protocol.getCypher();
            }
        }

        return -1;
    }

    private static long calculatePartTwo(List<String> input, long invalidNumber) {
        List<Long> weaknessRange = findWeaknessRange(input.stream().map(string -> Long.parseLong(string)).collect(Collectors.toList()), invalidNumber);

        return Collections.max(weaknessRange) + Collections.min(weaknessRange);
    }

    private static XMASProtocol convertToXMASProtocol(List<String> input, int preambleSize, long cypher, int index) {
        XMASProtocol protocol = new XMASProtocol();
        protocol.setPreamble(input.subList(index, preambleSize + index).stream().map(string -> Long.parseLong(string)).collect(Collectors.toList()));
        protocol.setCypher(cypher);
        return protocol;
    }

    private static List<Long> findWeaknessRange(List<Long> input, long invalidNumber) {
        List<Long> range;

        for (int i = 0; i < input.size(); i++) {
            for(int j = input.size() - 1; j > i; j--) {
                range = input.subList(i, j);
                if(range.stream().mapToLong(l -> l).sum() == invalidNumber) {
                    return range;
                }
            }
        }

        return new ArrayList<>();
    }
}
