import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class GameManager
{

    Player player;
    SudokuPuzzle puzzle;
    int currentPuzzleNumber; //saves the current puzzle number so later it can be added to solvedPuzzles successfully

    public GameManager()
    {
        player = new Player("");
    } //anonymous player in the beginning
}
