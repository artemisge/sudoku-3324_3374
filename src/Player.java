import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


public class Player {

    private String name;
    private int duidokuWins;
    private int duidokuLosses;
    private int[][] solvedPuzzles;//0=normal, 1=killer

    public Player(String name) {
        //initializing everything from scratch and if the player already exists, loadFromFile will update everything
        this.name = name;
        solvedPuzzles=new int[2][10];
        for (int i = 0; i <2; i++) {
            for (int j = 0; j < 10; j++) {
                solvedPuzzles[i][j] = 0;
            }
        }
        duidokuWins=0;
        duidokuLosses=0;
        //loadFromFile(); TODO i dont think it works properly for now
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getWins()
    {
        return duidokuWins;
    }

    public int getLosses()
    {
        return duidokuLosses;
    }

    public void addSolvedPuzzle() {

    }


    public int getNextUnsolvedPuzzle(int gameVersion)
    {
        //TODO randomly
        return 0; //temp value until done right, it works though, calls the 2 puzzle
    }

    public void addWin()
    {
        duidokuWins++;
    }

    public void addLoss()
    {
        duidokuLosses++;
    }

    public void loadFromFile() {
        try {
            FileReader fileReader = new FileReader("players.txt");
            Scanner sc = new Scanner(fileReader);

            //The java.util.Scanner.next(String pattern) method returns the next token if it matches the pattern constructed from the
            // specified string. If the match is successful, the scanner advances past the input that matched the pattern.
            //opote an den uparxei to onoma den psaxnoume tipota allo
            //TODO if username has whitespace in between, it wont work!!
            sc.next(name); //an sunexisei, o user uparxei hdh kai fortwnoume ta dedomena tou
            while (sc.hasNext()) { //checking the classic puzzles
                String token = sc.next();
                if ("Killer".equals(token)) {
                    break; //we're going to killer puzzles now
                }
                solvedPuzzles[0][Integer.parseInt(token)] = 1; //converting string to integer, means that player has solved that classic puzzle
                //puzzles will be zero-based, so p.e. first sudoku puzzle will have an index of 0.
            }
            while (sc.hasNext()) { //checking the killer puzzles
                String token = sc.next();
                if ("Duidoku".equals(token)) {
                    break; //we're going to wins and losses of duidoku
                }
                solvedPuzzles[1][Integer.parseInt(token)] = 1; //converting string to integer, means that player has solved that killer puzzle
            }
            duidokuWins = sc.nextInt();
            duidokuLosses = sc.nextInt();
            sc.close();
        } catch(Exception e) {
            System.out.println("It was a File error in Player, probably not an existing player");
        }//TODO finally
    }
}
