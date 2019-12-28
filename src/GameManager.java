import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class GameManager
{

    IOManager manager;
    Player player;
    boolean loggedIn;

    public GameManager()
    {
        manager=new IOManager();
    }
    //creates a player if he exists
    public void setExistingPlayer(Player player)
    {
        this.player=player;
        loggedIn=true;
    }

    public boolean getLoggedIn()
    {
        return loggedIn;
    }

    public void showStatistics()
    {

    }

    // tha apofasizei k pooio puzzle tha fortwnei
    public String[] loadPuzzle() throws FileNotFoundException {
        int noPuzzle;
        int low=1;
        int high=10;
        String [] puzzle=new String[81];
        File file;

        if(!loggedIn) //if no user is logged in a random puzzle is chosen
        {
            Random r=new Random();
            noPuzzle=r.nextInt(high-low)+low;
            file =new File("C:\\Users\\Oxi reman KALA\\IdeaProjects\\sudoku-3324_3374\\src\\sudokuPuzzle1.txt");

        }
        else
        {
            noPuzzle=player.getNextUnsolvedPuzzle();
            if(noPuzzle==-1)
            {
                //user has solved all puzzles maybe load random puzzle instead??
            }
            file=new File("C:\\Users\\Oxi reman KALA\\IdeaProjects\\sudoku-3324_3374\\src\\sudokuPuzzle1.txt");
        }
        Scanner sc=new Scanner(file);
        while(sc.hasNextLine())
        {
            for(int i=0;i<81;i++)
                puzzle[i]=sc.nextLine();
        }
        return puzzle;
    }
}
