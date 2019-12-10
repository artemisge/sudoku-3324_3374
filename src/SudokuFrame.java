import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuFrame extends JFrame {

    //basic layout, menu
    private JMenuBar menuBar;
    private JMenu user;
    private JMenu newGame;
    private JMenuItem classicSudoku;
    private JMenuItem killerSudoku;
    private JMenuItem duidoku;

    //sudoku grid components: nxn grid and n available numbers
    private JPanel sudokuPanel;
    private JButton[][] grid;
    private JButton[] numbers;


    private SudokuPuzzle puzzle;

    public SudokuFrame(){

        super("Sudoku Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 600));

        menuBar = new JMenuBar();
        user = new JMenu("User");
        newGame = new JMenu("New Game");

        classicSudoku = new JMenuItem("Classic Sudoku");
        classicSudoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        newGame.add(classicSudoku);

        killerSudoku = new JMenuItem("Killer Sudoku");
        killerSudoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        newGame.add(killerSudoku);

        duidoku = new JMenuItem("Duidoku");
        duidoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        newGame.add(duidoku);

        menuBar.add(newGame);
        menuBar.add(user);
        this.setJMenuBar(menuBar);
        this.setVisible(true);
    }

    public JButton[][] getGrid(){
        return grid;
    }

    public void setButtonNumber(int row, int col, int value){
        Integer i = value;
        grid[row][col].setText(i.toString());
    }

    public void startNewGame(int dimension){//TODO
        sudokuPanel = new JPanel();
        sudokuPanel.setLayout(new GridLayout(dimension, dimension));

        this.add(sudokuPanel);
    }

    public void updateGrid(int button, int row, int col){
        for (int i = 0; i < puzzle.getDimension(); i++){
            for (int j = 0; j < puzzle.getDimension(); j++){
                grid[i][j] = new JButton("");
                sudokuPanel.add(grid[i][j]);
                //grid[i][j].addActionListener(something);
            }
        }
    }

//    public class ButtonHandler implements ActionListener { //nested class
//        @Override
//        public void actionPerformed(ActionEvent actionEvent) {
//            Object source = actionEvent.getSource();
//            for (int i = 0; i < 9; i++){
//                for (int j = 0; j < 9; j++){
//                    if (source == grid){
//                        processClick(i, j);
//                        return;
//                    }
//                }
//            }
//        }
//
//        public void processClick(int i, int j){
//
//        }
//    }
}
