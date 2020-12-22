package solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import util.FileReader;

public class SolutionDay22 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day22.txt");

        long startTime = System.currentTimeMillis();

        int resultPartOne = calculatePartOne(input);
        System.out.println(resultPartOne);

        long afterPartOne = System.currentTimeMillis();
        System.out.println("Execution time: " +  (afterPartOne - startTime) + "ms");

        int resultPartTwo = calculatePartTwo(input);
        System.out.println(resultPartTwo);

        System.out.println("Execution time: " +  (System.currentTimeMillis() - afterPartOne) + "ms");
    }

    private static int calculatePartOne(List<String> input) {
        List<String> deckOneStrings = splitInput(input);
        List<String> deckTwoStrings = input;

        deckOneStrings.remove(0);
        deckTwoStrings.remove(0);

        List<Integer> deckOne = deckOneStrings.stream().map(string -> Integer.parseInt(string)).collect(Collectors.toList());
        List<Integer> deckTwo = deckTwoStrings.stream().map(string -> Integer.parseInt(string)).collect(Collectors.toList());

        while(deckOne.size() > 0 && deckTwo.size() > 0) {
           int cardOne = deckOne.get(0);
           int cardTwo = deckTwo.get(0);

           deckOne.remove(0);
           deckTwo.remove(0);

           if(cardOne > cardTwo) {
               deckOne.add(cardOne);
               deckOne.add(cardTwo);
           } else {
               deckTwo.add(cardTwo);
               deckTwo.add(cardOne);
           }
        }

        int winningscore = deckOne.size() > 0 ? calculateScore(deckOne) : calculateScore(deckTwo);

        return winningscore;
    }

    private static int calculateScore(List<Integer> deck) {
        int score = 0;
        int multiplier = 1;

        for(int j = deck.size() - 1; j >= 0; j--) {
            score += deck.get(j) * multiplier;
            multiplier++;
        }

        return score;
    }

    private static int calculatePartTwo(List<String> input) {
        return 0;
    }

    private static List<String> splitInput(List<String> input) {
        int i = 0;
        List<String> split = new ArrayList<>();

        while(!input.get(i).isEmpty()) {
            split.add(input.get(i));
            input.remove(i);
        }

        input.remove(i);
        return split;
    }
}
