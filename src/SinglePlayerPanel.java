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
    private JButton start,reset,clear,end;
    private GameTilePanel gamePanel;
    private HistoryPanel historyPanel;
    JLabel statusText;
    int players;

    public SinglePlayerPanel(HistoryPanel hPanel)
    {
        setBackground(new Color(130, 210, 225));
        start = new JButton("Start the Game");
        reset = new JButton("Reset the Game");
        clear = new JButton("Clear the Game");
        end = new JButton("End the System");

        setLayout(new BorderLayout());

        historyPanel = hPanel;
        statusText = new JLabel("");

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

        gamePanel = new GameTilePanel();
        gamePanel.setHistoryObj(historyPanel);
        gamePanel.setStatusObj(statusText);

        add(gamePanel, BorderLayout.CENTER);
        add(statusText, BorderLayout.PAGE_END);
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
                players = getNumberPlayers();
                gamePanel.initGame(players, true);        
            }
            else if (event.getSource() == clear)
            {
                gamePanel.initGame(players, false);
            }
            else if (event.getSource() == reset)
            {
                System.out.println("out");
                historyPanel.clearHistoryState();
            }
            else
            {
                // exit program
                System.exit(0);
            } // end if
        }
    }

    private int getNumberPlayers()
    {
        // local constants
        // local variables
        String userInput;           // string entered from user
        
        // get user input
        userInput = (String) JOptionPane.showInputDialog(this, "How many people will be playing?",
                                                         "Players", JOptionPane.PLAIN_MESSAGE,
                                                         null, null, "1");
        while (!userInput.matches("\\d+"))
        {
            // display error
            JOptionPane.showMessageDialog(this, "Input was not in the form of a number",
                                          "Invalid Input", JOptionPane.ERROR_MESSAGE);
            
            // get user input
            userInput = (String) JOptionPane.showInputDialog(this, "How many people will be playing?",
                    "Players", JOptionPane.PLAIN_MESSAGE,
                    null, null, "1");
        }

        return Integer.parseInt(userInput);
    }

}

