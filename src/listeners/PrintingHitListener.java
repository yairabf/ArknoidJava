package listeners;

import ballattributes.Ball;
import collidables.Block;

/**
 * a listener that prints hits on blocks.
 */
public class PrintingHitListener implements HitListener {
    /**
     * prints the amount of hits on a block once summoned.
     * @param beingHit is the block being hit.
     * @param hitter   parameter is the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}
