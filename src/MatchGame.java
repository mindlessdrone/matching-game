import javax.swing.*;

public class MatchGame
{
    //-----------------------------------------------------------------
    //  Sets up a frame containing a tabbed pane. The panel on each
    //  tab demonstrates a different layout manager.
    //-----------------------------------------------------------------
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Match Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane tp = new JTabbedPane();
        tp.addTab("Rules", new RulePanel());
        tp.addTab("Single Player", new SinglePlayerPanel());
        tp.addTab("Double Players", new DoublePlayersPanel());
        tp.addTab("History", new HistoryPanel());

        frame.getContentPane().add(tp);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
