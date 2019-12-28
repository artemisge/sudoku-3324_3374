import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Player {

    private String name;
    private int duidokuWins;
    private int duidokuLosses;
    private HashMap<Integer,Integer> solvedPuzzles;
    private IOManager myManager;

    public Player(String name) {
        this.name = name;
        myManager=new IOManager();
        myManager.saveUser(name);
        solvedPuzzles=new HashMap<>();
        duidokuWins=0;
        duidokuLosses=0;
    }

    public String getName(){
        return name;
    }

    public int getWins()
    {
        return duidokuWins;
    }

    public int getLosses()
    {
        return duidokuLosses;
    }

    private void addSolvedPuzzle()
    {
        int newPuzzle=myManager.getHashCode();
        solvedPuzzles.put(newPuzzle,1);
    }

    public boolean checkSolved(int hashCode)
    {
        if(solvedPuzzles.get(hashCode)==1)
            return true;
        return false;
    }

    public int getNextUnsolvedPuzzle()
    {
        Iterator<Map.Entry<Integer,Integer>> it;
        it= solvedPuzzles.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<Integer,Integer> e=it.next();
            if(e.getValue()!=1)
                return e.getKey();
        }
        return -1;

    }

    private void addWin()
    {
        duidokuWins++;
    }

    private void addLoss()
    {
        duidokuLosses++;
    }

    /*public boolean hasDonePuzzle(int index, int gameType){
        return puzzlesSolved[gameType][index];
    }

    public void setPuzzleDone(int index, int gameType){
        puzzlesSolved[gameType][index] = true;
    }*/
}
