/**
 * A class for representing venue (seat layout) and movement of selected seat.
 * @author Subin Seol 1086852 
 * sseol@student.unimelb.edu.au
 */
public class Venue {

    // declare variables for the seat layout
    private int rows;
    private int left;
    private int middle;
    private int right;
    private char[][] initialLayout; // array for seating layout
    // variable for tracking the seat movement
    private int selectedRow = 0;
    private int selectedColumn = 0;
    // boolean to indicate whether the seat is selected
    private boolean seatSelection = false;

    // constructor 
    public Venue(int rows, int left, int middle, int right) {
        this.rows = rows;
        this.left = left;
        this.middle = middle;
        this.right = right;
        this.initialLayout = new char[rows][left + middle + right + 2]; // +2 for aisles
        initialiseLayout();
    }

    // Getters
    public int getRows() {
        return rows;
    }

    public int getLeft() {
        return left;
    }

    public int getMiddle() {
        return middle;
    }

    public int getRight() {
        return right;
    }


    // Initialization of seating layout using ' ' and '_'
    private void initialiseLayout() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < initialLayout[i].length; j++) {
                // Mark aisles
                if (j == left || j == left + middle + 1) {
                    initialLayout[i][j] = ' ';
                } else {
                    initialLayout[i][j] = '_';
                }
            }
        }
        // Set the initial 'X' at the first available seat
        moveSelectorToNextAvailable(0, -1); // Start before the first index
    }

    // Display the current state of seats layout
    /**public void showSeatingLayout() {
        for (int i = 0; i < rows; i++) {
            System.out.print("[");
            for (int j = 0; j < initialLayout[i].length; j++) {
                char layout = initialLayout[i][j];
                if (layout != 'B' && !seatSelection) {  // Hide 'X' unless in seat selection mode
                    System.out.print(layout == 'X' ? '_' : layout);
                } else {
                    System.out.print(layout);
                }
            }
            System.out.println("]");
        }
    } */

    public void showSeatingLayout() {
        for (int i = 0; i < rows; i++) {
            System.out.print("[");
            for (int j = 0; j < initialLayout[i].length; j++) {
                char layout = initialLayout[i][j];
                // Only show 'X' if seatSelection is true, otherwise replace it with '_'
                if (!seatSelection && layout == 'X') {
                    System.out.print('_'); // Replace 'X' with '_' when not in seat selection mode
                } else {
                    System.out.print(layout); // Display the actual layout character
                }
            }
            System.out.println("]");
        }
    }

    // Seat selection initiated (true) for option 4
    public void seatSelectionInitiated(boolean initiated) {
        this.seatSelection = initiated;
    }

    // User can move the seat around up, down, left, and right with designated key
    // If movement is valid, moveSeat is true (successful) and otherwise, moveSeat is false (unsuccessful)
    public boolean moveSeat(char direction) {
        int newRow = selectedRow;
        int newColumn = selectedColumn;

        switch (direction) {
            case 'W': // move up
                if (newRow > 0) {
                    newRow--;
                } else {
                    return false;
                }
                break;
            case 'S': // move down
                if (newRow < rows - 1) {
                    newRow++;
                } else {
                    return false;
                }
                break;
            case 'A': // move left
                if (newColumn > 0) {
                    newColumn--;
                } else {
                    return false;
                }
                // Skip any space for aisle
                if (newColumn == left || newColumn == left + middle + 1) {
                    newColumn--;
                }
                break;
            case 'D': // move right
                if (newColumn < left + middle + right + 1) {
                    newColumn++;
                }
                else {
                    return false;
                }
                // Skip any space for aisle
                if (newColumn == left || newColumn == left + middle + 1) {
                    newColumn++;
                }
                break;
            default:
                return false;
        }

        // Update the seat and save
        if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < initialLayout[newRow].length
            && initialLayout[newRow][newColumn] != ' ') {
            initialLayout[selectedRow][selectedColumn] = '_'; // Remove 'X'
            selectedRow = newRow;
            selectedColumn = newColumn;
            initialLayout[selectedRow][selectedColumn] = 'X'; // Place new 'X'
            return true;
        }
        return false;
    }

    public void selectSeat() {
        if (initialLayout[selectedRow][selectedColumn] == 'X') {
            initialLayout[selectedRow][selectedColumn] = 'B'; // Book the seat
            moveSelectorToNextAvailable(selectedRow, selectedColumn); // Move 'X' to next available seat
        }
    }

    // Helper method to find the next available seat
    private void moveSelectorToNextAvailable(int startRow, int startColumn) {
        for (int i = startRow; i < rows; i++) {
            for (int j = (i == startRow ? startColumn + 1 : 0); j < initialLayout[i].length; j++) {
                if (initialLayout[i][j] == '_') {
                    selectedRow = i;
                    selectedColumn = j;
                    initialLayout[selectedRow][selectedColumn] = 'X';
                    return;
                }
            }
        }
        // If no available seat is found, reset the selector to prevent error
        selectedRow = -1;
        selectedColumn = -1;
    }
}
