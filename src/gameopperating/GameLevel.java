package gameopperating;

import animations.Animation;
import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import ballattributes.Velocity;
import collidables.Collidable;
import ballattributes.Ball;
import collidables.Block;
import collidables.Paddle;
import geometricshapes.Point;
import geometricshapes.Rectangle;
import indicators.LivesIndicator;
import indicators.ScoreIndicator;
import levels.LevelInformation;
import levels.NameOfLevel;
import listeners.HitListener;
import listeners.ScoreTrackingListener;
import removers.BallRemover;
import removers.BlockRemover;
import sprites.Sprite;
import sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;


/**
 * class that represents the game itself.
 */
public class GameLevel implements HitListener, Animation {
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter scoreCounter;
    private Counter livesCounter;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * constructor.
     *
     * @param levelInformation is the level info.
     * @param ky               is the keyboard.
     * @param runner           is the animation runner.
     * @param lives            the amount of lives for the game.
     * @param score            the starting score of the game.
     */
    public GameLevel(LevelInformation levelInformation,
                     KeyboardSensor ky, AnimationRunner runner, Counter lives, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.ballCounter = new Counter(0);
        this.blockCounter = new Counter(0);
        this.scoreCounter = score;
        this.livesCounter = lives;
        this.keyboard = ky;
        this.runner = runner;
        this.levelInformation = levelInformation;
    }

    /**
     * getter.
     *
     * @return the block counter.
     */
    public Counter getBlockCounter() {
        return this.blockCounter;
    }

    /**
     * getter.
     *
     * @return the ball counter.
     */
    public Counter getBallCounter() {
        return this.ballCounter;
    }

    /**
     * the method add collide object to the game environment.
     *
     * @param c the collidable we want to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * the method add sprite object to the sprite collection.
     *
     * @param s the sprite to be added.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)and add them to the game.
     */
    public void initialize() {
        //adding the background to the game
        this.addSprite(this.levelInformation.getBackground());
        //creating the blockRemover;
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        //creating ballRemover
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        //creating a score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreCounter);
        scoreIndicator.addToGame(this);
        //creating a lives indicator
        LivesIndicator livesIndicator = new LivesIndicator(livesCounter);
        livesIndicator.addToGame(this);
        //creating a score tracking listener
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.scoreCounter);
        //creating a sprite that will write the name of the level.
        NameOfLevel nameOfLevel = new NameOfLevel(this.levelInformation.levelName());
        nameOfLevel.addToGame(this);
        //creating the blocks for the game
        this.frameBlocksCreator(ballRemover);
        this.gameBlocksCreator(blockRemover, scoreTrackingListener);
    }

    /**
     * a method deciding wether the loop should stop or not.
     *
     * @return true if should stop, otherwise false.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * summons the runner and runs one go for the game(till a life ends).
     */
    public void playOneTurn() {
        //creating the game paddle
        Paddle paddleGame = new Paddle(new Rectangle(400 - this.levelInformation.paddleWidth() / 2, 560,
                this.levelInformation.paddleWidth(), 20),
                Color.ORANGE, this.keyboard, 20, 780, this.levelInformation.paddleSpeed());
        paddleGame.addToGame(this);
        //creating all balls
        this.ballsCreator();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        paddleGame.removeFromGame(this);
    }

    /**
     * does one frame for the game.
     *
     * @param d  a given surface to run on.
     * @param dt the game time counter.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        //if all blocks have been removed.
        if (this.blockCounter.getValue() == 0) {
            this.scoreCounter.increase(100);
            this.running = false;
        }
        //if the balls have finished decrease lives
        if (this.ballCounter.getValue() == 0) {
            this.livesCounter.decrease(1);
            this.running = false;
        }
        //if the player has paused
        if (this.keyboard.isPressed("p")) {
            KeyPressStoppableAnimation k = new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen());
            this.runner.run(k);
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
    }

    /**
     * removes a given collidable from the sprite list.
     *
     * @param c the collidable to be removed.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * removes a given sprite from the sprite list.
     *
     * @param s the sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit is the block being hit.
     * @param hitter   parameter is the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this);
    }

    /**
     * creates the frame blocks.
     *
     * @param ballRemover the death block needs to know the ball remover.
     *                    will remove balls when hits death block.
     */
    public void frameBlocksCreator(BallRemover ballRemover) {
        //initializing the frame blocks
        Block frameBlockUp = new Block(0, 20, 800, 20, Color.GRAY, -1);
        frameBlockUp.addToGame(this);
        Block frameBlockLeft = new Block(0, 40, 20, 580, Color.GRAY, -1);
        frameBlockLeft.addToGame(this);
        Block frameBlockRight = new Block(780, 40, 20, 580, Color.GRAY, -1);
        frameBlockRight.addToGame(this);
        //creating the death block
        Block deathBlock = new Block(0, 600, 800, 20, Color.GRAY, -1);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(ballRemover);
    }

    /**
     * adding the blocks as part of the game.
     *
     * @param blockRemover          removes blocks that have been hit.
     * @param scoreTrackingListener keeps score of the player.
     */
    public void gameBlocksCreator(HitListener blockRemover, HitListener scoreTrackingListener) {
        for (Block b : this.levelInformation.blocks()) {
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrackingListener);
            this.blockCounter.increase(1);
        }
    }

    /**
     * creates the balls for the game.
     */
    public void ballsCreator() {
        for (Velocity v : this.levelInformation.initialBallVelocities()) {
            Ball ball = new Ball(new Point(400, 550), 7, Color.white);
            //ball.setRandomColor();
            ball.setVelocity(v);
            ball.addToGame(this);
            ball.setGame(this.environment);
        }
        this.ballCounter.increase(this.levelInformation.numberOfBalls());
    }
}
