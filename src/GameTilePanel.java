import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.*;

public class GameTilePanel extends JPanel
{
    // class fields
    JButton[] tiles;  // tiles used for games
    DefaultListModel playerLabels;  // list of player labels
    ArrayList<Integer> tilesSelected; // tiles selected
    int[] answers;          // number answers DEBUGGING ONLY
    Player[] players;       // player objects carrying player state
    int currentTurn;      // current turn number
 //   int avgAttempts;      // average attempts taken by user in single player
 //   int sumAttempts;       // sum of attempts taken in single player mode
    HistoryPanel historyPanel;  // panel that keeps track of history state
    JLabel statusText;
    ImageIcon[] img = new ImageIcon[16];

    public GameTilePanel()
    {
        // local constants
        // local variables
        JPanel tilePanel = new JPanel();    // panel for tiles
        JList<String> scoreList = new JList<>();   // panel used for player scores

        // initialize list model
        playerLabels = new DefaultListModel();

        // initalize tile array
        tiles = new JButton[16];

        // set layout
        setLayout(new BorderLayout());


        // set layout for tiles
        tilePanel.setLayout(new GridLayout(4, 4));

        // loops for buttons
        for (int i = 0; i < tiles.length; i++)
        {
            // initalize tile button
            tiles[i] = new JButton();
            tiles[i].setDisabledIcon(new ImageIcon("null"));
            // disable all buttons
            tiles[i].setEnabled(false);

            // add listener
            tiles[i].addActionListener(new GameTileListener(i));

            // add to pane
            tilePanel.add(tiles[i]);
        }

        // initalize answer array
        answers = new int[16];
        for (int i = 0; i < answers.length; i += 2)
        {
            answers[i] = i / 2 + 1;
            answers[i + 1] = i / 2 + 1;

            img[i] = new ImageIcon(( i / 2 + 1) + ".jpg");
            img[i+1] = new ImageIcon(( i / 2 + 1) + ".jpg");
        }



        // add tile panel
        add(tilePanel, BorderLayout.CENTER);

        // add score panel
        scoreList.setModel(playerLabels);
        add(scoreList, BorderLayout.LINE_START);

    }

    public void setHistoryObj(HistoryPanel hPanel)
    {
        historyPanel = hPanel;
    }

    public void setStatusObj(JLabel status)
    {
        statusText = status;
    }


    // initalizes game state
    public void initGame(int numPlayers, boolean randomize)
    {
        // initialize players array
        players = new Player[numPlayers];
        tilesSelected = new ArrayList<>();
        statusText.setText("Player 1's turn");

        // clear players score list
        playerLabels.removeAllElements();

        // initalize all players
        for (int i = 0; i < players.length; i++)
        {
            // initialize player object
            players[i] = new Player();

            // add player score label
            playerLabels.addElement(String.format("Player %d - 0", i + 1));
        }


        // permute answer array
        if (randomize)
            permuteArray(answers,img);

        // set current player to first player
        currentTurn = 0;

        // enable all of the buttons
        for (int i = 0; i < tiles.length; i++)
        {
            tiles[i].setText("");
            tiles[i].setIcon(new ImageIcon("null"));
            tiles[i].setEnabled(true);
        }
    }


    private void permuteArray(int[] arr,ImageIcon[] img)
    {
        // local constants
        // local variables
        Random r = new Random(); // random number generator
        int d;                   // number generated
        int temp;
        ImageIcon tmpImg;

        for (int i = arr.length; i > 1; i--)
        {
            // generate random number
            d = r.nextInt(i);

            // swap a[d] and a[i - 1]
            temp = arr[d];
            arr[d] = arr[i - 1];
            arr[i - 1] = temp;

            tmpImg = img [d];
            img [d] = img [i - 1];
            img[i-1] = tmpImg;
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
            int currPlayer;
            int maxPlayerIndex = 0;

            //sourceTile.setText(new Integer(answers[numButton]).toString());
            //sourceTile.setIcon(img[numButton]);
            sourceTile.setDisabledIcon(img[numButton]);
            sourceTile.setEnabled(false);

            // add tile to selected tiles array
            tilesSelected.add(numButton);

            if (tilesSelected.size() % 2 == 0)
            {

                statusText.setText("Player " +
                        (((currentTurn+1) % players.length) + 1) + "'s turn");

                // if we are in single player
                if (players.length == 1)
                {
                    players[currentTurn % players.length].modifyScore(1);

                    // update current players score
                    playerLabels.setElementAt(String.format("Player 1 - %d",
                            players[0].getScore()), 0);
                }

                // first tile selected by current player
                first = tilesSelected.get(tilesSelected.size() - 1);

                // second tile selected by current player
                second = tilesSelected.get(tilesSelected.size() - 2);

                // do they match?
                if (answers[first] == answers[second])
                {
                    // add to current player's score

                    if (players.length > 1)
                    {
                        players[currentTurn % players.length].modifyScore(10);
                    }
                    currPlayer = currentTurn % players.length;


                    // is there a winner?
                    if (tilesSelected.size() == 16)
                    {
                        // is this a single player game?
                        if (players.length == 1)
                        {
                            // single player things
                            historyPanel.addSinglePlayerGame(currentTurn + 1);
                        }
                        else
                        {
                            boolean isTie = true;

                            // find player with highest score
                            for (int i = 1; i < players.length; i++)
                            {
                                if (players[i].getScore() > players[maxPlayerIndex].getScore())
                                {
                                    isTie = false;
                                    maxPlayerIndex = i;
                                } // end if
                            } // end for

                            if (!isTie)
                            {
                                players[maxPlayerIndex].modifyScore(25);
                                // update current players score
                                playerLabels.setElementAt(String.format("Player %d - %d",
                                        maxPlayerIndex + 1,
                                        players[maxPlayerIndex].getScore()),
                                        maxPlayerIndex);

                                historyPanel.addPlayerWin(maxPlayerIndex + 1);
                                statusText.setText("Player " + (maxPlayerIndex + 1) + " is the winner!");
                            }
                            else
                            {
                                statusText.setText("Woah! A tie!");
                            }
                        } // end if
                    }
                    // update current players score
                    playerLabels.setElementAt(String.format("Player %d - %d",
                            currPlayer + 1,
                            players[currPlayer].getScore()),
                            currPlayer);
                }
                else
                {

                    // disable all tiles
                    System.out.println("disabled");
                    for (int i = 0; i < tiles.length; i++)
                    {
                        tiles[i].setEnabled(false);
                    }
                    timer.schedule(new TimerTask() {
                        public void run()
                        {
                            tiles[first].setEnabled(true);
                            tiles[first].setText("");
                            tiles[first].setDisabledIcon(new ImageIcon("null"));

                            tiles[second].setEnabled(true);
                            tiles[second].setText("");
                            tiles[second].setDisabledIcon(new ImageIcon("null"));


                            // re-enable
                            for (int i = 0; i < tiles.length; i++)
                            {
                                tiles[i].setEnabled(true);
                            }

                            tilesSelected.remove(tilesSelected.size() - 1);
                            tilesSelected.remove(tilesSelected.size() - 1);

                            for (int i = 0; i < tilesSelected.size(); i++)
                            {
                                tiles[tilesSelected.get(i)].setEnabled(false);
                            }
                        }
                    }, 1000);

                } // end if

                // advance to next player
                currentTurn += 1;

            }
        }
    }
}
