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

    public void switchSeats() {
        for(int i = 0; i < this.inputSeats.size(); i++) {
            for (int j = 0; j < this.inputSeats.get(i).length(); j++) {
                List<Character> adjacentSeats = getAdjacentSeats(i, j);
                switch(this.inputSeats.get(i).charAt(j)) {
                    case '#':
                        replaceOccupied(adjacentSeats, i, j, 4);
                        break;
                    case 'L':
                        replaceEmpty(adjacentSeats, i, j);
                        break;
                }
            }
        }
    }

    private List<Character> getAdjacentSeats(int row, int column) {
        int leftBound = column - 1;
        int rightBound = column + 1;
        int upperBound = row - 1;
        int lowerBound = row + 1;
        List<Character> seats = new ArrayList<>();

        for(int i = upperBound; i <= lowerBound; i++) {
            for(int j = leftBound; j <= rightBound; j++) {
                if((i >= 0 && i < this.inputSeats.size()) && (j >= 0 && j < this.inputSeats.get(i).length())) {
                    if(!(i == row && j == column)) {
                        seats.add(inputSeats.get(i).charAt(j));
                    }
                }
            }
        }
        return seats;
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

    public void switchSeatsVisible() {
        for(int i = 0; i < this.inputSeats.size(); i++) {
            for (int j = 0; j < this.inputSeats.get(i).length(); j++) {
                List<Character> VisibleSeats = getVisibleSeats(i, j);
                switch(this.inputSeats.get(i).charAt(j)) {
                    case '#':
                        replaceOccupied(VisibleSeats, i, j, 5);
                        break;
                    case 'L':
                        replaceEmpty(VisibleSeats, i, j);
                        break;
                }
            }
        }
    }

    private List<Character> getVisibleSeats(int i, int j) {
        List<Character> visibleSeats = new ArrayList<>();
        visibleSeats.add(getVisibleUpperLeftDiagonal(i, j));
        visibleSeats.add(getVisibleUpperRightDiagonal(i,j));
        visibleSeats.add(getVisibleLowerLeftDiagonal(i,j));
        visibleSeats.add(getVivisbleLowerRightDiagonal(i,j));
        visibleSeats.add(getVisibleLeftHorizon(i,j));
        visibleSeats.add(getVisibleRightHorizon(i,j));
        visibleSeats.add(getVisibleUpperVertical(i,j));
        visibleSeats.add(getVisibleLowerVertical(i,j));

        return visibleSeats;
    }

    private Character getVisibleLowerVertical(int row, int column) {
        for(int i = row + 1; i < inputSeats.size(); i++) {
            if(checkSeat(i, column)) {
                return this.inputSeats.get(i).charAt(column);
            }
        }

        return 'E';
    }

    private Character getVisibleUpperVertical(int row, int column) {
        for(int i = row - 1; i >= 0; i--) {
            if(checkSeat(i, column)) {
                return this.inputSeats.get(i).charAt(column);
            }
        }

        return 'E';
    }

    private Character getVisibleRightHorizon(int row, int column) {
        for(int i = column + 1; i < inputSeats.get(row).length(); i++) {
            if(checkSeat(row, i)) {
                return this.inputSeats.get(row).charAt(i);
            }
        }

        return 'E';
    }

    private Character getVisibleLeftHorizon(int row, int column) {
        for(int i = column - 1; i >= 0; i--) {
            if(checkSeat(row, i)) {
                return this.inputSeats.get(row).charAt(i);
            }
        }

        return 'E';
    }

    private Character getVivisbleLowerRightDiagonal(int row, int column) {
        int y = column + 1;
        int x = row + 1;

        for(int i = x; i < inputSeats.size(); i++) {
            if((y < inputSeats.get(i).length() && i < inputSeats.size()) && checkSeat(i, y)) {
                return this.inputSeats.get(i).charAt(y);
            }
            y++;
        }

        return 'E';
    }

    private Character getVisibleLowerLeftDiagonal(int row, int column) {
        int y = column - 1;
        int x = row + 1;

        for(int i = x; i < inputSeats.size(); i++) {
            if((y > -1 && i < inputSeats.size()) && checkSeat(i, y)) {
                return this.inputSeats.get(i).charAt(y);
            }
            y--;
        }

        return 'E';
    }

    private Character getVisibleUpperRightDiagonal(int row, int column) {
        int y = column + 1;
        int x = row - 1;
        for(int i = x; i > -1; i--) {
            if((y < inputSeats.get(i).length() && i > -1) && checkSeat(i, y)) {
                return this.inputSeats.get(i).charAt(y);
            }
            y++;
        }

        return 'E';
    }

    private char getVisibleUpperLeftDiagonal(int row, int column) {
        int y = column - 1;
        int x = row - 1;
        for(int i = x; i > - 1; i--) {
            if((y > -1 && i > -1) && checkSeat(i, y)) {
                return this.inputSeats.get(i).charAt(y);
            }
            y--;
        }

        return 'E';
    }

    private boolean checkSeat(int row, int column) {
        return this.inputSeats.get(row).charAt(column) != '.';
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