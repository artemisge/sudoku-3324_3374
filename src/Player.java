import java.io.*;
import java.util.*;


public class Player implements Serializable { //Serializable has to do with file parsing

    //default serialVersion id (serializable shit)
    private static final long serialVersionUID = 1L;

    private String name;
    private int duidokuWins;
    private int duidokuLosses;
    private int[] solvedNormalPuzzles;
    private int[] solvedKillerPuzzles;


    public Player(String name) {
        initialize(name);
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

   //used for testing
    public int getSolvedNormalPuzzle(int index) {
        return solvedNormalPuzzles[index];
    }

   //used for testing
    public int getSolvedKillerPuzzle(int index) {
        return solvedKillerPuzzles[index];
    }

    /**
     * even if the player remains anonymous (""), the game will keep temporarily the puzzles he completed, but the data won't
     * be saved after the program is closed or the user signs with a different nickname. So if he continues playing, he won't encounter the same puzzles again.
     * @param currentPuzzleNumber
     */
    public void addSolvedNormalPuzzle(int currentPuzzleNumber) {
        solvedNormalPuzzles[currentPuzzleNumber] = 1;
    }

    public void addSolvedKillerPuzzle(int currentPuzzleNumber) {
        solvedKillerPuzzles[currentPuzzleNumber] = 1;
    }

    /**
     * depending on the game type and which puzzles the player has already completed, will return the number of puzzle we need to read from file later
     * @param gameType
     * @return
     */
    public int getNextUnsolvedPuzzle(int gameType)
    {
        int solvedPuzzles[];
        if (gameType==0) {
            solvedPuzzles = solvedNormalPuzzles;
        } else {
            solvedPuzzles = solvedKillerPuzzles;
        }
        int wantedPuzzle;
        Random r = new Random();
        wantedPuzzle = r.nextInt(solvedPuzzles.length);
        if (gameType == 0 && solvedPuzzles[wantedPuzzle]==1) {
            for (int i = 0; i < solvedPuzzles.length; i++) {
                if (solvedPuzzles[i] == 0) {
                    System.out.println("Puzzle chosen: " + i);
                    return i;
                }
            }
        }
        System.out.println("Puzzle chosen: " + wantedPuzzle);
        return wantedPuzzle;
    }

    public void addWin()
    {
        duidokuWins++;
    }

    public void addLoss()
    {
        duidokuLosses++;
    }

    /**
     * Updates file only if the username is different from "".
     * In that case, it makes an arrayList of class Player from file without the updates (or makes a blank one if the file doesn't exist)
     * and in order to update the stats for the current player, it searches the arrayList and if/when it founds him, deletes the old version
     * of him and then adds the new one. Afterwards it creates a new file and writes the arrayList of class Player, in a formatted way (implementing serializable).
     */
    public void updateFile() {

        if (!name.equals("")) { //saved only if player has logged in or uses a nickname other than ""
            ArrayList<Player> players;

            try { //diabazoume to arxeio kai ftiaxnoume ena arraylist kai an
                // den uparxei to arxeio ftiaxnoume ena keno arraylist
                FileInputStream fileIn = new FileInputStream("players.bin");
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                players = (ArrayList<Player>) objectIn.readObject();
            } catch (Exception e) {
                players = new ArrayList<Player>();
            }
            for (Player p : players) {
                if (p.name.equals(this.name)) {
                    players.remove(p);
                    break; //removing the old stats
                }
            }
            players.add(this); //adding the updated stats
            try {
                FileOutputStream fileOut = new FileOutputStream("players.bin");
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(players);
                objectOut.close();
                System.out.println("The file has been updated");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    public void initialize(String name) {
        this.name = name;
        //Everything in a Java program not explicitly set to something by the programmer, is initialized to a zero value.
        solvedNormalPuzzles=new int[10];
        solvedKillerPuzzles=new int[10];
        duidokuWins = 0;
        duidokuLosses = 0;
    }

    /**
     * while initializing the stats of the player in the beginning, then it parses through the file and creates a arrayList of Players and searches for the username.
     * If it's found, it updates the stats, else it is a new player and it is already initialized.
     * @param name
     */
    public void readFromFile(String name) {

        initialize(name);
        try {

            FileInputStream fileIn = new FileInputStream("players.bin");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            ArrayList<Player> players = new ArrayList<Player>();
            players = (ArrayList<Player>)objectIn.readObject();

            System.out.println("Scanning the file for the player");
            objectIn.close();
            boolean scan = false;
            for (Player p : players) {
                if (p.name.equals(this.name)) {
                    System.out.println("Player already registered. Uploading data...");
                    scan = true; //this is for the message of creating new player down below. probably there is a better way of checking this.. :P
                    this.duidokuWins = p.duidokuWins;
                    this.duidokuLosses = p.duidokuLosses;
                    this.solvedNormalPuzzles = p.solvedNormalPuzzles;
                    this.solvedKillerPuzzles = p.solvedKillerPuzzles;
                    break;
                }
            }
            if (!scan){
                System.out.println("Player wasn't found in the file. Creating new player...");
            }

        } catch (Exception ex) {
            System.out.println("File didn't exist");
        }
    }
}
