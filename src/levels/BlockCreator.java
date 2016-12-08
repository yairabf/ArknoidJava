package levels;

import collidables.Block;

/**
 * Class that represent a ball creator.
 */
public interface BlockCreator {

    /**
     * Create a block at the specified location.
     *
     * @param xpos the x value of the top left corner of the block.
     * @param ypos the y value of the top left corner of the block.
     * @return new block.
     */
     Block create(int xpos, int ypos);
}
