import java.lang.Math;

import static java.lang.Math.sqrt;

public class SudokuPuzzle {
    private SudokuGrid grid;

    public SudokuPuzzle(int dimention) {
        grid = new SudokuGrid(dimention);
    }

    public boolean isInRow(int row, int value){
        for (int i=0; i<getDimention(); i++){
            if (grid.getGrid()[row][i] == value){
                return true;
            }
        }
        return false;
    }

    public boolean isInCol(int col, int value){
        for (int i=0; i<getDimention(); i++){
            if (grid.getGrid()[i][col] == value){
                return true;
            }
        }
        return false;
    }

    public boolean isInBox(int row, int col, int value){
        int boxRC = (int)sqrt(getDimention()); //Number of boxes in a row or column.
        int boxNumber = ((col-1)/boxRC*boxRC + row/boxRC); //current number of box of the value.
        for (int i=(boxNumber%boxRC/boxRC); i<(boxNumber%boxRC/boxRC)+boxRC; i++){
            for (int j=(boxNumber/boxRC*boxRC); j<(boxNumber/boxRC*boxRC)+boxRC; j++){
                if (grid.getGrid()[i][j] == value){
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
        if (canInsert(row, col, value)){
            grid.setGrid(row, col, value);
        }
        else {
            //Invalid movement, GUI message.
        }
    }

    public int getDimention(){
        return grid.getDimention();
    }

    public SudokuGrid getGrid() { return grid; }

    //public boolean supportsAI() { return false; }
    //public SudokuSolver createSolver() { return null; }
}
