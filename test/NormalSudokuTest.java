import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalSudokuTest extends SudokuPuzzleTest {

    /**
     * the tests are created given that we know each puzzle inside Normal.txt
     */
    @Test
    void testLoadFromFile() {
        NormalSudoku n=new NormalSudoku(0);
        assertEquals(true,n.isInBox(0,1,4)&&n.isInCol(1,4)&&n.isInRow(0,4));
    }

    @Test
    void testReset() {
        NormalSudoku n=new NormalSudoku(0);
        n.insert(0,0,8); //we know it's a valid move
        n.reset();
        assertEquals(true,n.canInsert(0,0,8));
    }

    @Test
    void testCanInsert() {
        NormalSudoku n=new NormalSudoku(0);
        assertEquals(true,n.canInsert(0,0,8));
    }
}