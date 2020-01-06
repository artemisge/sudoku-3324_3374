import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    public void getName() {
        Player p = new Player("test");
        String name = p.getName();
        assertEquals("test", name);
    }

    @Test
    public void getWins() {
        Player p = new Player("test");
        int wins = p.getWins();
        assertEquals(0, wins);
    }

    @Test
    public void getLosses() {
        Player p = new Player("test");
        int losses = p.getLosses();
        assertEquals(0, losses);
    }

    @Test
    public void addSolvedNormalPuzzle() {
        Player p = new Player("test");
        p.addSolvedNormalPuzzle(0);
        assertEquals(1, p.getSolvedNormalPuzzle(0));
    }

    @Test
    public void addSolvedKillerPuzzle() {
        Player p = new Player("test");
        p.addSolvedKillerPuzzle(0);
        assertEquals(1, p.getSolvedKillerPuzzle(0));
    }

    @Test
    public void getNextUnsolvedPuzzle() {
        Player p = new Player("test");
        for (int i = 0; i < 9; i++) {
            p.addSolvedNormalPuzzle(i);
        }
        assertEquals(9, p.getNextUnsolvedPuzzle(0));
    }

    @Test
    public void addWin() {
        Player p = new Player("test");
        p.addWin();
        assertEquals(1, p.getWins());
    }

    @Test
    public void addLoss() {
        Player p = new Player("test");
        p.addLoss();
        assertEquals(1, p.getLosses());
    }

    @Test
    public void updateFile() {
//        Player p = new Player("test");
//        p.addWin();
//        assertEquals(1, p.getWins());
    }

    @Test
    public void initialize() {
        Player p = new Player("test");
        p.addWin();
        p.addLoss();
        p.initialize("test1");
        assertEquals("test1", p.getName());
        assertEquals(0, p.getLosses());
        assertEquals(0, p.getWins());

    }

    @Test
    public void readFromFile() {

    }
}