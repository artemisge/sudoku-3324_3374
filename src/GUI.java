import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static java.awt.Color.CYAN;

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
        setForeground(CYAN);

        JMenuBar menu=new JMenuBar();

        JMenu menuOptions=new JMenu("Options");
        menuOptions.setMnemonic(KeyEvent.VK_A);
        menuOptions.setDisplayedMnemonicIndex(0);
        menu.add(menuOptions);

        JMenu newGame =new JMenu("New Game");
        JMenuItem classic=new JMenuItem("Classic");
        JMenuItem killer=new JMenuItem("Killer");
        JMenuItem duidoku=new JMenuItem("Duidoku");
        newGame.add(classic);
        newGame.add(killer);
        newGame.add(duidoku);
        menuOptions.add(newGame);

        menuOptions.addSeparator();

        JMenuItem logIn=new JMenuItem("Log in");
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //popup to log in
            }
        });
        menuOptions.add(logIn);

        menuOptions.addSeparator();

        JMenuItem signIn=new JMenuItem("Sign in");
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //popup to sign in
            }
        });
        menuOptions.add(signIn);

        setJMenuBar(menu);

        setVisible(true);

    }


}
