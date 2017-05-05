
// Player class - stores current scores of players

public class Player
{
    // class constants
    // class fields
    int score;          // current score of player
    public Player()
    {
        score = 0;
    }

    public int getScore()
    {
        // local constants
        // local variables
        return score;
    }

    public int modifyScore(int dscore)
    {
        score += dscore;
        return score;
    }
}
