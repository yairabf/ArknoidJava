package levels;

import ballattributes.Velocity;
import collidables.Block;
import sprites.Sprite;

import java.util.List;

/**
 * Class that represent an object that hold the level information.
 */
public class LevelInformationBySpecification implements LevelInformation{
    private String levelName;
    private List<Velocity> ballsVelocity;
    private BackgroundCreator background;
    private int paddleSeed;
    private int paddleWidth;
    private List<Block> blocks;
    private int startX;
    private int startY;
    private int rowHeight;
    private int numberOfBlocks;

    /**
     *
     * @param levelName
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     *
     * @param ballsVelocity
     */
    public void setBallsVelocity(List<Velocity> ballsVelocity) {
        this.ballsVelocity = ballsVelocity;
    }

    /**
     *
     * @param background
     */
    public void setBackground(BackgroundCreator background) {
        this.background = background;
    }

    /**
     *
     * @param paddleSeed
     */
    public void setPaddleSeed(int paddleSeed) {
        this.paddleSeed = paddleSeed;
    }

    /**
     *
     * @param paddleWidth
     */
    public void setPaddleWidth(int paddleWidth) {
        this.paddleWidth = paddleWidth;
    }

    /**
     *
     * @param blocks
     */
    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    /**
     *
     * @param startX
     */
    public void setStartX(int startX) {
        this.startX = startX;
    }

    /**
     *
     * @param startY
     */
    public void setStartY(int startY) {
        this.startY = startY;
    }

    /**
     *
     * @param rowHeight
     */
    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }

    /**
     *
     * @param numberOfBlocks
     */
    public void setNumberOfBlocks(int numberOfBlocks) {
        this.numberOfBlocks = numberOfBlocks;
    }

    /**
     *
     * @return
     */
    @Override
    public int numberOfBalls() {
            return this.ballsVelocity.size();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballsVelocity;
    }

    /**
     *
     * @return
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSeed;
    }

    /**
     *
     * @return
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     *
     * @return
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     *
     * @return
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     *
     * @return
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocks;
    }

    /**
     *
     * @return
     */
    public int getStartX() {
        return startX;
    }

    /**
     *
     * @return
     */
    public int getStartY() {
        return startY;
    }

    /**
     *
     * @return
     */
    public int getRowHeight() {
        return rowHeight;
    }
}
