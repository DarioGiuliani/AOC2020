package solutions;

import java.util.ArrayList;
import java.util.List;
import util.FileReader;

public class SolutionDay12 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = FileReader.readFile("input/day12.txt");

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
        int x = 0;
        int y = 0;
        int direction = 90;
        
        for (String string : input) {
            char identifier = string.charAt(0);
            int value = Integer.parseInt(string.substring(1));

            switch(identifier) {
                case 'N':
                    y += value;
                    break;
                case 'S':
                    y -= value;
                    break;
                case 'E':
                    x += value;
                    break;
                case 'W':
                    x -= value;
                    break;
                case 'L':
                    direction = Math.floorMod(direction - value, 360);
                    break;
                case 'R':
                    direction = Math.floorMod((direction + value), 360);
                    break;
                case 'F':
                    switch(direction) {
                        case 0:
                            y += value;
                            break;
                        case 90:
                            x += value;
                            break;
                        case 180:
                            y -= value;
                            break;
                        case 270:
                            x -= value;
                            break;
                    }
                    break;
            }
        }

        return Math.abs(x) + Math.abs(y);
    }

    private static int calculatePartTwo(List<String> input) {
        int waypointX = 10;
        int wayPointY = 1;
        int rotation = 0;
        int shipX = 0;
        int shipY = 0;

        for (String string : input) {
            char identifier = string.charAt(0);
            int value = Integer.parseInt(string.substring(1));

            switch(identifier) {
                case 'N':
                    switch(rotation) {
                        case 0:
                            wayPointY += value;
                            break;
                        case 90:
                            waypointX -= value;
                            break;
                        case 180:
                            wayPointY -= value;
                            break;
                        case 270:
                            waypointX += value;
                            break;
                    }
                    break;
                case 'S':
                    switch(rotation) {
                        case 0:
                            wayPointY -= value;
                            break;
                        case 90:
                            waypointX += value;
                            break;
                        case 180:
                            wayPointY += value;
                            break;
                        case 270:
                            waypointX -= value;
                            break;
                    }
                    break;
                case 'E':
                    switch(rotation) {
                        case 0:
                            waypointX += value;
                            break;
                        case 90:
                            wayPointY += value;
                            break;
                        case 180:
                            waypointX -= value;
                            break;
                        case 270:
                            wayPointY -= value;
                            break;
                    }
                    break;
                case 'W':
                    switch(rotation) {
                        case 0:
                            waypointX -= value;
                            break;
                        case 90:
                            wayPointY -= value;
                            break;
                        case 180:
                            waypointX += value;
                            break;
                        case 270:
                            wayPointY += value;
                            break;
                    }
                    break;
                case 'F':
                    switch(rotation) {
                        case 0:
                            shipX += waypointX * value;
                            shipY += wayPointY * value;
                            break;
                        case 90:
                            shipX += wayPointY * value;
                            shipY += waypointX * -value;
                            break;
                        case 180:
                            shipX += waypointX * -value;
                            shipY += wayPointY * -value;
                            break;
                        case 270:
                            shipX += wayPointY * -value;
                            shipY += waypointX * value;
                            break;
                    }
                    break;
                case 'R':
                    rotation += value;
                    if(rotation > 270) rotation -= 360;
                    break;
                case 'L':
                    rotation -= value;
                    if(rotation < 0) rotation += 360;
                    break;
            }
        }

        return Math.abs(shipX) + Math.abs(shipY);
    }
}
