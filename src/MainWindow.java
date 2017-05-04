import javax.swing.*;

public class MainWindow 
{
    public static void main(String... args)
    {
        // local constants
        // local variables
        JFrame window = new JFrame(); // main window
        GameTilePanel gamePanel = new GameTilePanel(); // game tile panel

        // set title
        window.setTitle("Tile Game");
        
        // set window size 
        window.setSize(400, 400);

        // set window close operation
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        window.getContentPane().add(gamePanel);
        
        // show main window
        window.setVisible(true);
    }
}
