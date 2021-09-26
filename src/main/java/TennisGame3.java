
public class TennisGame3 implements TennisGame {
    
    private int scorePlayer2;
    private int scorePlayer1;
    private String player1Name;
    private String player2Name;

    public TennisGame3(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore() {
        if (getIfEqual()) {
            return getEqualityScore();
        }
        if (isEquals()) return "Deuce";
        return getScoreAdvantage();
    }

    private boolean isEquals() {
        return scorePlayer1 == scorePlayer2;
    }

    private boolean sumIsDifferentFromSix() {
        return scorePlayer1 + scorePlayer2 != 6;
    }

    private boolean isOlderThanFour() {
        return scorePlayer1 < 4 && scorePlayer2 < 4;
    }

    private boolean getIfEqual() {
        return isOlderThanFour() && sumIsDifferentFromSix();
    }

    private String getScoreAdvantage() {
        String score;
        score = setScoreByPlayer();
        return getAdvantageCondition() ? "Advantage " + score : "Win for " + score;
    }

    private boolean getAdvantageCondition() {
        return Math.pow((scorePlayer1 - scorePlayer2),2) == 1;
    }

    private String setScoreByPlayer() {
        return scorePlayer1 > scorePlayer2 ? player1Name : player2Name;
    }

    private String getEqualityScore() {
        String score;
        String[] options = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
        score = options[scorePlayer1];
        return (scorePlayer1 == scorePlayer2) ? score + "-All" : score + "-" + options[scorePlayer2];
    }


    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            this.scorePlayer1 += 1;
        else
            this.scorePlayer2 += 1;
        
    }

}
