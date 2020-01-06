public class Duidoku extends SudokuPuzzle {
    /**
     * will indicate who made the last valid move to determine the winner.
     * false: AI made the last valid move, true: user made the last valid move.
     * The game will finish when no other moves are allowed by user or AI. Last player who makes a valid move is the winner.
     */
    boolean lastValidMove;

    public Duidoku() {
        super(4);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                grid[i][j] = 0;
            }
        }
        lastValidMove = true; //the user always plays first
    }


    /**
     * AI that makes a move. It checks the grid cell by cell and inserts the first value it can.
     * It updates the variable lastValidMove so that the computer made the last move.
     */
    public void AI() {
        for (int n = 1; n <= dimension; n++) { //for every possible number
            for (int i = 0; i < dimension; i++){
                for (int j = 0; j < dimension; j++) { //for every grid box
                    if (canInsert(i, j, n)) {
                        insert(i, j, n);
                        lastValidMove = false; //AI made a valid move
                        return;
                    }
                }
            }
        }
    }

    /**
     * allowing insertion of value in the specific cell only if it goes by sudoku rules and the cell is empty.
     * in duidoku the cells can't be changed once they have a value inserted.
     * @param row
     * @param col
     * @param value
     * @return
     */
    @Override
    public boolean canInsert(int row, int col, int value){
        boolean sudokuRules = !isInRow(row, value) && !isInCol(col, value) && !isInBox(row, col, value);
        return sudokuRules && grid[row][col] == 0; //grid[row][col] == 0 means that we cannot remove a number already placed in the grid
    }


    /**
     * the duidoku game is finished with a winner when there are no more available moves in the game.
     * @return
     */
    @Override
    public boolean isSolved() {
        //checking for available moves
        boolean availableMoves = false;
        outerloop:
        for (int n = 1; n <= dimension; n++) { //for every possible number
            for (int i = 0; i < dimension; i++){
                for (int j = 0; j < dimension; j++) { //for every grid box
                    if (canInsert(i, j, n)) {
                        availableMoves = true; //still has available moves
                        break outerloop; //not finished yet, will break from nested loops
                    }
                }
            }
        }
        return !availableMoves; //only when there are no more available moves the game will be called finished
    }
}