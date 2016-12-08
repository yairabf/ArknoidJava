package listeners;

import ballattributes.Ball;
import collidables.Block;
import gameopperating.Counter;

/**
 * a class that listens to the game and updates the score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     * @param scoreCounter the score to start with.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * performs hit event and updates the score.
     * @param beingHit is the block being hit.
     * @param hitter   parameter is the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.hasReachedZeroHits()) {
            this.currentScore.increase(10);
        } else {
            this.currentScore.increase(5);
        }
    }
}
