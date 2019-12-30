import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.awt.Color.CYAN;
import static java.awt.Color.white;
import static java.lang.Math.sqrt;

public class GUI extends JFrame implements ActionListener
{
    private JPanel game; //panel for the game, sudoku grid, buttons etc
    private JButton[][] grid;  //the grid constructed with buttons
    private boolean letters = false; //variable indicating wordoku
    private Integer[][] array;  //temp array gia main
    private int clickedValue = -1;
    private JButton numbers[];
    private JButton clearBox;
    private JButton clearAll;
    private JLabel userLabel;
    private GameManager gm;

    public GUI()
    {
        gm = new GameManager();
        int gameVersion = 0; //will initialize to a classic Sudoku
        makeFrame(gameVersion);
    }

    //makes the main window for the game and the sudoku grid with the starter puzzle
    private void makeFrame(int gameVersion)
    {
        //code for the main frame
        setTitle("Sudoku");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        createMenuBar();

        //this is to create the puzzle using the array from main
        // (will change later, taking the array from files)

        //main panel, will contain two smaller
        game = new JPanel(new BorderLayout());
        createPuzzle(gameVersion);  // arxikopoiei to dimension kai ftiaxnei enan array stin metabliti puzzle.

        //panel inside the main panel for sudoku grid
        // buttons, will be on the left
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(gm.puzzle.dimension, gm.puzzle.dimension));
        gridPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        gridPanel.setPreferredSize(new Dimension(500, 500));

        grid = new JButton[gm.puzzle.dimension][gm.puzzle.dimension];
        //initializing grid[][] and setting text to buttons
        for(int i = 0; i < gm.puzzle.dimension; i++){
            for(int j = 0; j < gm.puzzle.dimension; j++){
                grid[i][j] = new JButton("");
                grid[i][j].setBackground(Color.lightGray);
                if (gm.puzzle.dimension == 9){
                    if ((((i)/(int)sqrt(gm.puzzle.dimension)*(int)sqrt(gm.puzzle.dimension) + j/(int)sqrt(gm.puzzle.dimension)) % 2 == 1)){
                        grid[i][j].setBackground(white);
                    }
                }else{//an einai duidoku, oste na bgainoun xiasti ta xromata
                    if ((((i)/(int)sqrt(gm.puzzle.dimension)*(int)sqrt(gm.puzzle.dimension) + j/(int)sqrt(gm.puzzle.dimension)) % 3 == 0)){
                        grid[i][j].setBackground(white);
                    }
                }

                grid[i][j].addActionListener(this);
                convertGridNumber(grid[i][j], i , j);
                gridPanel.add(grid[i][j]);
            }
        }
        game.add(gridPanel, BorderLayout.WEST);

        //another panel inside of the main panel for
        // the options, help check box and number buttons
        //will be on the right
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //TODO fix this one for Killer Sudoku:
//        String html="<html><small>%s</small><br><center><big>&nbsp;&nbsp;&nbsp;%d&nbsp;&nbsp;&nbsp;</big><br><small>&nbsp;</small>";
//        JButton b3 = new JButton(String.format(html, "15", 9));

        clearAll = new JButton("Clear All");
        clearAll.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(5, 5, 50, 5);
        eastPanel.add(clearAll, constraints);

        JCheckBox wordoku = new JCheckBox("Wordoku");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(5, 5, 5, 5);
        eastPanel.add(wordoku, constraints);

        JCheckBox help = new JCheckBox("Help");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(5, 5, 5, 5);
        eastPanel.add(help, constraints);

        constraints.gridwidth = 1;
        constraints.insets = new Insets(50, 2, 5, 2);
        numbers = new JButton[gm.puzzle.dimension];
        for (Integer i = 1; i < gm.puzzle.dimension+1; i++){
            numbers[i-1] = new JButton(i.toString());
            numbers[i-1].addActionListener(this);
            constraints.gridx = i-1;
            constraints.gridy = 5;
            eastPanel.add(numbers[i-1], constraints);
        }

        constraints.insets = new Insets(5, 5, 5, 5);
        clearBox = new JButton("Clear Box");
        clearBox.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 3;
        eastPanel.add(clearBox, constraints);

        constraints.insets = new Insets(50, 5, 5, 5);
        userLabel = new JLabel("You haven't logged in, your stats won't be saved");
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 7;
        eastPanel.add(userLabel, constraints);

        game.add(eastPanel, BorderLayout.EAST);

        add(game);
        pack();
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
                createPuzzle(0);
            }
        });
        JMenuItem killer=new JMenuItem("Killer");
        killer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPuzzle(1);
            }
        });
        JMenuItem duidoku=new JMenuItem("Duidoku");
        duidoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPuzzle(2);
            }
        });
        newGame.add(classic);
        newGame.add(killer);
        newGame.add(duidoku);
        menuOptions.add(newGame);

        menuOptions.addSeparator();

        //Log in option in menu bar
        JMenuItem logIn=new JMenuItem("Log in/Sign up");
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(getContentPane(),"Username",null);
                gm.player.setName(username);
                userLabel.setText("You are logged as " + gm.player.getName());
                //TODO den apothikeuei dedomena, oute sugxronizei ta dedomena otan kanei login kapoios uparxontas xristis. prepei na ftiaxtei sunartisi stin class Player pou kanei update i kati..
            }
        });
        menuOptions.add(logIn);

        JMenuItem stats=new JMenuItem("Statistics");
        stats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gm.player.getName().equals("")) {
                    JOptionPane.showMessageDialog(getContentPane(), "You haven't logged in yet");
                } else {
                    JOptionPane.showMessageDialog(getContentPane(), "Duidoku Wins: " + gm.player.getWins() + "\nDuidoku Losses: " + gm.player.getLosses());
                }
            }
        });
        menuOptions.add(stats);

        setJMenuBar(menu);
    }



    //updates the whole grid, for example when there is a change
    // in boolean variable letters and the grid needs to change
    private void updateGrid(){
        for(int i = 0; i < gm.puzzle.dimension; i++){
            for(int j = 0; j < gm.puzzle.dimension; j++){
                convertGridNumber(grid[i][j], i , j);
            }
        }
    }

    //returns the equivalent char version of the number that is saved in grid[i][j]
    //boolean letters is to add or not the ascii value to convert to letters. true for letters, false for numbers
    private void convertGridNumber(JButton button, int row, int col) {

        //Integer add = letters? 65-49: 0;
        // 65 is the decimal value of "A" character and 45 is the decimal value of "1" character

        try {
            if ( gm.puzzle.grid[row][col] == 0){
                //TODO killer sudoku
//                button.setMargin(new Insets(0, 0, 0, 0));
//                button.setText(String.format("<html><small>%s</small><br><center style='width: 50'><big>%s</big><br><small>&nbsp;</small>", "14", ""));
                button.setText("");
            } else{
                button.setMargin(new Insets(0, 0, 0, 0));
                button.setText(String.format("<html><small>%s</small><br><center style='width: 50'><big>%s</big><br><small>&nbsp;</small>", "", Integer.toString(gm.puzzle.grid[row][col])));
            }
        }catch (Throwable e){
            System.out.println("Exception");
        }
    }

    private void createPuzzle(int gameVersion)
    {
        if (gameVersion == 0) {
            gm.puzzle = new NormalSudoku(gm.player.getNextUnsolvedPuzzle(0));
        } else if (gameVersion == 1) {
            gm.puzzle = new KillerSudoku(gm.player.getNextUnsolvedPuzzle(1));
        } else {
            gm.puzzle = new Duidoku();
        }
    }

//    actionListener for grid buttons
//  will use the variable clickedValue to assign a value to the grid
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();

        for (int i = 0; i < gm.puzzle.dimension; i++){
            if (numbers[i] == src) {
                clickedValue = i+1; //user has pressed that button, we save the value.
                System.out.println(clickedValue);
            }
        }
        if (clearBox == src) {
            clickedValue = 0;  //user has pressed clear button, we save the value.
            System.out.println(clickedValue);
        }

        if (clearAll == src) {
            gm.puzzle.reset();
            updateGrid();
        }

        if ( clickedValue == -1) {
            //the case where the user has clicked the
            // grid without first clicking a number to choose
            //the value -1 corresponds to not an (acceptable) value
        } else {
            //the case where the user has chosen a value, so clickedValue has a value other than -1
            for (int i = 0; i < gm.puzzle.dimension; i++) {
                for (int j = 0; j < gm.puzzle.dimension; j++){
                    if (src == grid[i][j]) {
                        if (gm.puzzle.canInsert(i, j, clickedValue)) {
                            gm.puzzle.insert(i, j, clickedValue);
                            updateGrid();
                        } else {
                            System.out.println("invalid move");
                            //TODO put that in timer Jlabel
                        }

                    }
                }
            }

        }

    }


}
//2 lines text in JButton
//JButton button = new JButton("<html><font size=-1><b><u>Click-Me!</u></b>"
//                                 + "<p>Do so!</html>");