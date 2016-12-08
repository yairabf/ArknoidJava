package collidables;

import ballattributes.Ball;
import ballattributes.Velocity;
import gameopperating.GameLevel;
import geometricshapes.Line;
import geometricshapes.Point;
import geometricshapes.Rectangle;
import sprites.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * this class creates a paddle for the game.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle rect;
    private Color color;
    private Line[] segments;
    private double leftLimit;
    private double rightLimit;
    private double speed;

    /**
     * constructor.
     *
     * @param rect     the rectangle.
     * @param color    the color of the paddle/
     * @param keyboard the keyboard to control the paddle.
     * @param l        the left limit of the paddle.
     * @param r        the right limit.
     * @param s        the speed of the paddle.
     */
    public Paddle(Rectangle rect, Color color, KeyboardSensor keyboard, double l, double r, double s) {
        //super(rect, color);
        this.rect = rect;
        this.color = color;
        this.keyboard = keyboard;
        this.segments = new Line[5];
        this.segmentInitialization();
        this.leftLimit = l;
        this.rightLimit = r;
        this.speed = s;
    }

    /**
     * initializes all segments so that the paddle will know where it hit.
     */
    private void segmentInitialization() {
        double size = this.rect.getWidth() / 5;
        for (int i = 0; i < segments.length; i++) {
            //sets 5 new lines for 5 different segments and stores them in the array.
            Point start = new Point(this.rect.getUpperLeft().getX() + (i * size), this.rect.getUpperLeft().getY());
            Point end = new Point(this.rect.getUpperLeft().getX() + ((i + 1) * size), this.rect.getUpperLeft().getY());
            Line segment = new Line(start, end);
            segments[i] = segment;
        }
    }

    /**
     * moves the paddle left.
     *
     * @param dt the game time counter.
     */
    private void moveLeft(double dt) {
        double newX = this.rect.getUpperLeft().getX() - (this.speed * dt);
        if (inBoundaries(newX)) {
            this.rect.setTopLeftX(newX);
        } else {
            this.rect.setTopLeft(new Point(this.leftLimit, this.rect.getUpperLeft().getY()));
        }
    }

    /**
     * moves the paddle right.
     *
     * @param dt the game time counter.
     */
    private void moveRight(double dt) {
        double newX = this.rect.getUpperLeft().getX() + (this.speed * dt);
        if (inBoundaries(newX)) {
            this.rect.setTopLeftX(newX);
        } else {
            this.rect.setTopLeft(new Point(this.rightLimit - this.rect.getWidth(), this.rect.getUpperLeft().getY()));
        }
    }

    /**
     * checks if the paddle is in the boundries.
     *
     * @param x the x variable to be checked.
     * @return true if in boundaries else false.
     */
    private boolean inBoundaries(double x) {
        return (x + this.rect.getWidth() <= this.rightLimit && x >= leftLimit);
    }

    /**
     * activates time passed for paddle.moves the paddle accordingly.
     *
     * @param dt the game time counter.
     */
    @Override
    public void timePassed(double dt) {
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            this.moveLeft(dt);
        } else if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            this.moveRight(dt);
        }
    }

    /**
     * draws the paddle on a gives surface.
     *
     * @param d the drawsurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    /**
     * returns the shape.
     *
     * @return the shape rectangle of object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * returns the new velocity of the object that hit the paddle.
     *
     * @param collisionPoint  the collision point.
     * @param currentVelocity the current velocity of the object we collided with.
     * @param hitter          the hitting ball.
     * @return returns the new velocity.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //if the ball didnt hit the top surface
        this.segmentInitialization();
        if (collisionPoint.getY() != this.rect.getUpperLeft().getY()) {
            if (collisionPoint.getX() == rect.getLeftLine().start().getX()
                    || collisionPoint.getX() == rect.getRightLine().start().getX()) {
                //update the 'x' value of the velocity.
                currentVelocity = new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
                return currentVelocity;
            }
        }
        //if the ball hit the top surface
        int segment = whichSegment(collisionPoint);
        if (segment == 2) {
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
        }
        //sets the new angle according to request, maintains the ball speed as initialized in game class.
        double newSpeed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        currentVelocity = Velocity.fromAngleAndSpeed((210 + 30 * segment), newSpeed);
        return currentVelocity;
    }

    /**
     * returns the segment where the ball hit.
     *
     * @param collisionPoint the point of collision.
     * @return the number of the segment.
     */
    private int whichSegment(Point collisionPoint) {
        int i;
        double x = Math.round(collisionPoint.getX());
        for (i = 0; i < segments.length; i++) {
            //if we have found the segment that collided with the collision point.
            if (x >= Math.round(segments[i].start().getX()) && x < Math.round(segments[i].end().getX())) {
                break;
            }
        }
        return i;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game to be added to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * removes the paddle from the game.
     *
     * @param g the game the paddle should be removed from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }
}
