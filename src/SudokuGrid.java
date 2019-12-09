
public class SudokuGrid {
    private int[][] grid;
    private int size;

    public SudokuGrid(int size){
        grid = new int[size][size];
        this.size = size;
    }

    public int getSize(){
        return size;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int row, int col, int value){
        grid[row][col] = value;
    }
}
