import javax.swing.*;
import java.lang.Math;

import static java.lang.Math.sqrt;

public class SudokuPuzzle {
    private Integer[][] grid;
    /*private int dimension;
    private int elementsAdded;

    //this will load from files a puzzle and initialize accordingly
    public SudokuPuzzle(int dimension) {
        this.dimension = dimension;
        grid = new Integer[dimension][dimension];
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                grid[i][j] = fileGrid[i][j];
            }
        }
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
    }*/

    public Integer[][] getGrid() {
        return grid;
    }

    //public boolean supportsAI() { return false; }
    //public SudokuSolver createSolver() { return null; }*/
}
