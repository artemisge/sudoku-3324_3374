import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static java.awt.Color.CYAN;

public class GUI extends JFrame
{
    //private JPanel game; //panel for the game, sudoku grid, buttons etc
    private JButton[][] grid;//the grid constructed with buttons
    int dimention;
    private SudokuPuzzle puzzle;
    boolean letters = false; //variable indicating wordoku

    public GUI(int gameVersion)
    {
        makeFrame(gameVersion);
    }

    //maakes the main window for the game
    private void makeFrame(int gameVersion)
    {
        //code for the main frame
        setTitle("Sudoku");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);


        setVisible(true);

        /*
        //this is to create the grid
        createPuzzle(gameVersion);
        grid = new JButton[dimention][dimention];

        //initializing grid[][] and setting text to buttons
        for(int i = 0; i < dimention; i++){
            for(int j = 0; j < dimention; j++){
                convertGridNumber(grid[i][j], i , j);
                add(grid[i][j]);
            }
        }
        //add(game);*/


    }

    private void createMenuBar()
    {
        //menu bar with the options
        JMenuBar menu=new JMenuBar();

        JMenu menuOptions=new JMenu("Options");
        menuOptions.setMnemonic(KeyEvent.VK_A);
        menuOptions.setDisplayedMnemonicIndex(0);
        menu.add(menuOptions);

        //dropdown menu for the new game options
        JMenu newGame =new JMenu("New Game");
        JMenuItem classic=new JMenuItem("Classic");
        classic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //createPuzzle(0);
            }
        });
        JMenuItem killer=new JMenuItem("Killer");
        killer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //createPuzzle(1);
            }
        });
        JMenuItem duidoku=new JMenuItem("Duidoku");
        duidoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //createPuzzle(2);
            }
        });
        newGame.add(classic);
        newGame.add(killer);
        newGame.add(duidoku);
        menuOptions.add(newGame);

        menuOptions.addSeparator();

        //Log in option in menu bar
        JMenuItem logIn=new JMenuItem("Log in");
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //popup to log in
            }
        });
        menuOptions.add(logIn);

        menuOptions.addSeparator();

        //Sign in option in menu bar
        JMenuItem signIn=new JMenuItem("Sign in");
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //popup to sign in
            }
        });
        menuOptions.add(signIn);

        setJMenuBar(menu);
    }

/*
    //updates the whole grid, for example when there is a change
    // in boolean variable letters and the grid needs to change
    private void updateGrid(){
        for(int i = 0; i < dimention; i++){
            for(int j = 0; j < dimention; j++){
                convertGridNumber(grid[i][j], i , j);
            }
        }
    }

    //returns the equivalent char version of the number that is saved in grid[i][j]
    //boolean letters is to add or not the ascii value to convert to letters. true for letters, false for numbers
    private void convertGridNumber(JButton button, int row, int col){
        //option for wordoku should not be here
        //wordoku should be another function
        Integer add = letters? 65-49: 0; //65 is the decimal value of "A" character and 45 is the decimal value of "1" character
        try{
        button.setText(puzzle.getGrid()[row][col].toString());
        button.setBounds(row*100, col*100, 1, 1);
        }catch (Throwable e){
            System.out.println("Exception");
        }
    }

    private void createPuzzle(int gameVersion)
    {
        SudokuPuzzle puzzle;
        if (gameVersion == 0){
            dimention = 9;
            puzzle = new NormalSudoku();
        }else if (gameVersion == 1){
            dimention = 9;
            puzzle = new KillerSudoku();
        }else {
            dimention = 4;
            puzzle = new Duidoku();
        }
    }*/


    //useful code for changing between panels, after the main menu we go to game panel
//        public void actionPerformed(ActionEvent ae)
//        {
//            getContentPane().removeAll();
//            add(panel2);
//
//            repaint();
//        }



}
