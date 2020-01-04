import javax.swing.*;
import java.lang.Math;

import static java.lang.Math.sqrt;

public class SudokuPuzzle {
    protected int[][] grid;
    protected int dimension;
    protected int elementsAdded;
    protected int current; //current puzzle
    //saves the current puzzle number so later it can be added to solvedPuzzles successfully

    //this will load from files a puzzle and initialize accordingly
    public SudokuPuzzle(int dimension) {
        this.dimension = dimension;
        System.out.println("Mother Class Sudoku constructor, dimension is: "+ dimension);
        grid = new int[dimension][dimension];
        elementsAdded = 0;
    }

    /**
     * checks if the value that the user wants to place in this row is already in this row.
     * @param row
     * @param value
     * @return
     */
    public boolean isInRow(int row, int value){
        for (int i=0; i<dimension; i++){
            if (grid[row][i] == value){
                return true;
            }
        }
        return false;
    }

    /**
     * checks if the value that the user wants to place this column is already in this column.
     * @param col
     * @param value
     * @return
     */
    public boolean isInCol(int col, int value){
        for (int i=0; i<dimension; i++){
            if (grid[i][col] == value){
                return true;
            }
        }
        return false;
    }

    /**
     * checks if the value that the user wants to place this box is already in this box.
     * boxRC : Number of boxes in a row or column.
     * boxNumber : current number of box of the value.
     * @param row
     * @param col
     * @param value
     * @return
     */
    public boolean isInBox(int row, int col, int value){
        int boxRC = (int)sqrt(dimension);
        int boxNumber = ((row)/boxRC*boxRC + col/boxRC);
        for (int i=(boxNumber/boxRC*boxRC); i<(boxNumber/boxRC*boxRC)+boxRC; i++){
            for (int j=(boxNumber%boxRC*boxRC); j<(boxNumber%boxRC*boxRC)+boxRC; j++){
                if (grid[i][j] == value){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the value can be placed in the grid, keeping in mind the type of sudoku rules.
     * The input limitation comes from the button-numbers in GUI. But a really easy if (value>0 && value<10) can be added
     * @param row
     * @param col
     * @param value
     * @return
     */
    public boolean canInsert(int row, int col, int value) {
        boolean sudokuRules = !isInRow(row, value) && !isInCol(col, value) && !isInBox(row, col, value);
        return sudokuRules || value == 0;
    }

    /**
     * adds, subtracts, or does nothing to elementsAdded,
     * depending on current and previous value of the grid box
     * elementsAdded is a counter that is to check if the grid is filled hence the puzzle is solved.
     * @param row
     * @param col
     * @param value
     */
    public void insert(int row, int col, int value) {
        if (value == 0 && grid[row][col] != 0){
            elementsAdded -= 1;
        } else if (value != 0 && grid[row][col] == 0){
            elementsAdded += 1;
        }
        grid[row][col] = value;
    }


    /**
     * checks if the counter elementsAdded is equal to the grid's total capacity, meaning the puzzle is solved
     * @return
     */
    public boolean isSolved() {
        return elementsAdded == dimension * dimension;
    }

    /**
     * reading and assigning to puzzle-grid the number of puzzle in file that was passed as a parameter.
     * @param numberOfGame class Player uses the function getNextUnsolvedPuzzle to determine the next puzzle of the sudoku game type that the player is going to play
     */
    public void loadFromFile(int numberOfGame) {
        //to be overridden
    }

    /**
     * resets the grid to the starting situation, and that depends on the sudoku type
     */
    public void reset() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                grid[i][j] = 0;
            }
        }
    }
}
