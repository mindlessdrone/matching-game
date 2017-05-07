import java.awt.*;
import javax.swing.*;

public class RulePanel extends JPanel
{
    public RulePanel()
    {
        setBackground(new Color(130, 210, 225));
        setPreferredSize(new Dimension(600, 600));
    }

    public void paintComponent(Graphics page)
    {
        super.paintComponent(page);

        //Intro
        page.setColor(new Color(185, 160, 215));
        page.fillRect(20,20,560,80);

        page.setColor(Color.BLACK);
        page.setFont(new Font("Arial", Font.BOLD, 15));
        page.drawString("Introduction: This is finding a matching pictures game, which it will have two", 35, 50);
        page.drawString("choices - single or double player, under set of different rules.", 130, 75);

        //Single Player
        page.setColor(new Color(185, 160, 215));
        page.fillRect(20,120,560,190);

        page.setColor(Color.BLACK);
        page.setFont(new Font("Arial", Font.BOLD, 14));
        page.drawString("Single-Player-Rules:", 35, 150);

        page.setFont(new Font("Arial", Font.PLAIN, 13));
        page.drawString("To start, you have to fill your name and then select 'New game'. The system will", 100, 180);
        page.drawString("show the tries and number of the game you played on the bottom. 'New game' will keep", 50, 205);
        page.drawString("tracking your score. Select 'Clear game' to give up the current game, it won't count in your", 50, 230);
        page.drawString("score. 'Reset game' will clean all the data then record your score and average of attempts", 50, 255);
        page.drawString("into History, it only save the top 3 highest scores. 'End game' will shut down the system.", 50, 280);

        //Double Players
        page.setColor(new Color(185, 160, 215));
        page.fillRect(20,330,560,190);

        page.setColor(Color.BLACK);
        page.setFont(new Font("Arial", Font.BOLD, 14));
        page.drawString("Double-Players-Rules:", 35, 360);

        page.setFont(new Font("Arial", Font.PLAIN, 13));
        page.drawString("To start, you have to fill your names and then select 'New Game'. Once a person", 100, 390);
        page.drawString("and take turns. 10 points for a match, 25 points for getting more matches than another", 50, 415);
        page.drawString("in the end of the game. Same as rule of single player,  you can choose to 'Clear game',", 50, 440);
        page.drawString("'Reset game', and 'End game'. The two player's scores will be recorded to History which", 50, 465);
        page.drawString("will save the 3 games recently played.", 50, 490);

        //Producer
        page.setColor(new Color(185, 160, 215));
        page.fillRect(20,540,560,40);

        page.setColor(Color.BLACK);
        page.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        page.drawString("Produced by: Anthony, Shao yu", 340, 565);
    }
}