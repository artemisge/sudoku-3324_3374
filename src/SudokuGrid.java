
public class SudokuGrid {
    private int[][] grid;
    private int dimention;

    public SudokuGrid(int dimention){
        grid = new int[dimention][dimention];
        this.dimention = dimention;
    }

    public int getDimention(){
        return dimention;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int row, int col, int value){
        grid[row][col] = value;
    }
}
