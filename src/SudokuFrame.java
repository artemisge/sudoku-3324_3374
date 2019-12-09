import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuFrame extends JFrame {

    private JPanel buttonSelectionPanel;
    private JPanel sudokuPanel;
    private JButton[][] grid;
    private JMenuBar menuBar;
    private JMenu file;
    private JMenu newGame;
    private ButtonHandler buttonHandler;

    public SudokuFrame(int dimention){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sudoku");
        this.setMinimumSize(new Dimension(800, 600));

        buttonHandler = new ButtonHandler();
        grid = new JButton[dimention][dimention];
        for (int i = 0; i < dimention; i++){
            for (int j = 0; j < dimention; j++){
                grid[i][j] = null;
                grid[i][j].addActionListener(buttonHandler);
            }
        }
        sudokuPanel = new JPanel();
        sudokuPanel.setLayout(new GridLayout(dimention, dimention));
        menuBar = new JMenuBar();
        file = new JMenu("Game");
        newGame = new JMenu("New Game");

        JMenuItem classicSudoku = new JMenuItem("Classic Sudoku");
        classicSudoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        newGame.add(classicSudoku);

        JMenuItem killerSudoku = new JMenuItem("Killer Sudoku");
        killerSudoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        newGame.add(killerSudoku);

        JMenuItem duidoku = new JMenuItem("Duidoku");
        duidoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        newGame.add(duidoku);

        file.add(newGame);
        menuBar.add(file);
        this.setJMenuBar(menuBar);

    }

    public JButton[][] getGrid(){
        return grid;
    }

    public void setButtonNumber(int row, int col, int value){
        Integer i = value;
        grid[row][col].setText(i.toString());
    }

    public class ButtonHandler implements ActionListener { //nested class
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Object source = actionEvent.getSource();
            for (int i = 0; i < 9; i++){
                for (int j = 0; j < 9; j++){
                    if (source == grid){
                        processClick(i, j);
                        return;
                    }
                }
            }
        }

        public void processClick(int i, int j){

        }
    }
}
