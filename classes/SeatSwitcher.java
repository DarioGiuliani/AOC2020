package classes;

import java.util.ArrayList;
import java.util.List;

public class SeatSwitcher {
    List<String> switchedSeats;
    List<String> inputSeats;
    int amountOfSeatsSwitched;
    int amountofSeatsTaken;

    public SeatSwitcher(List<String> inputSeats) {
        this.inputSeats = inputSeats;
        this.switchedSeats = new ArrayList<>(this.inputSeats);
    }

    public void switchSeats(boolean visible) {
        for(int i = 0; i < this.inputSeats.size(); i++) {
            for (int j = 0; j < this.inputSeats.get(i).length(); j++) {
                List<Character> seats = visible ? getVisibleSeats(i, j) : getAdjacentSeats(i, j);
                switch(this.inputSeats.get(i).charAt(j)) {
                    case '#':
                        if(visible) {
                            replaceOccupied(seats, i, j, 5);
                        } else {
                            replaceOccupied(seats, i, j, 4);
                        } 
                        break;
                    case 'L':
                        replaceEmpty(seats, i, j);
                        break;
                }
            }
        }
    }

    private List<Character> getAdjacentSeats(int row, int column) {
        List<Character> seats = new ArrayList<>();

        for(int i = row - 1; i <= row + 1; i++) {
            for(int j = column - 1; j <= column + 1; j++) {
                if((i >= 0 && i < this.inputSeats.size()) && (j >= 0 && j < this.inputSeats.get(i).length())) {
                    if(!(i == row && j == column)) {
                        seats.add(inputSeats.get(i).charAt(j));
                    }
                }
            }
        }
        return seats;
    }

    private List<Character> getVisibleSeats(int i, int j) {
        List<Character> visibleSeats = new ArrayList<>();
        visibleSeats.add(getVisibleSeat(i, j, -1, -1));
        visibleSeats.add(getVisibleSeat(i, j, -1, 1));
        visibleSeats.add(getVisibleSeat(i, j, 1, -1));
        visibleSeats.add(getVisibleSeat(i, j, 1, 1));
        visibleSeats.add(getVisibleSeat(i, j, 0, -1));
        visibleSeats.add(getVisibleSeat(i, j, 0, 1));
        visibleSeats.add(getVisibleSeat(i, j, -1, 0));
        visibleSeats.add(getVisibleSeat(i, j, 1, 0));

        return visibleSeats;
    }

    private char getVisibleSeat(int row, int column, int xChange, int yChange) {
        int startRow = row + xChange;
        int startColumn = column + yChange;

        while((startRow > -1 && startRow < this.inputSeats.size()) && (startColumn > -1 && startColumn < this.inputSeats.get(startRow).length())) {
            if(this.inputSeats.get(startRow).charAt(startColumn) != '.') {
                return this.inputSeats.get(startRow).charAt(startColumn);
            }

            startRow += xChange;
            startColumn += yChange;
        }

        return 'E';
    }

    private void replaceEmpty(List<Character> adjacentSeats, int row, int column) {
        if(!adjacentSeats.contains('#')) {
            StringBuilder builder = new StringBuilder(switchedSeats.get(row));
            builder.setCharAt(column, '#');
            switchedSeats.set(row, builder.toString());
            this.amountOfSeatsSwitched++;
            this.amountofSeatsTaken++;
        }
    }

    private void replaceOccupied(List<Character> adjacentSeats, int row, int column, int amount) {
        if(adjacentSeats.stream().filter(c -> c == '#').count() >= amount) {
            StringBuilder builder = new StringBuilder(switchedSeats.get(row));
            builder.setCharAt(column, 'L');
            switchedSeats.set(row, builder.toString());
            this.amountOfSeatsSwitched++;
            this.amountofSeatsTaken--;   
        }
    }

    // getters and setters

    public void setSwitchedSeats(List<String> switchedSeats) {
        this.switchedSeats = switchedSeats;
    }

    public List<String> getSwitchedSeats() {
        return this.switchedSeats;
    }

    public void setAmountOfSeatsSwitched(int amountOfSeatsSwitched) {
        this.amountOfSeatsSwitched = amountOfSeatsSwitched;
    }

    public int getAmountOfSeatsSwitched() {
        return this.amountOfSeatsSwitched;
    }

    public int getAmountOfSeatsTaken() {
        return this.amountofSeatsTaken;
    }
}