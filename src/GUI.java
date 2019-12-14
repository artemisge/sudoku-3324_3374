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
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }


}
