package removers;

import ballattributes.Ball;
import collidables.Block;
import gameopperating.Counter;
import gameopperating.GameLevel;
import listeners.HitListener;

/**
 * a class that helps the game class by removing it's balls when needed.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param g the game this class removes blocks for.
     * @param c how many balls to start with.
     */
    public BallRemover(GameLevel g, Counter c) {
        this.game = g;
        this.remainingBalls = c;
    }

    /**
     * Blocks that are hit and reach 0 hit-points will be removed.
     *
     * @param beingHit is the death block being hit.
     * @param hitter   is the hitting ball.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
