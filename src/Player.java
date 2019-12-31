import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


public class Player {

    private String name;
    private int duidokuWins;
    private int duidokuLosses;
    private int[] solvedNormalPuzzles;
    private int[] solvedKillerPuzzles;

    public Player(String name) {
        solvedNormalPuzzles=new int[10];
        solvedKillerPuzzles=new int[10];
        loadFromFile(name);
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

    //even if the player remains anonymous (""), the game will save the puzzles he completed, but the data won't
    // be saved after the program is closed. So if he continues playing, he won't encounter the same puzzles again.
    public void addSolvedNormalPuzzle(int currentPuzzleNumber) {
        solvedNormalPuzzles[currentPuzzleNumber] = 1;
    }

    public void addSolvedKillerPuzzle(int currentPuzzleNumber) {
        solvedKillerPuzzles[currentPuzzleNumber] = 1;
    }


    public int getNextUnsolvedPuzzle(int gameVersion)
    {
        //TODO randomly
        return 1; //temp value until done right, it works though, calls the 2 puzzle
    }

    public void addWin()
    {
        duidokuWins++;
    }

    public void addLoss()
    {
        duidokuLosses++;
    }

    public void loadFromFile(String name) {
        this.name = name;
        //initializing everything from scratch
        for (int i = 0; i < 10; i++) {
            solvedNormalPuzzles[i] = 0;
            solvedKillerPuzzles[i] = 0;
        }
        try {
            FileReader fileReader = new FileReader("players.txt");
            Scanner sc = new Scanner(fileReader);

            //The java.util.Scanner.next(String pattern) method returns the next token if it matches the pattern constructed from the
            // specified string. If the match is successful, the scanner advances past the input that matched the pattern.
            //opote an den uparxei to onoma den psaxnoume tipota allo, kai pigainoume sto catch, opou kanoume initialize ta wins/losses.
            //TODO if username has whitespace in between, it wont work!!
            sc.next(name); //an sunexisei, o user uparxei hdh kai fortwnoume ta dedomena tou
            while (sc.hasNext()) { //checking the classic puzzles
                String token = sc.next();
                if ("Killer".equals(token)) {
                    break; //we're going to killer puzzles now
                }
                solvedNormalPuzzles[Integer.parseInt(token)] = 1; //converting string to integer, means that player has solved that classic puzzle
                //puzzles will be zero-based, so p.e. first sudoku puzzle will have an index of 0.
            }
            while (sc.hasNext()) { //checking the killer puzzles
                String token = sc.next();
                if ("Duidoku".equals(token)) {
                    break; //we're going to wins and losses of duidoku
                }
                solvedKillerPuzzles[Integer.parseInt(token)] = 1; //converting string to integer, means that player has solved that killer puzzle
            }
            duidokuWins = sc.nextInt();
            duidokuLosses = sc.nextInt();
            sc.close();
        } catch(Exception e) {
            System.out.println("It was a File error in Player, probably not an existing player, so i'll initialize one");
            duidokuWins=0;
            duidokuLosses=0;
        }//TODO finally
    }

    public void readFromFile() {

    }

    public void saveToFile() {
//        try {
//            FileReader fileReader = new FileReader("players.txt");
//            Scanner sc = new Scanner(fileReader);
//
//            //The java.util.Scanner.next(String pattern) method returns the next token if it matches the pattern constructed from the
//            // specified string. If the match is successful, the scanner advances past the input that matched the pattern.
//            //opote an den uparxei to onoma den psaxnoume tipota allo
//            //TODO if username has whitespace in between, it wont work!!
//            sc.next(name); //an sunexisei, o user uparxei hdh kai fortwnoume ta dedomena tou
//            while (sc.hasNext()) { //checking the classic puzzles
//                String token = sc.next();
//                if ("Killer".equals(token)) {
//                    break; //we're going to killer puzzles now
//                }
//                solvedNormalPuzzles[Integer.parseInt(token)] = 1; //converting string to integer, means that player has solved that classic puzzle
//                //puzzles will be zero-based, so p.e. first sudoku puzzle will have an index of 0.
//            }
//            while (sc.hasNext()) { //checking the killer puzzles
//                String token = sc.next();
//                if ("Duidoku".equals(token)) {
//                    break; //we're going to wins and losses of duidoku
//                }
//                solvedKillerPuzzles[Integer.parseInt(token)] = 1; //converting string to integer, means that player has solved that killer puzzle
//            }
//            duidokuWins = sc.nextInt();
//            duidokuLosses = sc.nextInt();
//            sc.close();
//        } catch(Exception e) {
//            System.out.println("It was a File error in Player, probably not an existing player, so i'll initialize one");
//            duidokuWins=0;
//            duidokuLosses=0;
//        }//TODO finally
    }
}
