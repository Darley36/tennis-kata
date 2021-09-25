import java.lang.reflect.Array;
import java.util.List;
import java.util.Arrays;

public class TennisGame2 implements TennisGame
{
    public int scorePlayer1 = 0;
    public int scorePlayer2 = 0;
    public String player1Name = "";
    public String player2Name = "";

    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore(){
        String score = "";
        score = searchForEquals(score);

        if (scorePlayer1 > 0 && scorePlayer2 ==0)
        {
            player1Name = playerPosition(scorePlayer1);

            score = getBothScores(player1Name,"Love");
        }
        if (scorePlayer2 > 0 && scorePlayer1 ==0)
        {
            player2Name = playerPosition(scorePlayer2);
            score = getBothScores("Love",player2Name);
        }
        
        if (scorePlayer1 > scorePlayer2 && scorePlayer1 < 4)
        {
            player1Name=playerPosition(scorePlayer1);
            player2Name=playerPosition(scorePlayer2);
            score = player1Name + "-" + player2Name;
        }
        if (scorePlayer2 > scorePlayer1 && scorePlayer2 < 4)
        {
            player1Name=playerPosition(scorePlayer1);
            player2Name=playerPosition(scorePlayer2);
            score = player1Name + "-" + player2Name;
        }
        if(scoreGreatherThanFour()) {
            int minusResult = scorePlayer1-scorePlayer2;
            //getAdvantage(minusResult);
            if (scorePlayer1 > scorePlayer2 ) {
                score = "Advantage player1";
            }

            if (scorePlayer2 > scorePlayer1 ) {
                score = "Advantage player2";
            }

            if ((scorePlayer1 - scorePlayer2) >= 2) {
                score = "Win for player1";
            }
            if ((scorePlayer2 - scorePlayer1) >= 2) {
                score = "Win for player2";
            }
        }
        return score;
    }

    private String searchForEquals(String score) {
        if (returnConditions() && scorePlayer1 < 4)
        {
            score = getEquality(scorePlayer1);
        }
        if (returnConditions() && scorePlayer1 >=3){
            score = getEquality(3);
        }
        return score;
    }

    private boolean scoreGreatherThanFour(){
        return scorePlayer1 >= 4 || scorePlayer2 >=4;
    }

    private String getAdvantage(int minusResult){
        String score="";
        if(minusResult == 1){
            score = "Advantage player1";
        }
        return score;
    }

    private boolean returnConditions(){
        if(scorePlayer1 == scorePlayer2) return true;
        return false;
    }

    private String getBothScores(String player1,String player2) {
        player1Name = player1;
        player2Name = player2;
        return player1Name + "-" + player2Name;
    }

    private String playerPosition(int Player) {
        if (Player < 4) {
            List<String> score = Arrays.asList("Love","Fifteen","Thirty","Forty");
            return score.get(Player);
        }
        return  null;
    }

    private String getEquality(int scorePlayer1) {
        String[] equalityOptions = {"Love-All","Fifteen-All","Thirty-All","Deuce"};
        return equalityOptions[scorePlayer1];
    }

    public void SetP1Score(int number){
        for (int i = 0; i < number; i++)
        {
            scorePlayer1++;
        }
            
    }
    
    public void SetP2Score(int number){
        
        for (int i = 0; i < number; i++)
        {
            scorePlayer2++;
        }
            
    }

    public void wonPoint(String player) {
        if (player.equals(player1Name))
            scorePlayer1++;
        else
            scorePlayer2++;
    }
}