package solutions;

import java.util.ArrayList;
import java.util.List;

import util.FileReader;

public class SolutionDay16 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day16.txt");

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
        int errorRate = 0;
        List<String> rules = splitInput(input);
        String ticket = splitInput(input).get(1);
        List<String> nearbyTickets = new ArrayList<>(input);
        nearbyTickets.remove(0);

        for (String nearbyTicket : nearbyTickets) {
            errorRate += checkForError(nearbyTicket, rules);
        }

        return errorRate;
    }

    private static int checkForError(String ticket, List<String> rules) {
        int ticketErrorRate = 0;
        String[] ticketNumbers = ticket.split(",");
        for (String numberString : ticketNumbers) {
            int number = Integer.parseInt(numberString);
            if(!isInvalid(number, rules).contains(false)) {
                ticketErrorRate += number;
            }
        }
        
        return ticketErrorRate;
    }

    private static List<Boolean> isInvalid(int number, List<String> rules) {
        List<Boolean> validity = new ArrayList<>();
        
        for (String rule : rules) {
            String[] ruleNumbers = rule.split("[\\-:a-z ]+");
            int betweenOneLower = Integer.parseInt(ruleNumbers[1]);
            int betweenOneUpper = Integer.parseInt(ruleNumbers[2]);
            int betweenTwoLower = Integer.parseInt(ruleNumbers[3]);
            int betweenTwoUpper = Integer.parseInt(ruleNumbers[4]);
            if((number >= betweenOneLower && number <= betweenOneUpper) || (number >= betweenTwoLower && number <= betweenTwoUpper)) {
                validity.add(false);
            } else {
                validity.add(true);
            }
        }

        return validity;
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
