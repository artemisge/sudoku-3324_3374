import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DuidokuTest extends SudokuPuzzleTest {

    @Test
    void AI() {
        Duidoku d=new Duidoku();
        d.AI();
        assertEquals(false,d.lastValidMove);
    }

    @Test
    void testCanInsert() {
        Duidoku d=new Duidoku();
        assertEquals(true,d.canInsert(0,0,1));
    }

    @Test
    void testIsSolved() {
        Duidoku d= new Duidoku();
        assertEquals(false, d.isSolved());
    }
}