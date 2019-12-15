import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static java.awt.Color.CYAN;

public class GUI extends JFrame
{
    //TO DELETE private JFrame f=new JFrame();
    private JPanel game; //panel for the game, sudoku grid, buttons etc
    private JButton[][] grid;//the grid constructed with buttons
    private int dimension;
    private SudokuPuzzle puzzle;
    boolean letters = false; //variable indicating wordoku
    private Integer[][] array;//temp array gia main

    public GUI(int gameVersion,Integer[][] array)
    {
        this.array = array.clone();
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

        createMenuBar();

        //this is to create the puzzle using the array from main
        // (will change later, taking the array from files)
        game = new JPanel();
        //game.setLayout(new BorderLayout());
        createPuzzle(gameVersion);
        grid = new JButton[dimension][dimension];

        //initializing grid[][] and setting text to buttons
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                grid[i][j] = new JButton("");
                convertGridNumber(grid[i][j], i , j);
                game.add(grid[i][j], BorderLayout.CENTER);
            }
        }
        add(game, BorderLayout.CENTER);
        setVisible(true);
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
        JMenuItem logIn=new JMenuItem("Log in/Sign in");
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username=JOptionPane.showInputDialog(getContentPane(),"Username",null);
            }
        });
        menuOptions.add(logIn);

        setJMenuBar(menu);
    }



    //updates the whole grid, for example when there is a change
    // in boolean variable letters and the grid needs to change
    private void updateGrid(){
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                convertGridNumber(grid[i][j], i , j);
            }
        }
    }

    //returns the equivalent char version of the number that is saved in grid[i][j]
    //boolean letters is to add or not the ascii value to convert to letters. true for letters, false for numbers
    private void convertGridNumber(JButton button, int row, int col){

        //Integer add = letters? 65-49: 0;
        // 65 is the decimal value of "A" character and 45 is the decimal value of "1" character

        try{
            button.setText(array[row][col].toString());
                    //puzzle.getGrid()[row][col].toString()); allagi gia main check
            button.addActionListener(this::actionPerformed);
            //button.setBounds(row*100, col*100, 1, 1);
        }catch (Throwable e){
            System.out.println("Exception");
        }
    }

    private void createPuzzle(int gameVersion)
    {
        if (gameVersion == 0){
            dimension = 9;
            puzzle = new NormalSudoku(array);
        }else if (gameVersion == 1){
            dimension = 9;
            puzzle = new KillerSudoku(array);
        }else {
            dimension = 4;
            puzzle = new Duidoku(array);
        }
    }

//    actionListener for grid buttons
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++){
                if (src==grid[i][j]) {
                    // Do something...
                }
              }
        }
    }


}
