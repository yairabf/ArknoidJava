package animations;

import java.io.Serializable;

/**
 * Class that represent a score information.
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * constructor that receives name of the user and it score.
     *
     * @param name  the player's name.
     * @param score the player's score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * getter for the player's name.
     *
     * @return player's name;
     */
    public String getName() {
        return this.name;
    }

    /**
     * getter for the player's score.
     *
     * @return player's score.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * the method return string with the name of the player in his score.
     *
     * @return string with the name of the player in his score.
     */
    public String toString() {
        return this.name + ": " + this.score;
    }
}