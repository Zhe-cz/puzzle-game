package lab.game.model;

/**
 * Player Score
 */
public class Score {

    private int score_id;
    private String player;
    private int scores;


    public Score(int score_id, String player, int scores) {
        this.score_id = score_id;
        this.player = player;
        this.scores = scores;
    }

    public String getPlayer() {
        return player;
    }

    public int getScore_id() {
        return score_id;
    }


    public int getScores() {
        return scores;
    }


}
