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
        this.name = name;
        solvedNormalPuzzles=new int[10];
        solvedKillerPuzzles=new int[10];
        duidokuWins = 0;
        duidokuLosses = 0;
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

    public void readFromFile(String name) {

        this.name = name;
        solvedNormalPuzzles=new int[10];
        solvedKillerPuzzles=new int[10];
        duidokuWins = 0;
        duidokuLosses = 0;
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
