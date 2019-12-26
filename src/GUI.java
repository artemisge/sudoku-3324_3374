import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static java.awt.Color.CYAN;
import static java.awt.Color.white;
import static java.lang.Math.sqrt;

public class GUI extends JFrame implements ActionListener
{
    private JPanel game; //panel for the game, sudoku grid, buttons etc
    private JButton[][] grid;//the grid constructed with buttons
    private int dimension;
    private SudokuPuzzle puzzle;
    boolean letters = false; //variable indicating wordoku
    private Integer[][] array;//temp array gia main
    private int clickedValue = -1;
    JButton numbers[];
    JButton clearBox;
    JButton clearAll;

    public GUI(int gameVersion,Integer[][] array)
    {
        this.array = array.clone();
        makeFrame(gameVersion);
    }

    //makes the main window for the game
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

        //main panel, will contain two smaller
        game = new JPanel(new BorderLayout());


        //panel inside the main panel for sudoku grid
        // buttons, will be on the left
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(9, 9 ));
        gridPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        gridPanel.setPreferredSize(new Dimension(500, 500));

        createPuzzle(gameVersion);
        grid = new JButton[dimension][dimension];
        //initializing grid[][] and setting text to buttons
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                grid[i][j] = new JButton("");
                grid[i][j].setBackground(Color.lightGray);
                if ((((i)/(int)sqrt(dimension)*(int)sqrt(dimension) + j/(int)sqrt(dimension)) % 2 == 1)){
                    grid[i][j].setBackground(white);
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
        numbers = new JButton[dimension];
        for (Integer i = 1; i < dimension+1; i++){
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
        JLabel label = new JLabel("You haven't logged in, your stats won't be saved");
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 7;
        eastPanel.add(label, constraints);

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
            if ( array[row][col] == 0){
                button.setText("");
            } else{
                button.setText(array[row][col].toString());
            }
            //puzzle.getGrid()[row][col].toString()); allagi gia main check
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
//  will use the variable clickedValue to assign a value to the grid
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();

        for (int i = 0; i < dimension; i++){
            if (numbers[i] == src){
                clickedValue = i+1;//user has pressed that button, we save the value.
                System.out.println(clickedValue);
            }
        }
        if (clearBox == src){
            clickedValue = 0;//user has pressed clear button, we save the value.
            System.out.println(clickedValue);
        }

        if (clearAll == src){
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++){
                        array[i][j] = 0;
                }
            }
            updateGrid();
        }

        if ( clickedValue == -1){
            //the case where the user has clicked the
            // grid without first clicking a number to choose
            //the value -1 corresponds to not an (acceptable) value
        }else{
            //the case where the user has chosen a value, so clickedValue has a value other than -1
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++){
                    if (src == grid[i][j]) {
                        // Do something...
                        //just for the test, the correct version is commented out
                        array[i][j] = clickedValue;
                        updateGrid();
//                        if (puzzle.canInsert(i, j, clickedValue)){
//                            puzzle.insert(i, j, clickedValue);
//                        }else{
//                            //couldn't insert value to that grid box
//                            //put Timer JLabel
//                        }
                    }
                }
            }

        }

    }


}
//2 lines text in JButton
//JButton button = new JButton("<html><font size=-1><b><u>Click-Me!</u></b>"
//                                 + "<p>Do so!</html>");