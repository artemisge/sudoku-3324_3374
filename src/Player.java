import java.io.IOException;
import java.util.Iterator;
import java.util.Map;


public class Player {

    private String name;
    private int duidokuWins;
    private int duidokuLosses;
    private int [] normalSolvedPuzzles;
    private int [] killerSolvedPuzzles;
    //private HashMap<Integer,Integer> solvedPuzzles;
    private IOManager myManager;

    //2 constructores
    public Player(String name) throws IOException{
        this.name = name;
        normalSolvedPuzzles=new int[10];
        killerSolvedPuzzles=new int[10];
        //saveUser();
        //check if player exists
        myManager=new IOManager();
        myManager.addPlayer(name);
        //myManager.saveUser(name);
        //solvedPuzzles=new HashMap<>();
        duidokuWins=0;
        duidokuLosses=0;
    }

    public String getNormalSolvedPuzzles()
    {
        String solvedPuzzles="n";
        for(int i=0;i<normalSolvedPuzzles.length;i++)
        {
            if(normalSolvedPuzzles[i]==1)
                solvedPuzzles+=Integer.toString(i);
        }
        return solvedPuzzles;
    }

    public String getKillerSolvedPuzzles()
    {
        String solvedPuzzles="k";
        for(int i=0;i<killerSolvedPuzzles.length;i++)
        {
            if(killerSolvedPuzzles[i]==1)
                solvedPuzzles+=Integer.toString(i);
        }
        return solvedPuzzles;
    }
    private void saveUser(String name) //throws IOException
    {
        try {
            myManager.addPlayer(name);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
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


    public void addNormalSolvedPuzzle(SudokuPuzzle puzzle) throws IOException
    {
        int solvedPuzzle;
        if(puzzle.isSolved()) {
            solvedPuzzle = puzzle.getNumberOfPuzzleInFile();  //pairnoume to noumero tou lumenou puzzle sto file
            normalSolvedPuzzles[solvedPuzzle] = 1;
            myManager.modifyPlayerFile(this,"solvedNormal");
        }

    }

    public void addKillerSolvedPuzzle(SudokuPuzzle puzzle) throws IOException
    {
        int solvedPuzzle;
        if(puzzle.isSolved()) {
            solvedPuzzle = puzzle.getNumberOfPuzzleInFile();  //pairnoume to noumero tou lumenou puzzle sto file
            killerSolvedPuzzles[solvedPuzzle] = 1;
            myManager.modifyPlayerFile(this,"solvedKiller");
        }

    }


    public boolean checkSolved(SudokuPuzzle sudoku,String type)
    {
        if(type=="killer" && killerSolvedPuzzles[sudoku.getNumberOfPuzzleInFile()] == 1)
                return true;
        else if(type=="normal" && normalSolvedPuzzles[sudoku.getNumberOfPuzzleInFile()]==1)
            return true;
        return false;
    }

    public int getUnsolvedPuzzle(String type)
    {
        int puzzle=-1;
        if(type=="normal")
        {
            for(int i=0;i<normalSolvedPuzzles.length;i++)
            {
                if(normalSolvedPuzzles[i]==0)
                {
                    puzzle=i;
                    break;
                }
            }
        }
        else
        {
            for(int i=0;i<killerSolvedPuzzles.length;i++)
            {
                if(killerSolvedPuzzles[i]==0)
                {
                    puzzle=i;
                    break;
                }
            }
        }
        return puzzle;
    }

    private void addWin()
    {
        duidokuWins++;
        //updateFile
    }

    private void addLoss()
    {
        duidokuLosses++;
        //updateFile
    }

    //des an saresei kai apofasise :)
//    public Player(String name) {//+, int gameType
//        load(name);
//    }

//    // Reads player "name" from file, or initializes it to zero if not found
////    public void load(String name) {
////        this.name = name;
////    }
////
////    //updates file, once won or lost duidoku, or completed a puzzle
////    public void save() {
////
////    }









    /*public boolean hasDonePuzzle(int index, int gameType){
        return puzzlesSolved[gameType][index];
    }

    public void setPuzzleDone(int index, int gameType){
        puzzlesSolved[gameType][index] = true;
    }*/
}
