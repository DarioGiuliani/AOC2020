package solutions;

import java.util.ArrayList;
import java.util.List;

import classes.SeatSwitcher;
import util.FileReader;

public class SolutionDay11 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day11.txt");

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
        SeatSwitcher seatSwitcher = new SeatSwitcher(input);
        seatSwitcher.setAmountOfSeatsSwitched(1);
        int occupiedSeats = 0;

        while (seatSwitcher.getAmountOfSeatsSwitched() > 0) {
            seatSwitcher.setAmountOfSeatsSwitched(0);
            occupiedSeats += seatSwitcher.getAmountOfSeatsTaken();
            seatSwitcher = new SeatSwitcher(seatSwitcher.getSwitchedSeats());
            seatSwitcher.switchSeats();
        }

        return occupiedSeats;
    }

    private static int calculatePartTwo(List<String> input) {
        SeatSwitcher seatSwitcher = new SeatSwitcher(input);
        seatSwitcher.setAmountOfSeatsSwitched(1);
        int occupiedSeats = 0;

        while (seatSwitcher.getAmountOfSeatsSwitched() > 0) {
            seatSwitcher.setAmountOfSeatsSwitched(0);
            occupiedSeats += seatSwitcher.getAmountOfSeatsTaken();
            seatSwitcher = new SeatSwitcher(seatSwitcher.getSwitchedSeats());
            seatSwitcher.switchSeatsVisible();
        }

        return occupiedSeats;
    }
}
