package collidables;

import ballattributes.Velocity;
import ballattributes.Ball;
import geometricshapes.Point;
import geometricshapes.Rectangle;

/**
 * interface for objects that can be collide with.
 */
public interface Collidable {
    /**
     * return the "collision shape" of the object.
     *
     * @return the collision shape.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object we collided with it at collisionPoint with a given velocity.
     *
     * @param hitter is the ball hitting.
     * @param collisionPoint  the collision point.
     * @param currentVelocity the current velocity of the object we collided with.
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
