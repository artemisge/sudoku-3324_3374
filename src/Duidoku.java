//TODO currently not in GUI.

public class Duidoku extends SudokuPuzzle {
    public Duidoku() {
        super(4);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                grid[i][j] = 0;
            }
        }
    }


    public void AI() {
        for (int n = 1; n <= dimension; n++) { //for every possible number TODO randomize numbers
            for (int i = 0; i < dimension; i++){
                for (int j = 0; j < dimension; j++) { //for every grid box
                    if (canInsert(i, j, n)) {
                        insert(i, j, n);
                    }
                }
            }
        }
    }

    @Override
    public boolean canInsert(int row, int col, int value){
        boolean sudokuRules = !isInRow(row, value) && !isInCol(col, value) && !isInBox(row, col, value);
        return sudokuRules && grid[row][col] == 0; //grid[row][col] == 0 means that we cannot remove a number already placed in the grid
    }
}