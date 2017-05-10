/**
 * Created by Shao yu on 2017/5/4.
 */
import java.awt.*;
import javax.swing.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
public class HistoryPanel extends JPanel
{
    // class constants
    // class fields
    Map<Integer, Integer> playerHistory;    // player history table 
    List<Integer> attemptsTaken;            // attempts taken by player in each single player game
    JLabel attemptsLabel;
    JLabel avgLabel;
    JLabel bestPlayer;

    //-----------------------------------------------------------------
    //  Sets up this panel with two labels.
    //-----------------------------------------------------------------
    public HistoryPanel()
    {
        // local constants
        // local variables
        

        // initialize hash map
        playerHistory = new HashMap<>();

        // initialize attempts list
        attemptsTaken = new ArrayList<>();

        // set layout
        setLayout(new GridLayout(10, 1));

        // add single player label
        add(new JLabel("Single Player Statistics"));

        // add attempts taken label
        attemptsLabel = new JLabel("Games played: ");
        add(attemptsLabel);

        // add average attempts label
        avgLabel = new JLabel("Average number of attempts: ");
        add(avgLabel);

        // add multiplayer label
        add(new JLabel("Multiplayer statistics: "));

        // add best player label
        bestPlayer = new JLabel("Best player so far: ");
        add(bestPlayer);

        setBackground(new Color(130, 210, 225));
    }

    public void clearHistoryState()
    {
        // reset all state
        playerHistory.clear();
        attemptsTaken.clear();

        // reset labels
        attemptsLabel.setText("Games played:");
        avgLabel.setText("Average number of attempts");
        bestPlayer.setText("Best player so far: ");

    }

    public void addPlayerWin(int index)
    {
        if (!playerHistory.containsKey(index))
        {
            playerHistory.put(index, 1);
        }
        else
        {
            int wins = playerHistory.get(index);
            playerHistory.replace(index, wins + 1);
        }

        Map.Entry<Integer, Integer> maxEntry = null;
        for (Map.Entry<Integer, Integer> entry : playerHistory.entrySet())
        {
            if (maxEntry == null || maxEntry.getValue() < entry.getValue())
                maxEntry = entry;
        }
        bestPlayer.setText("Best player so far: " +
                "Player " + maxEntry.getKey() +
                " - " + maxEntry.getValue());


    }

    public void addSinglePlayerGame(int attempts)
    {
        attemptsTaken.add(attempts);
        attemptsLabel.setText("Games played: " + attemptsTaken.size());
        avgLabel.setText("Average number of attempts: " +
                attemptsTaken.stream()
                    .mapToInt(i -> i)
                    .average()
                    .orElse(0));
    }
}
