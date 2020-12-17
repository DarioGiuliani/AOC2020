package solutions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import util.FileReader;

public class SolutionDay14 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day14.txt");

        long startTime = System.currentTimeMillis();

        long resultPartOne = calculatePartOne(input);
        System.out.println(resultPartOne);

        long afterPartOne = System.currentTimeMillis();
        System.out.println("Execution time: " +  (afterPartOne - startTime) + "ms");

        long resultPartTwo = calculatePartTwo(input);
        System.out.println(resultPartTwo);

        System.out.println("Execution time: " +  (System.currentTimeMillis() - afterPartOne) + "ms");
    }

    private static long calculatePartOne(List<String> input) {
        String mask = "";
        long[] memory = new long[100000];

        for (String inputString : input) {
            if(inputString.contains("mask")) {
                mask = inputString.split(" = ")[1];
            } else {
                String[] splitString = inputString.split(" = ");
                Integer memoryId = Integer.parseInt(splitString[0].substring(4, splitString[0].length() - 1));
                Integer current = Integer.parseInt(splitString[1]);
                String binary = String.format("%036d", new BigInteger(Integer.toBinaryString(current)));
                StringBuilder builder = new StringBuilder(binary);
                for (int i = 0; i < binary.length(); i++) {
                    switch(mask.charAt(i)) {
                        case '0':
                            builder.setCharAt(i, '0');
                            break;
                        case '1':
                            builder.setCharAt(i, '1');
                            break;
                    }
                }

                memory[memoryId] = Long.parseLong(builder.toString(), 2);
            }
        }

        return LongStream.of(memory).sum();
    }

    private static long calculatePartTwo(List<String> input) {
        return  0;
    }
}
