import javafx.scene.shape.Line;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static java.awt.Color.*;
import static java.lang.Math.sqrt;

public class GUI extends JFrame implements ActionListener
{
    private JPanel game; //panel for the game, sudoku grid, buttons etc
    private JPanel eastPanel;
    private JButton[][] grid;  //the grid constructed with buttons
    private int clickedValue = -1;
    private JButton[] numbers;
    private JButton clearBox;
    private JButton clearAll;
    private JLabel userLabel;
    private JLabel invalidLabel;
    private JCheckBox help;
    private JCheckBox wordoku;
    protected GameManager gm; //protected for testing

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

        //main panel, will contain two smaller
        game = new JPanel(new BorderLayout());
        userLabel = new JLabel("You haven't logged in, your stats won't be saved");
        createPuzzle(gameVersion);  // arxikopoiei to dimension kai ftiaxnei enan array stin metabliti puzzle.
        createGamePanel(gameVersion); //analoga me to gameVersion, tha exei diaforetiko layout to grid kai diaforetikes epiloges (px sto duidoku den exei ClearBox button)

        add(game);
        pack();
        setVisible(true);

    }

    private void createGamePanel(int gameVersion) {
        //panel inside the main panel for sudoku grid buttons, will be on the left
        game.removeAll(); //in case for a new game, it clears everything

        //a panel inside of the main panel for
        // the options, help check box and number buttons
        //will be on the right
        eastPanel = new JPanel();
        eastPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        if (gameVersion == 0) { //classic
            classicEastPanel(constraints); //nai, dustuxos xreiazomaste ta constraints
        } else if (gameVersion == 1) { //killer
            killerEastPanel(constraints);
        } else {
            duidokuEastPanel(constraints);
        }

        constraints.insets = new Insets(50, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.gridwidth = 10;
        eastPanel.add(userLabel, constraints);

        invalidLabel = new JLabel(" ");
        constraints.gridx = 0;
        constraints.gridy = 11;
        constraints.gridwidth = 10;
        eastPanel.add(invalidLabel, constraints);

        game.add(eastPanel, BorderLayout.CENTER);

        //west panel, for buttons-grid
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(gm.puzzle.dimension, gm.puzzle.dimension));
        gridPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        gridPanel.setPreferredSize(new Dimension(500, 500));

        grid = new JButton[gm.puzzle.dimension][gm.puzzle.dimension];
        //initializing grid[][] and setting text to buttons
        for(int i = 0; i < gm.puzzle.dimension; i++){
            for(int j = 0; j < gm.puzzle.dimension; j++){
                grid[i][j] = new JButton("");
                grid[i][j].addActionListener(this);
                convertGridNumber(grid[i][j], i , j);
                gridPanel.add(grid[i][j]);
            }
        }
        colorButtons(); //colors the grid buttons
        game.add(gridPanel, BorderLayout.WEST);
        game.revalidate(); //something like an update for the panel
    }

    //these are the panels for each game Version. They need to be distinguished
    // because each of them has different options and available buttons.
    private void classicEastPanel(GridBagConstraints constraints) {
        clearAll = new JButton("Clear All");
        clearAll.addActionListener(this);
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(5, 5, 50, 5);
        eastPanel.add(clearAll, constraints);

        wordoku = new JCheckBox("Wordoku");
        wordoku.addActionListener(this);
        constraints.gridx = 3;
        constraints.gridy = 3;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(5, 5, 5, 5);
        eastPanel.add(wordoku, constraints);

        help = new JCheckBox("Help");
        help.addActionListener(this);
        constraints.gridx = 3;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(5, 5, 5, 5);
        eastPanel.add(help, constraints);

        constraints.gridwidth = 1;
        constraints.insets = new Insets(50, 2, 5, 2);
        numbers = new JButton[gm.puzzle.dimension];
        for (int i = 1; i < gm.puzzle.dimension+1; i++){
            numbers[i-1] = new JButton(Integer.toString(i));
            numbers[i-1].addActionListener(this);
            constraints.gridx = i-1;
            constraints.gridy = 5;
            eastPanel.add(numbers[i-1], constraints);
        }

        constraints.insets = new Insets(5, 5, 5, 5);
        clearBox = new JButton("Clear Box");
        clearBox.addActionListener(this);
        constraints.gridx = 3;
        constraints.gridy = 7;
        constraints.gridwidth = 3;
        eastPanel.add(clearBox, constraints);
    }

    private void killerEastPanel(GridBagConstraints constraints) {

        clearAll = new JButton("Clear All");
        clearAll.addActionListener(this);
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(5, 5, 50, 5);
        eastPanel.add(clearAll, constraints);

        help = new JCheckBox("Help");
        help.addActionListener(this);
        constraints.gridx = 3;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(5, 5, 5, 5);
        eastPanel.add(help, constraints);

        constraints.gridwidth = 1;
        constraints.insets = new Insets(50, 2, 5, 2);
        numbers = new JButton[gm.puzzle.dimension];
        for (int i = 1; i < gm.puzzle.dimension+1; i++){
            numbers[i-1] = new JButton(Integer.toString(i));
            numbers[i-1].addActionListener(this);
            constraints.gridx = i-1;
            constraints.gridy = 5;
            eastPanel.add(numbers[i-1], constraints);
        }

        constraints.insets = new Insets(5, 5, 5, 5);
        clearBox = new JButton("Clear Box");
        clearBox.addActionListener(this);
        constraints.gridx = 3;
        constraints.gridy = 7;
        constraints.gridwidth = 3;
        eastPanel.add(clearBox, constraints);
    }

    private void duidokuEastPanel(GridBagConstraints constraints) {
        String  sText  = "<html>Win your opponent by making <br> the last valid move on the game.</html>";
        JLabel duidokuLabel = new JLabel(sText);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 10;
        constraints.gridheight = 2;
        constraints.insets = new Insets(5, 5, 50, 5);
        eastPanel.add(duidokuLabel, constraints);

        wordoku = new JCheckBox("Wordoku");
        wordoku.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 9;
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridheight = 1;
        constraints.insets = new Insets(5, 5, 5, 5);
        eastPanel.add(wordoku, constraints);

        help = new JCheckBox("Help");
        help.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 9;
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);
        eastPanel.add(help, constraints);

        constraints.gridwidth = 1;
        //constraints.fill = GridBagConstraints.PAGE_END;
        constraints.insets = new Insets(50, 2, 5, 2);
        numbers = new JButton[gm.puzzle.dimension];
        for (int i = 1; i < gm.puzzle.dimension+1; i++){
            numbers[i-1] = new JButton(Integer.toString(i));
            numbers[i-1].addActionListener(this);
            constraints.gridx = 3+i-1;
            constraints.gridy = 5;
            eastPanel.add(numbers[i-1], constraints);
        }
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
                createGamePanel(0);
            }
        });
        JMenuItem killer=new JMenuItem("Killer");
        killer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPuzzle(1);
                createGamePanel(1);
            }
        });
        JMenuItem duidoku=new JMenuItem("Duidoku");
        duidoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPuzzle(2);
                createGamePanel(2);
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
                String username = JOptionPane.showInputDialog(getContentPane(),"Username","");
                if (username != null){ //handling cancel case
                    gm.player.readFromFile(username);
                    gm.player.updateFile();
                    if (!username.equals("")) {
                        userLabel.setText("You are logged as " + gm.player.getName());
                    } else {
                        userLabel.setText("You haven't logged in, your stats won't be saved");
                    }
                }
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
    // in boolean variable letters (indicating wordoku) and the grid needs to change
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
        try {
            if ( gm.puzzle.grid[row][col] == 0) {
                if (gm.puzzle instanceof KillerSudoku) {
                    button.setMargin(new Insets(0, 0, 0, 0));
                    button.setText(String.format("<html><small>%s</small><br><center style='width: 50'><big>%s</big><br><small>&nbsp;</small>",
                            ((KillerSudoku) gm.puzzle).regionSum[((KillerSudoku) gm.puzzle).regionIndex[row][col]], "&nbsp;"));
                } else {
                    button.setText("");
                }
            } else {
                if (gm.puzzle instanceof KillerSudoku) {
                    button.setMargin(new Insets(0, 0, 0, 0));
                    button.setText(String.format("<html><small>%s</small><br><center style='width: 50'><big>%s</big><br><small>&nbsp;</small>",
                            ((KillerSudoku) gm.puzzle).regionSum[((KillerSudoku) gm.puzzle).regionIndex[row][col]], gm.puzzle.grid[row][col]));
                } else {
                    String d;
                    if (wordoku.isSelected()) {
                        d = Character.toString((char) (gm.puzzle.grid[row][col]-1 + (int) 'A'));
                    } else {
                        d = Integer.toString(gm.puzzle.grid[row][col]);
                    }
                    button.setMargin(new Insets(0, 0, 0, 0));
                    button.setText(String.format("<html><small>%s</small><br><center style='width: 50'><big>%s</big><br><small>&nbsp;</small>", "", d));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void createPuzzle(int gameVersion)
    {
        if (gameVersion == 0) {
            gm.currentPuzzleNumber = gm.player.getNextUnsolvedPuzzle(0);
            gm.puzzle = new NormalSudoku(gm.currentPuzzleNumber);
        } else if (gameVersion == 1) {
            gm.currentPuzzleNumber = gm.player.getNextUnsolvedPuzzle(1);
            gm.puzzle = new KillerSudoku(gm.currentPuzzleNumber);
        } else {
            gm.puzzle = new Duidoku();
        }
    }

    private void colorButtons() { //colors all grid buttons with the default color (no help check box activated
        if (gm.puzzle instanceof KillerSudoku){ //killer sudoku coloring
            //color sum regions differently
            for (int i = 0; i < gm.puzzle.dimension; i++) { //gia kathe cell sto grid
                for (int j = 0; j < gm.puzzle.dimension; j++) {
                    int clr = ((KillerSudoku) gm.puzzle).regionColor[((KillerSudoku) gm.puzzle).regionIndex[i][j]]; //to xroma (regionColor) pou brisketai stin perioxi pou eimaste tora (grid[i][j]), anaparistatai se arithmo.
                    Color c;
                    switch (clr) {
                        case 1:
                            c = Color.decode("#fffd98"); // lightGray;
                            break;
                        case 2:
                            c = Color.decode("#cfe799"); // DARK_GRAY;
                            break;
                        case 3:
                            c = Color.decode("#cbe8fa"); // pink;
                            break;
                        default:
                            c = Color.decode("#f8cfdf"); // white;
                            break;
                    }
                    grid[i][j].setBackground(c); //xromatizei analoga ton arithmo tis perioxis
                }
            }
        } else { //normal and duidoku coloring
            for (int i = 0; i < gm.puzzle.dimension; i++) {
                for (int j = 0; j < gm.puzzle.dimension; j++) {
                    grid[i][j].setBackground(Color.lightGray);
                    if (gm.puzzle.dimension == 9) {
                        if ((((i)/(int)sqrt(gm.puzzle.dimension)*(int)sqrt(gm.puzzle.dimension) + j/(int)sqrt(gm.puzzle.dimension)) % 2 == 1)) {
                            grid[i][j].setBackground(white);
                        }
                    } else {  //an einai duidoku, oste na bgainoun xiasti ta xromata
                        if ((((i)/(int)sqrt(gm.puzzle.dimension)*(int)sqrt(gm.puzzle.dimension) + j/(int)sqrt(gm.puzzle.dimension)) % 3 == 0)) {
                            grid[i][j].setBackground(white);
                        }
                    }
                }
            }
        }
    }

//    actionListener for grid buttons and help
//  will use the variable clickedValue to assign a value to the grid
    public void actionPerformed(ActionEvent e) {
        JComponent src = (JComponent) e.getSource(); //JComponent is a super class of JButton and JCheckBox

        if (src == wordoku) {
            updateGrid();
        }

        if (!help.isSelected() && src == help) {
            System.out.println("Help is disabled");
            clickedValue = -1;
            colorButtons();
        } else if (help.isSelected() && src == help) {
            System.out.println("Help is enabled");
        }

        for (int i = 0; i < gm.puzzle.dimension; i++){
            if (numbers[i] == src) {
                clickedValue = i+1; //user has pressed that button, we save the value.
                System.out.println(clickedValue);
                if (help.isSelected()) {
                    helpFunction();
                }
            }
        }
        if (clearBox == src) {
            clickedValue = 0;  //user has pressed clear button, we save the value.
            System.out.println(clickedValue);
            if (help.isSelected()) {
                helpFunction();
            }
        }

        if (clearAll == src) {
            gm.puzzle.reset();
            updateGrid();
            clickedValue = -1;
            System.out.println("Cleared all");
            if (help.isSelected()) {
                helpFunction();
            }
        }

        if ( clickedValue != -1) {
            //the case where the user has chosen a value, so clickedValue has a value other than -1
            for (int i = 0; i < gm.puzzle.dimension; i++) {
                for (int j = 0; j < gm.puzzle.dimension; j++){
                    if (src == grid[i][j]) {
                        if (gm.puzzle.canInsert(i, j, clickedValue)) {
                            gm.puzzle.insert(i, j, clickedValue);
                            updateGrid();
                            if (gm.puzzle instanceof Duidoku) {
                                ((Duidoku) gm.puzzle).lastValidMove = true; //player made a valid move
                                //after player's turn, AI will play
                                ((Duidoku) gm.puzzle).AI();
                                updateGrid();

                            }
                            //an kapoios kerdisei to paixnidi!!!
                            if (gm.puzzle.isSolved()) {
                                //pop-up
                                if (gm.puzzle instanceof NormalSudoku) {
                                    JOptionPane.showMessageDialog(getContentPane(), "Congratulations, you solved a Classic Sudoku. For a new game go to Options.");
                                    gm.player.addSolvedNormalPuzzle(gm.currentPuzzleNumber);
                                } else if (gm.puzzle instanceof KillerSudoku) {
                                    JOptionPane.showMessageDialog(getContentPane(), "Congratulations, you solved a Killer Sudoku. For a new game go to Options.");
                                    gm.player.addSolvedKillerPuzzle(gm.currentPuzzleNumber);
                                } else { //duidoku
                                    if (((Duidoku) gm.puzzle).lastValidMove) {
                                        JOptionPane.showMessageDialog(getContentPane(), "Congratulations, you won the round! For a new game go to Options.");
                                        gm.player.addWin(); //player made the last valid move so he won
                                    } else {
                                        JOptionPane.showMessageDialog(getContentPane(), "Computer won the round. For a new game go to Options.");
                                        gm.player.addLoss(); //AI made the last valid move so user lost
                                    }
                                }
                                gm.player.updateFile();
                            }
                        } else {
                            invalidLabel.setText("Invalid move");
                            System.out.println("invalid move");
                            Timer timer = new Timer();
                            TimerTask task = new TimerTask() {
                                @Override
                                public void run() {
                                    invalidLabel.setText(" ");
                                }
                            };
                            timer.schedule(task, 2000);

                        }
                        if (help.isSelected()) {
                            helpFunction();
                        }

                    }
                }
            }
        }

    }


    private void helpFunction() { //help is enabled
        colorButtons();
        if (clickedValue != -1 && clickedValue != 0) {
            for (int i = 0; i < gm.puzzle.dimension; i++) {
                for (int j = 0; j < gm.puzzle.dimension; j++) {
                    if (gm.puzzle.canInsert(i, j, clickedValue) && gm.puzzle.grid[i][j] == 0) { //checks where the clickedValue can be placed
                        grid[i][j].setBackground(MAGENTA);
                    }
                }
            }
        }
    }
}