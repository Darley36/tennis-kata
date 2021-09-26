import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class TennisGame2 implements TennisGame
{
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore(){
        String score = "";
        score = searchForEquals(score);
        List<Boolean> playerOptions = getPlayerOptions();
        score = getPlayerOneScore(score, playerOptions);
        score = getPlayerTwoScore(score, playerOptions);
        score = getEqualityByPlayerOne(score, playerOptions);
        score = getEqualityByPlayerTwo(score, playerOptions);
        if(scoreGreaterThanFour()) {
            int minusResult = scorePlayer1-scorePlayer2;
            score = getAdvantage(score, resultOption(minusResult));
        }
        return score;
    }

    private String getEqualityByPlayerTwo(String score, List<Boolean> playerOptions) {
        if (playerOptions.get(1) && playerOptions.get(3))
        {
            player1Name=playerPosition(scorePlayer1);
            player2Name=playerPosition(scorePlayer2);
            score = player1Name + "-" + player2Name;
        }
        return score;
    }

    private String getEqualityByPlayerOne(String score, List<Boolean> playerOptions) {
        if (playerOptions.get(0) && playerOptions.get(2))
        {
            player1Name=playerPosition(scorePlayer1);
            player2Name=playerPosition(scorePlayer2);
            score = player1Name + "-" + player2Name;
        }
        return score;
    }

    private String getPlayerTwoScore(String score, List<Boolean> playerOptions) {
        if (playerOptions.get(6) && playerOptions.get(7))
        {
            player2Name = playerPosition(scorePlayer2);
            score = getBothScores("Love",player2Name);
        }
        return score;
    }

    private String getPlayerOneScore(String score, List<Boolean> playerOptions) {
        if (playerOptions.get(4) && playerOptions.get(5))
        {
            player1Name = playerPosition(scorePlayer1);

            score = getBothScores(player1Name,"Love");
        }
        return score;
    }

    private List<Boolean> getPlayerOptions() {
        List<Boolean> playerOptions = new ArrayList<>();
        playerOptions.add(scorePlayer1 > scorePlayer2);
        playerOptions.add(scorePlayer2 > scorePlayer1);
        playerOptions.add(scorePlayer1 < 4);
        playerOptions.add(scorePlayer2 < 4);
        playerOptions.add(scorePlayer1 > 0);
        playerOptions.add(scorePlayer2 == 0);
        playerOptions.add(scorePlayer2 > 0);
        playerOptions.add(scorePlayer1 == 0);
        return playerOptions;
    }

    private String getAdvantage(String score, int option) {
        String[] messageOptions = {"Advantage "+player1Name,"Advantage "+player2Name,"Win for player1","Win for player2"};
        if (option == -1) {
            return score;
        }
        return messageOptions[option];
    }

    private int resultOption(int minusResult){
        List<Boolean> minusResultOptions = Arrays.asList(minusResult == 1,minusResult == -1,minusResult >= 2,minusResult <= -2);
        return minusResultOptions.indexOf(true);
    }

    private String searchForEquals(String score) {
        if (returnConditionsEquals())
        {
            score = getEquality(scorePlayer1);
        }
        return score;
    }

    private boolean scoreGreaterThanFour(){
        return scorePlayer1 >= 4 || scorePlayer2 >=4;
    }

    private boolean returnConditionsEquals(){
        if(scorePlayer1 == scorePlayer2) return true;
        return false;
    }

    private String getBothScores(String player1,String player2) {
        player1Name = player1;
        player2Name = player2;
        return player1Name + "-" + player2Name;
    }

    private String playerPosition(int player) {
        if (player < 4) {
            List<String> score = Arrays.asList("Love","Fifteen","Thirty","Forty");
            return score.get(player);
        }
        return  null;
    }

    private String getEquality(int scorePlayer1) {
        String[] equalityOptions = {"Love-All","Fifteen-All","Thirty-All","Deuce"};
        if(scorePlayer1 < 3) return equalityOptions[scorePlayer1];
        return "Deuce";
    }

    public void wonPoint(String player) {
        if (player.equals(player1Name))
            scorePlayer1++;
        else
            scorePlayer2++;
    }
}