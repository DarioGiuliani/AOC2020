package solutions;

import java.util.ArrayList;
import java.util.List;

import classes.Interpreter;
import util.FileReader;

public class SolutionDay08 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day08.txt");

        int resultPartOne = calculatePartOne(input);
        System.out.println(resultPartOne);

        int resultPartTwo = calculatePartTwo(input);
        System.out.println(resultPartTwo);
    }

    private static int calculatePartOne(List<String> input) {
        return checkInfinite(input).getAccumulator();
    }

    private static int calculatePartTwo(List<String> input) {
        Interpreter interpreter;

        for (int i = 0; i < input.size(); i++) {
            if(replaceLine(input, i)) {
                interpreter = checkInfinite(input);
                if(!interpreter.isInfinite()) {
                    return interpreter.getAccumulator();
                }

                replaceLine(input, i);
            }
        }

        return -1;
    }

    private static boolean replaceLine(List<String> input, int index) {
        if(input.get(index).contains("jmp")) {
            input.set(index, input.get(index).replace("jmp", "nop"));
            return true;
        } else if (input.get(index).contains("nop")) {
            input.set(index, input.get(index).replace("nop", "jmp"));
            return true;
        }

        return false;
    }

    private static Interpreter checkInfinite(List<String> input) {
        int index = 0;
        Interpreter interpreter = new Interpreter(0, false);
        List<Integer> executedLines = new ArrayList<>();

        while (!executedLines.contains(index) && index < input.size()) {
            executedLines.add(index);
            index = executeLine(input.get(index), index, interpreter);

            if(executedLines.contains(index)) {
                interpreter.setInfinite(true);
            }
        }

        return interpreter;
    }

    private static int executeLine(String line, int index, Interpreter interpreter) {
        String[] splitInput = line.split(" "); 

        switch(splitInput[0]) {
            case "acc":
                interpreter.setAccumulator(interpreter.getAccumulator() + Integer.parseInt(splitInput[1]));
                index++;
                break;
            case "jmp":
                index += Integer.parseInt(splitInput[1]);
                break;
            default:
                index++;
                break;
        }

        return index;
    }
}
