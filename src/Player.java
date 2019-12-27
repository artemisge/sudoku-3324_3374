import java.util.HashMap;

public class Player {

    private String name;
    private int wins;
    private int losses;
    private HashMap<Integer,Integer> solvedPuzzles;
    private IOManager myManager;

    public Player(String name) {
        this.name = name;
        myManager=new IOManager(name);
        solvedPuzzles=new HashMap<>();
        wins=0;
        losses=0;
    }

    public String getName(){
        return name;
    }

    public int getWins()
    {
        return wins;
    }

    public int getLosses()
    {
        return losses;
    }

    private void addSolvedPuzzle()
    {
        int newPuzzle=myManager.getHashCode();
        solvedPuzzles.put(newPuzzle,1);
    }

    private boolean checkSolved(int hashCode)
    {
        if(solvedPuzzles.get(hashCode)==1)
            return true;
        return false;
    }

    private void addWin()
    {
        wins++;
    }

    private void addLoss()
    {
        losses++;
    }

    /*public boolean hasDonePuzzle(int index, int gameType){
        return puzzlesSolved[gameType][index];
    }

    public void setPuzzleDone(int index, int gameType){
        puzzlesSolved[gameType][index] = true;
    }*/
}
