
public abstract class SudokuSolver<P extends SudokuPuzzle> {
    protected P puzzle;

    public SudokuSolver(P puzzle) {
        this.puzzle = puzzle;
    }
}