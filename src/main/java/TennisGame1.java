import java.util.List;
import java.util.Vector;

public class TennisGame1 implements TennisGame {
    
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            scorePlayer1 += 1;
        else
            scorePlayer2 += 1;
    }

    public String getScore() {
        String score = "";
        Vector<Boolean> options = new Vector<>();
        options.add(scorePlayer1 == scorePlayer2);
        options.add(scorePlayer1 >=4 || scorePlayer2 >=4);
        options.add(true);
        score = getOptionScore(options.indexOf(true), score);

        return score;
    }

    private String getOptionScore(int i, String score){
        switch (i){
            case 0:
                return getEqualityValidated();
            case 1:
                return getValidatedAdvantage();
            case 2:
                return getAllScore(score);
        }
        return "";
    }

    private String getAllScore(String score){
        for (int i=1; i<3; i++)
        {
            score = getResult(score, i);
        }
        return score;
    }

    private String getEqualityValidated() {
        String[] alternatives = {"Love-All","Fifteen-All","Thirty-All","Deuce"};
        if (scorePlayer1<3) return alternatives[scorePlayer1];
        return "Deuce";
    }

    private String getValidatedAdvantage() {
        int minusResult = scorePlayer1 - scorePlayer2;
        return getMessageByMinusResult(getListOfBooleans(minusResult));
    }

    private int getListOfBooleans(int minusResult) {
        Vector<Boolean> conditions = new Vector<>();
        conditions.add(minusResult ==1);
        conditions.add(minusResult ==-1);
        conditions.add(minusResult >=2);
        conditions.add(minusResult <=2);
        return conditions.indexOf(true);
    }

    private String getMessageByMinusResult(int option){
        String[] alternatives = {"Advantage "+player1Name,"Advantage "+player2Name,"Win for player1","Win for player2"};
        return alternatives[option];

    }

    private String getResult(String score, int i) {
        int tempScore;
        if (i ==1) tempScore = scorePlayer1;
        else { score +="-"; tempScore = scorePlayer2;}
        return getScoreResult(score, tempScore);
    }

    private String getScoreResult(String score, int tempScore) {
        String[] alternatives = {"Love","Fifteen","Thirty","Forty"};
        return score.concat(alternatives[tempScore]);
    }
}
