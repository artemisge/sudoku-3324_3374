import javax.swing.*;

public class GUI extends JFrame
{

    public GUI()
    {
        makeFrame();
    }

    private void makeFrame()
    {
        setTitle("Sudoku");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }


}
