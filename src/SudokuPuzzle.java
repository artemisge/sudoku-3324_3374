import javax.swing.*;
import java.lang.Math;

import static java.lang.Math.sqrt;

public class SudokuPuzzle {
    protected Integer[][] grid;
    protected int dimension;
    protected int elementsAdded;

    //this will load from files a puzzle and initialize accordingly
    public SudokuPuzzle(int dimension, Integer[][] fileGrid) {
        this.dimension = dimension;
        System.out.println("Mother Class Sudoku constructor, dimension is: "+ dimension);
        grid = new Integer[dimension][dimension];
        grid = fileGrid.clone();//can print grid, tested, so far so good
        elementsAdded = 0;
    }

    public boolean isInRow(int row, int value){
        for (int i=0; i<dimension; i++){
            if (grid[row][i] == value){
                return true;
            }
        }
        return false;
    }

    public boolean isInCol(int col, int value){
        for (int i=0; i<dimension; i++){
            if (grid[i][col] == value){
                return true;
            }
        }
        return false;
    }

    public boolean isInBox(int row, int col, int value){
        int boxRC = (int)sqrt(dimension); //Number of boxes in a row or column.
        int boxNumber = ((row)/boxRC*boxRC + col/boxRC); //current number of box of the value.
        for (int i=(boxNumber/boxRC*boxRC); i<(boxNumber/boxRC*boxRC)+boxRC; i++){
            for (int j=(boxNumber%boxRC*boxRC); j<(boxNumber%boxRC*boxRC)+boxRC; j++){
                if (grid[i][j] == value){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canInsert(int row, int col, int value){
        return !isInRow(row, value) && !isInCol(col, value) && !isInBox(row, col, value);
    }

    //adds, subtracts, or does nothing to elementsAdded,
    // depending on current and previous value of the grid box
    public void insert(int row, int col, int value){
        if (value == 0 && grid[row][col] != 0){
            elementsAdded -= 1;
        } else if (value != 0 && grid[row][col] == 0){
            elementsAdded += 1;
        }
        grid[row][col] = value;
    }

    public int getDimension(){
        return dimension;
    }

    public boolean isSolved() {
        return elementsAdded == dimension * dimension;
    }

    public Integer[][] getGrid() {
        return grid;
    }

    //public boolean supportsAI() { return false; }
    //public SudokuSolver createSolver() { return null; }*/
}
