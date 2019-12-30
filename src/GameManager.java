import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class GameManager
{

    private IOManager manager;
    private Player player;
    private boolean loggedIn;

    public GameManager() throws IOException
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
    public int[][] loadNormalPuzzle( InputStream in, SudokuPuzzle sudoku, String type) throws FileNotFoundException {
        int temp;
        int noOfPuzzle=-1;
        int wantedPuzzle;
        int low=0;
        int high=9;
        int [][] puzzle= new int[9][9];
        File file = new File("C:\\Users\\Oxi reman KALA\\IdeaProjects\\sudoku-3324_3374\\src\\Normal.txt");
        //if no user is logged in a random puzzle is chosen
        //wanted puzzle from 1 to 10
        Random r=new Random();
        wantedPuzzle=r.nextInt(high-low)+low;
        if(loggedIn)
        {
            if(player.checkSolved(sudoku,type))
            {
                temp=player.getUnsolvedPuzzle(type);
                if(temp==-1)
                    System.out.println("Player has solved all puzzles so an already solved puzzle is loading");
                else
                    wantedPuzzle=temp;
            }
        }
        sudoku.setNumberOfPuzzleInFile(wantedPuzzle);
        Scanner sc=new Scanner(file);
        while(noOfPuzzle!=wantedPuzzle) {
            if (sc.hasNextLine() && sc.nextLine()!=".") {
                for (int row = 0; row < puzzle.length; row++) {
                    String line = sc.nextLine();
                    for (int col = 0; col < puzzle[row].length; col++) {
                        puzzle[row][col] = Integer.parseInt(String.valueOf(line.charAt(col)));
                    }
                }
            }
            else
                noOfPuzzle++;
        }
        return puzzle;
    }

}

