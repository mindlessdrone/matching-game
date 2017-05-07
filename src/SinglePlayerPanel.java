/**
 * Created by Shao yu on 2017/5/4.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class SinglePlayerPanel extends JPanel
{
    //-----------------------------------------------------------------
    //  Sets up this panel with two labels.
    //-----------------------------------------------------------------
    private final int ROW = 4;
    private final int COL = 4;
    private JButton start,reset,clear,end;
    private JButton bt[];
    private int selected[];
    private ImageIcon [] img = new ImageIcon [8];
    String[] names = {"1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg", "8.jpg"};
    String array[]={"0","1","2","3","4","5","6","7"};
    private int tries = 0;
    private int totalTries = 0;
    private int games = 0;
    private int avg = 0;
    private JTextField name;
    private JPanel panel1 = new JPanel();
    private GameTilePanel gamePanel;

    public SinglePlayerPanel()
    {
        setBackground(new Color(130, 210, 225));
        start = new JButton("Start the Game");
        reset = new JButton("Reset the Game");
        clear = new JButton("Clear the Game");
        end = new JButton("End the System");
        name = new JTextField("Enter your name");
        bt = new JButton[16];
        selected = new int[16];

        setLayout(new BorderLayout());

        start.addActionListener(new ButtonListener());
        reset.addActionListener(new ButtonListener());
        clear.addActionListener(new ButtonListener());
        end.addActionListener(new ButtonListener());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(8, 1));
        buttonPanel.add(start);
        buttonPanel.add(reset);
        buttonPanel.add(clear);
        buttonPanel.add(end);

        add(buttonPanel, BorderLayout.LINE_END);
        add(name, BorderLayout.PAGE_END);

        gamePanel = new GameTilePanel();
        add(gamePanel, BorderLayout.CENTER);
    }
    private class ButtonListener implements ActionListener
    {
        //--------------------------------------------------------------
        //  Updates the counter and label when the button is pushed.
        //--------------------------------------------------------------
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == start)
            {
                gamePanel.initGame();        
            }
        }
    }

    private void setGame()
    {
        Random r = new Random();
        int d;
        String temp;
        int arr [] = new int[16] ;
        for(int i=0;i<8;i++)
        {
            img[i]=new ImageIcon(names[i]);
        }
        for(int i=1;i<9;i++){
            bt[i]=new JButton();
            int rand = r.nextInt(9-i);

            temp = array[8-i];
            array[8-i] = array[rand];
            array[rand] = temp;

            bt[i].setText(""+array[8-i]);
            bt[i].setIcon(img[Integer.parseInt(array[8-i])]);
            bt[i].setActionCommand(""+array[8-i]);

            panel1.add(bt[i]);
        }
    }
}

