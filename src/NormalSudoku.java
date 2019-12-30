import java.io.FileReader;
import java.util.Scanner;

public class NormalSudoku extends SudokuPuzzle {

    private int[][] initialGrid;  //keeps the initial condition of the grid in case of a reset

    public NormalSudoku(int numberOfGame) {
        super(9);
        System.out.println("Normal Sudoku constructor");
        initialGrid = new int[dimension][dimension];
        loadFromFile(numberOfGame);
    }

    @Override
    public void loadFromFile(int numberOfGame) {
        try {
            FileReader fileReader = new FileReader("Normal.txt");
            Scanner sc = new Scanner(fileReader);
            int numberOfTotalPuzzlesInFile = sc.nextInt();
            for (int p = 0; p < numberOfTotalPuzzlesInFile; p++) {
                for (int i = 0; i < dimension; i++) {
                    for (int j = 0; j < dimension; j++) {
                        int tmp = sc.nextInt();
                        if (numberOfGame == p) {
                            grid[i][j] = tmp;
                        }
                    }
                }
                // Stop reading when puzzle is found :)
                if (numberOfGame == p) {
                    break;
                }
            }
            sc.close();
        } catch(Exception e) {
            System.out.println("It was a File Oopsie in Classic");
        }//TODO finally

        for(int i=0; i<dimension; i++) {
            for(int j=0; j<dimension; j++) {
                initialGrid[i][j] = grid[i][j];
            }
        }
    }

    @Override
    public void reset() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                grid[i][j] = initialGrid[i][j];
            }
        }
    }

    @Override
    public boolean canInsert(int row, int col, int value){
        boolean isInInitialGrid = true;
        if (initialGrid[row][col] == 0){
            isInInitialGrid = false;
        }
        //checks if the grid box we want to change is in the initial grid
        return super.canInsert(row, col, value) && !isInInitialGrid;
    }
}
