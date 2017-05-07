import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.*;

public class GameTilePanel extends JPanel
{
    // class fields
    JButton[] tiles;  // tiles used for games
    int[] tilesSelected; // tiles selected
    int numTilesSelected;    // number of tiles selected
    int[] answers;          // number answers DEBUGGING ONLY
    Player[] players;       // player objects carrying player state
    int currentPlayer;      // current player

    public GameTilePanel()
    {
        // local constants
        // local variables

        // initalize tile array
        tiles = new JButton[16];

        // init tiles selected array
        tilesSelected = new int[16];

        // set layout
        setLayout(new GridLayout(4, 4));

        // loops for buttons
        for (int i = 0; i < tiles.length; i++)
        {
            // initalize tile button
            tiles[i] = new JButton();

            // disable all buttons
            tiles[i].setEnabled(false);

            // add listener
            tiles[i].addActionListener(new GameTileListener(i));

            // add to pane
            add(tiles[i]);

        }

        // initalize answer array
        answers = new int[16];
        for (int i = 0; i < answers.length; i += 2)
        {
            answers[i] = i / 2 + 1;
            answers[i + 1] = i / 2 + 1;
        }

        // initialize player array
        players = new Player[2];

    }

    // initalizes game state
    public void initGame()
    {
        // initalize all players
        for (int i = 0; i < players.length; i++)
        {
            // initialize player object
            players[i] = new Player();
        }

        // permute answer array
        permuteArray(answers);

        // set current player to first player
        currentPlayer = 0;

        // enable all of the buttons
        for (int i = 0; i < tiles.length; i++)
        {
            tiles[i].setText("");
            tiles[i].setEnabled(true);
        }

        numTilesSelected = 0;

    }


    private void permuteArray(int[] arr)
    {
        // local constants
        // local variables
        Random r = new Random(); // random number generator
        int d;                   // number generated
        int temp;

        for (int i = arr.length; i > 1; i--)
        {
            // generate random number
            d = r.nextInt(i);

            // swap a[d] and a[i - 1]
            temp = arr[d];
            arr[d] = arr[i - 1];
            arr[i - 1] = temp;
        }
    }

    private class GameTileListener implements ActionListener
    {
        // class constants

        // class fields
        int numButton;  // button number
        java.util.Timer timer;
        int first;
        int second;

        public GameTileListener(int i)
        {
            numButton = i;

            timer = new java.util.Timer();
        } 
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // local constants
            // local variables
            JButton sourceTile = (JButton) e.getSource(); // tile clicked

            sourceTile.setText(new Integer(answers[numButton]).toString());
            sourceTile.setEnabled(false);
    
            // add tile to selected tiles array
            tilesSelected[numTilesSelected] = numButton;

            // increment number of tiles selected
            numTilesSelected++;
            
            if (numTilesSelected % 2 == 0)
            {
                // first tile selected by current player
                first = tilesSelected[numTilesSelected - 1];

                // second tile selected by current player
                second = tilesSelected[numTilesSelected - 2];

                // do they match?
                if (answers[first] == answers[second])
                {
                    // add to current player's score
                    players[currentPlayer % players.length].modifyScore(10);

                }
                else
                {
                  
                    // disable all tiles
                    for (int i = 0; i < tiles.length; i++)
                    {
                        tiles[i].setEnabled(false);
                    }
                    timer.schedule(new TimerTask() {
                        public void run()
                        {
                            tiles[first].setEnabled(true);
                            tiles[first].setText("");

                            tiles[second].setEnabled(true);
                            tiles[second].setText("");

                            numTilesSelected -= 2;

                            // re-enable
                            for (int i = 0; i < tiles.length; i++)
                            {
                                tiles[i].setEnabled(true);
                            }

                            for (int i = 0; i < numTilesSelected; i++)
                            {
                                tiles[tilesSelected[i]].setEnabled(false);
                            }
                        }
                    }, 1000);

                }

                // advance to next player
                currentPlayer += 1;

                // print out scores
                System.out.println("Current scores, ");
                for (Player p: players)
                {
                    System.out.println("\t" + p.getScore());
                }
            }

        }
    }
}
