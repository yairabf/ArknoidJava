package ballattributes;

/**
 * An object class that holds the limits of a rectangle on x and y axis.
 */
public class Limits {
    private int lLimit;
    private int width;
    private int height;
    private int bottom;

    /**
     * constructor.
     *
     * @param height the height limit.
     * @param width  the right limit.
     * @param lLimit the left limit.
     * @param bottom the bottom limit.
     */
    public Limits(int height, int width, int lLimit, int bottom) {
        this.lLimit = Math.abs(lLimit);
        this.width = Math.abs(width);
        this.height = Math.abs(height);
        this.bottom = Math.abs(bottom);
    }

    /**
     * returns the height.
     *
     * @return the height variable.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * returns the left limit.
     *
     * @return the lLimit variable.
     */
    public int getlLimit() {
        return this.lLimit;
    }

    /**
     * returns the right limit.
     *
     * @return the width variable.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * returns the bottom limit.
     *
     * @return the bottom variable.
     */
    public int getBottom() {
        return this.bottom;
    }
}