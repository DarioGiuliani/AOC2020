package solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import util.FileReader;

public class SolutionDay06 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day06.txt");

        int resultPartOne = calculatePartOne(input);
        System.out.println(resultPartOne);

        int resultPartTwo = calculatePartTwo(input);
        System.out.println(resultPartTwo);
    }

    private static int calculatePartOne(List<String> input) {
        Set<Character> answerChars = new HashSet<>();
        int amountOfAnswersCombined = 0;

        for (String answerString : input) {
            if(answerString.isEmpty()) {
                amountOfAnswersCombined += answerChars.size();
                answerChars.clear();
            }
            
            for (Character character : answerString.toCharArray()) {
                answerChars.add(character);
            }
        }

        return amountOfAnswersCombined + answerChars.size();
    }

    private static int calculatePartTwo(List<String> input) {
        Map<Character, Integer> answerMap = new HashMap<>();
        int amountOfAnswersCombined = 0;
        int personsInGroupCounter = 0;

        for (String answerString : input) {
            if(!answerString.isEmpty()) {
                personsInGroupCounter++;
            }

            if(answerString.isEmpty()) {
                int occurences = amountOfAnswersEqualToAmountOfPersons(answerMap, personsInGroupCounter);
                amountOfAnswersCombined += occurences;
                answerMap.clear();
                personsInGroupCounter = 0;
            }
            
            for (Character character : answerString.toCharArray()) {
                if(answerMap.keySet().contains(character)) {
                    answerMap.put(character, answerMap.get(character) + 1);
                } else {
                    answerMap.put(character, 1);
                }
            }
        }

        return amountOfAnswersCombined + amountOfAnswersEqualToAmountOfPersons(answerMap, personsInGroupCounter);
    }

    private static int amountOfAnswersEqualToAmountOfPersons(Map<Character, Integer> answerMap, int personsInGroupCounter) {
        int amountOfSameAnswersCombined = 0;
        
        for (Integer value : answerMap.values()) {
            if(value == personsInGroupCounter) {
                amountOfSameAnswersCombined++;
            }
        }

        return amountOfSameAnswersCombined;
    }
}
