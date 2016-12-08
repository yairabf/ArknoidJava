package levels;

import collidables.Block;
import geometricshapes.Point;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * Class the holds all the value a block need to be created.
 */
public class BlockCreatorFromValues implements BlockCreator {
    private int width;
    private int height;
    private List<Color> fillColors = new ArrayList<>();
    private List<Image> images = new ArrayList<>();
    private int hitPoints;
    private Color stroke;
    private String symbol;

    /**
     * getter for the width.
     *
     * @return the width of the future block.
     */
    public int getWidth() {
        return width;
    }

    /**
     * setter for the width.
     *
     * @param width1 the width we want to set.
     */
    public void setWidth(int width1) {
        this.width = width1;
    }

    /**
     * getter for the height.
     *
     * @return the height of the future block.
     */
    public int getHeight() {
        return height;
    }

    /**
     * setter for the height.
     *
     * @param height1 the height we want to set.
     */
    public void setHeight(int height1) {
        this.height = height1;
    }

    /**
     * getter for the stroke.
     *
     * @return the stroke of the block.
     */
    public Color getStroke() {
        return stroke;
    }

    /**
     * getter for the block's colors.
     *
     * @return the block's colors
     */
    public List<Color> getFillColors() {
        return fillColors;
    }


    /**
     * getter for the block's images.
     *
     * @return the block's images.
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * getter fot the block's hit points.
     *
     * @return the block's hit points.
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * setter fot the block's stroke.
     *
     * @param stroke1 the stroke we want to set.
     */
    public void setStroke(Color stroke1) {
        this.stroke = stroke1;
    }

    /**
     * setter for the block's hit points.
     *
     * @param hitPoints1 the hit point we want to set.
     */
    public void setHitPoints(int hitPoints1) {
        this.hitPoints = hitPoints1;
    }

    /**
     * setter for the block's symbol.
     *
     * @param symbol1 the symbol we want to set.
     */
    public void setSymbol(String symbol1) {
        this.symbol = symbol1;
    }

    /**
     * getter for the block's symbol.
     *
     * @return the block's symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @param xpos the x value of the top left corner of the block.
     * @param ypos the y value of the top left corner of the block.
     * @return new block built from all the parameters we have in the current object in the location of x an y value we
     * sent.
     */
    @Override
    public Block create(int xpos, int ypos) {
        Point topLeft = new Point(xpos, ypos);
        Block block = new Block(topLeft, this.width, this.height, this.fillColors, this.stroke, this.hitPoints);
        if (this.getImages() != null) {
            for (Image image : this.getImages()) {
                block.setImage(image);
            }
        }
        return block;
    }
}
