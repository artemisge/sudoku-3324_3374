import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuPuzzleTest {

    @Test
    void isInRow() {
        SudokuPuzzle p=new SudokuPuzzle(9);
        assertEquals(false, p.isInRow(0,9));
    }

    @Test
    void isInCol() {
        SudokuPuzzle p=new SudokuPuzzle(9);
        assertEquals(false,p.isInCol(0,9));
    }

    @Test
    void isInBox() {
        SudokuPuzzle p=new SudokuPuzzle(9);
        assertEquals(false,p.isInBox(0,0,9));
    }

    @Test
    void canInsert() {
        SudokuPuzzle p=new SudokuPuzzle(9);
        assertEquals(true,p.canInsert(0,0,9));
    }

    @Test
    void insert() {
        SudokuPuzzle p=new SudokuPuzzle(9);
        p.insert(0,0,9);
        assertEquals(true,p.isInRow(0,9));
    }

    @Test
    void isSolved() {
        SudokuPuzzle p=new SudokuPuzzle(9);
        boolean test=p.isSolved();
        assertEquals(false,test);
    }

    @Test
    void loadFromFile() {
    }

    @Test
    void reset() {
        SudokuPuzzle p=new SudokuPuzzle(9);
        p.insert(1,0,8);
        p.reset();
        assertEquals(true,p.canInsert(1,0,8));
    }
}