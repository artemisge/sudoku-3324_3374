import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KillerSudokuTest {

    @Test
    public void testIsSumOk() {
        KillerSudoku k = new KillerSudoku(0);
        k.insert(0, 0, 9);
        boolean ok = k.isSumOk(1, 0, 9);
        assertEquals(false, ok);
    }

    @Test
    public void testCanInsert() {
        KillerSudoku k = new KillerSudoku(0);
        k.insert(0, 0, 9);
        boolean ok = k.canInsert(1, 0, 9);
        assertEquals(false, ok);
    }

    @Test
    public void testLoadFromFile() {
        KillerSudoku k = new KillerSudoku(0);
        k.loadFromFile(0);
        int[][] test = {{0, 1, 1, 2, 2, 2, 3, 3, 4},
                        {0, 5, 6, 6, 7, 8, 8, 9, 4},
                        {10, 5, 5, 11, 7,12, 9, 9,13},
                        {10,14,14,11,15,12,16,16,13},
                        {17,17,17,18,15,19,20,20,20},
                        {21,21,22,18,23,19,24,25,25},
                        {26,27,22,28,23,29,24,30,31},
                        {26,27,32,28,23,29,33,30,31},
                        {34,34,32,35,35,35,33,36,36}};
        boolean ok = true;
        for (int i = 0; i < k.dimension; i++) {
            for (int j = 0; j < k.dimension; j++) {
                if (k.regionIndex[i][j] != test[i][j]) {
                    ok = false;
                }
            }
        }
        assertEquals(true, ok);
    }

    @Test
    public void testIsSolved() {
        KillerSudoku k = new KillerSudoku(0);
        boolean test = k.isSolved();
        assertEquals(false,test);
    }

    @Test
    public void testSumArray() {
        KillerSudoku k = new KillerSudoku(0);
        assertEquals(15, k.regionSum[0]);
    }

}