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

    protected Player player;
    protected SudokuPuzzle puzzle;

    /**
     * will initialize to anonymous player in the beginning and to a classic sudoku
     */
    public GUI()
    {
        player = new Player("");
        int gameType = 0;
        makeFrame(gameType);
    }

    /**
     * makes the main window for the game and the sudoku grid with the starter puzzle (classic)
     * @param gameType 0: classic, 1: killer, 2: duidoku
     */
    private void makeFrame(int gameType)
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
        createPuzzle(gameType);  // arxikopoiei to dimension kai ftiaxnei enan array stin metabliti puzzle.
        createGamePanel(gameType); //analoga me to gameType, tha exei diaforetiko layout to grid kai diaforetikes epiloges (px sto duidoku den exei ClearBox button)

        add(game);
        pack();
        setVisible(true);

    }

    /**
     * panel inside of the main panel for sudoku grid-puzzle buttons (on the left/west side) and number-buttons and checkboxes (on the right/east side)
     * @param gameType 0: classic, 1: killer, 2: duidoku
     */
    private void createGamePanel(int gameType) {
        game.removeAll(); //in case of a new game, it clears everything

        //a panel inside of the main panel for
        // the options, help check box and number buttons
        //will be on the right
        eastPanel = new JPanel();
        eastPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        if (gameType == 0) { //classic
            classicEastPanel(constraints);
        } else if (gameType == 1) { //killer
            killerEastPanel(constraints);
        } else { //duidoku
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
        gridPanel.setLayout(new GridLayout(puzzle.dimension, puzzle.dimension));
        gridPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        gridPanel.setPreferredSize(new Dimension(500, 500));

        grid = new JButton[puzzle.dimension][puzzle.dimension];
        //initializing grid[][] and setting text to buttons
        for(int i = 0; i < puzzle.dimension; i++){
            for(int j = 0; j < puzzle.dimension; j++){
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


    /**
     * these are the panels for each game Type. They need to be distinguished
     * because each of them has different options and available buttons.
     * panel in normal type
     * @param constraints
     */
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
        numbers = new JButton[puzzle.dimension];
        for (int i = 1; i < puzzle.dimension+1; i++){
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

    /**
     * panel in killer type
     * @param constraints
     */
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
        numbers = new JButton[puzzle.dimension];
        for (int i = 1; i < puzzle.dimension+1; i++){
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

    /**
     * panel in duidoku type
     * @param constraints
     */
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
        numbers = new JButton[puzzle.dimension];
        for (int i = 1; i < puzzle.dimension+1; i++){
            numbers[i-1] = new JButton(Integer.toString(i));
            numbers[i-1].addActionListener(this);
            constraints.gridx = 3+i-1;
            constraints.gridy = 5;
            eastPanel.add(numbers[i-1], constraints);
        }
    }

    /**
     * menu bar with Options:
     * -New Game    -> Classic
     *              -> Killer
     *              -> Duidoku
     * -Log in/ Sign Up
     * -Statistics
     */
    private void createMenuBar()
    {
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
                    player.readFromFile(username);
                    player.updateFile();
                    if (!username.equals("")) {
                        userLabel.setText("You are logged in as " + player.getName());
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
               if (player.getName().equals("")) {
                   JOptionPane.showMessageDialog(getContentPane(), "You haven't logged in yet");
               } else {
                    JOptionPane.showMessageDialog(getContentPane(), "Duidoku Wins: " + player.getWins() + "\nDuidoku Losses: " + player.getLosses());
               }
            }
        });
        menuOptions.add(stats);

        setJMenuBar(menu);
    }


    /**
     * in case of a change in the puzzle-grid, this one updates the grid-buttons.
     */
    private void updateGrid(){
        for(int i = 0; i < puzzle.dimension; i++){
            for(int j = 0; j < puzzle.dimension; j++){
                convertGridNumber(grid[i][j], i , j);
            }
        }
    }



    /**
     * returns the equivalent char version of the number that is saved in grid[i][j]
     * boolean letters is to add or not the ascii value to convert to letters. true for letters, false for numbers
     * @param button
     * @param row
     * @param col
     */
    private void convertGridNumber(JButton button, int row, int col) {
        try {
            if ( puzzle.grid[row][col] == 0) {
                if (puzzle instanceof KillerSudoku) {
                    button.setMargin(new Insets(0, 0, 0, 0));
                    button.setText(String.format("<html><small>%s</small><br><center style='width: 50'><big>%s</big><br><small>&nbsp;</small>",
                            ((KillerSudoku) puzzle).regionSum[((KillerSudoku) puzzle).regionIndex[row][col]], "&nbsp;"));
                } else {
                    button.setText("");
                }
            } else {
                if (puzzle instanceof KillerSudoku) {
                    button.setMargin(new Insets(0, 0, 0, 0));
                    button.setText(String.format("<html><small>%s</small><br><center style='width: 50'><big>%s</big><br><small>&nbsp;</small>",
                            ((KillerSudoku)puzzle).regionSum[((KillerSudoku) puzzle).regionIndex[row][col]], puzzle.grid[row][col]));
                } else {
                    String d;
                    if (wordoku.isSelected()) {
                        d = Character.toString((char) (puzzle.grid[row][col]-1 + (int) 'A'));
                    } else {
                        d = Integer.toString(puzzle.grid[row][col]);
                    }
                    button.setMargin(new Insets(0, 0, 0, 0));
                    button.setText(String.format("<html><small>%s</small><br><center style='width: 50'><big>%s</big><br><small>&nbsp;</small>", "", d));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void createPuzzle(int gameType) {
        switch (gameType) {
            case 0:
                puzzle = new NormalSudoku(player.getNextUnsolvedPuzzle(0));
                break;
            case 1:
                puzzle = new KillerSudoku(player.getNextUnsolvedPuzzle(1));
                break;
            case 2:
                puzzle = new Duidoku();
                break;
        }
    }

    /**
     * colors all grid buttons with the default color (no help check box activated)
     */
    private void colorButtons() {
        if (puzzle instanceof KillerSudoku) {
            colorKiller();
        } else if (puzzle instanceof NormalSudoku) {
            colorNormal();
        } else {
            colorDuidoku();
        }
    }

    /**
     * colors Killer type
     */
    public void colorKiller() {
        //colors sum-regions differently
        for (int i = 0; i < puzzle.dimension; i++) { //for every cell in grid
            for (int j = 0; j < puzzle.dimension; j++) {
                int clr = ((KillerSudoku) puzzle).regionColor[((KillerSudoku) puzzle).regionIndex[i][j]]; //the color (regionColor) of the region we are now (grid[i][j]), represented by a number.
                Color c;
                switch (clr) {
                    case 1:
                        c = Color.decode("#fffd98");
                        break;
                    case 2:
                        c = Color.decode("#cfe799");
                        break;
                    case 3:
                        c = Color.decode("#cbe8fa");
                        break;
                    default:
                        c = Color.decode("#f8cfdf");
                        break;
                }
                grid[i][j].setBackground(c); //colors depending on the region number
            }
        }
    }

    /**
     * colors Normal type
     */
    public void colorNormal() {
        for (int i = 0; i < puzzle.dimension; i++) {
            for (int j = 0; j < puzzle.dimension; j++) {
                grid[i][j].setBackground(Color.lightGray);
                if ((((i)/(int)sqrt(puzzle.dimension)*(int)sqrt(puzzle.dimension) + j/(int)sqrt(puzzle.dimension)) % 2 == 1)) {
                    grid[i][j].setBackground(white);
                }
            }
        }
    }

    /**
     * colors duidoku type
     */
    public void colorDuidoku() {
        for (int i = 0; i < puzzle.dimension; i++) {
            for (int j = 0; j < puzzle.dimension; j++) {
                grid[i][j].setBackground(Color.lightGray);
                if ((((i)/(int)sqrt(puzzle.dimension)*(int)sqrt(puzzle.dimension) + j/(int)sqrt(puzzle.dimension)) % 3 == 0)) {
                    grid[i][j].setBackground(white);
                }
            }
        }
    }



    /**
     * ActionListener for puzzle-grid buttons, available number-buttons/clear-buttons, wordoku and help checkbox
     * Will use the variable clickedValue when the user clicks at a specific available number-button to assign a value to the grid
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        JComponent src = (JComponent) e.getSource(); //JComponent is a super class of JButton and JCheckBox

        if (src == wordoku) {
            updateGrid();
            wordokuNumbers();
        }

        if (!help.isSelected() && src == help) {
            System.out.println("Help is disabled");
            clickedValue = -1;
            colorButtons();
        } else if (help.isSelected() && src == help) {
            System.out.println("Help is enabled");
        }

        for (int i = 0; i < puzzle.dimension; i++){
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
            puzzle.reset();
            updateGrid();
            clickedValue = -1;
            System.out.println("Cleared all");
            if (help.isSelected()) {
                helpFunction();
            }
        }

        if ( clickedValue != -1) {
            //the case where the user has chosen a value, so clickedValue has a value other than -1
            for (int i = 0; i < puzzle.dimension; i++) {
                for (int j = 0; j < puzzle.dimension; j++){
                    if (src == grid[i][j]) {
                        if (puzzle.canInsert(i, j, clickedValue)) {
                            puzzle.insert(i, j, clickedValue);
                            updateGrid();
                            if (puzzle instanceof Duidoku) {
                                ((Duidoku) puzzle).lastValidMove = true; //player made a valid move
                                //after player's turn, AI will play
                                ((Duidoku) puzzle).AI();
                                updateGrid();

                            }
                            //if someone finishes the game
                            if (puzzle.isSolved()) {
                               gameFinished();
                            }
                        } else {
                            invalidMove();
                        }
                        if (help.isSelected()) {
                            helpFunction();
                        }

                    }
                }
            }
        }

    }

    /**
     * Label with a timer when the user makes an invalid move
     */
    private void invalidMove() {
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

    /**
     * Pop up message with the game info
     * updates file with the new stats
     */
    private void gameFinished() {
        //pop-up
        if (puzzle instanceof NormalSudoku) {
            JOptionPane.showMessageDialog(getContentPane(), "Congratulations, you solved a Classic Sudoku. For a new game go to Options.");
            player.addSolvedNormalPuzzle(puzzle.current);
        } else if (puzzle instanceof KillerSudoku) {
            JOptionPane.showMessageDialog(getContentPane(), "Congratulations, you solved a Killer Sudoku. For a new game go to Options.");
            player.addSolvedKillerPuzzle(puzzle.current);
        } else { //duidoku
            if (((Duidoku) puzzle).lastValidMove) {
                JOptionPane.showMessageDialog(getContentPane(), "Congratulations, you won the round! For a new game go to Options.");
                player.addWin(); //player made the last valid move so he won
            } else {
                JOptionPane.showMessageDialog(getContentPane(), "Computer won the round. For a new game go to Options.");
                player.addLoss(); //AI made the last valid move so user lost
            }
        }
        player.updateFile();
    }

    /**
     * assigns new text to available nkber-buttons when wordoku is checked
     */
    private void wordokuNumbers() {
        for (int i = 0; i < puzzle.dimension; i++) {
            String d;
            if (wordoku.isSelected()) {
                d = Character.toString((char) (i + 'A'));
            } else {
                d = Integer.toString(i+1);
            }
            numbers[i].setText(d);
        }
    }

    /**
     * when help is checked, colors the cells where user can place the value he chose at the moment (clickedValue)
     */
    private void helpFunction() { //help is enabled
        colorButtons();
        if (clickedValue != -1 && clickedValue != 0) {
            for (int i = 0; i < puzzle.dimension; i++) {
                for (int j = 0; j < puzzle.dimension; j++) {
                    if (puzzle.canInsert(i, j, clickedValue) && puzzle.grid[i][j] == 0) { //checks where the clickedValue can be placed
                        grid[i][j].setBackground(MAGENTA);
                    }
                }
            }
        }
    }
}