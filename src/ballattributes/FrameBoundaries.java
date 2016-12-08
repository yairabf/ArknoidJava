package ballattributes;

import geometricshapes.Point;

/**
 * class that represent frame boundaries.
 */
public class FrameBoundaries {
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int width;
    private int height;

    /**
     * constructor.
     *
     * @param startX the x value of the start point of the frame.
     * @param startY the y value of the start point of the frame.
     * @param endX   the x value of the and point of the frame.
     * @param endY   the y value of the and point of the frame.
     */
    public FrameBoundaries(int startX, int startY, int endX, int endY) {
        this.startX = Math.abs(startX);
        this.startY = Math.abs(startY);
        this.endX = Math.abs(endX);
        this.endY = Math.abs(endY);
        this.width = endX - startX;
        this.height = endY - startY;
    }

    /***
     * second constructor by two point.
     *
     * @param startPoint the start point of the frame.
     * @param endPoint   the end point of the frame.
     */
    public FrameBoundaries(Point startPoint, Point endPoint) {
        this.startX = Math.abs((int) startPoint.getX());
        this.startY = Math.abs((int) startPoint.getY());
        this.endX = Math.abs((int) endPoint.getX());
        this.endY = Math.abs((int) endPoint.getY());
        this.width = this.endX - this.startX;
        this.height = this.endY - this.startY;
    }

    /**
     * getter for the x value of the start point.
     *
     * @return the x value of the start point.
     */
    public int getStartX() {
        return startX;
    }

    /**
     * setter for the x value of the start point.
     *
     * @param x the x value we set.
     */
    public void setStartX(int x) {
        this.startX = x;
    }

    /**
     * getter for the y value of the start point.
     *
     * @return the y value of the start point.
     */
    public int getStartY() {
        return startY;
    }

    /**
     * setter for the y value of the start point.
     *
     * @param y the y value we set.
     */
    public void setStartY(int y) {
        this.startY = Math.abs(y);
    }

    /**
     * getter for the x value of the end point.
     *
     * @return the x value of the end point.
     */
    public int getEndX() {
        return this.endX;
    }

    /**
     * setter for the x value of the end point.
     *
     * @param x the x value we set.
     */
    public void setEndX(int x) {
        this.endX = Math.abs(x);
    }

    /**
     * getter for the y value of the end point.
     *
     * @return the y value of the end point.
     */
    public int getEndY() {
        return this.endY;
    }

    /**
     * setter for the y value of the end point.
     *
     * @param y the y value we set.
     */
    public void setEndY(int y) {
        this.endY = Math.abs(y);
    }

    /**
     * getter for the width of the frame.
     *
     * @return the width of the frame.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * getter for the height of the frame.
     *
     * @return the height of the frame.
     */
    public int getHeight() {
        return this.height;
    }
}
