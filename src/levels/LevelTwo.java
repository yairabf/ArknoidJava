package levels;

import ballattributes.Ball;
import ballattributes.Velocity;
import collidables.Block;
import geometricshapes.Line;
import geometricshapes.Point;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * level two of the game.
 */
public class LevelTwo implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed((i * 15) + 181, 300);
            ballsVelocity.add(velocity);
        }
        return ballsVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 150;
    }

    @Override
    public int paddleWidth() {
        return 650;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        //Background background = new Background();
        SpriteCollection sprites = new SpriteCollection();
        sprites.addSprite(new Block(0, 25, 800, 600, Color.WHITE, -1));
        for (int i = 1; i < 1000; i = i + 10) {
            Line line = new Line(new Point(140, 140), new Point(40 + i, 240));
            line.setColor(new Color(0xFFFC00));
            sprites.addSprite(line);
        }
        sprites.addSprite(new Ball(140, 140, 60, Color.getHSBColor(235, 235, 167)));
        sprites.addSprite(new Ball(140, 140, 50, Color.orange));
        sprites.addSprite(new Ball(140, 140, 40, Color.YELLOW));
        return new BackgroundCreator(sprites);
    }

    @Override
    public List<Block> blocks() {
        int blockWidth = 50;
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Block block1 = new Block(25 + (blockWidth * i), 240, blockWidth, 20, Color.red, 1);
            blocks.add(block1);
        }
        for (int i = 2; i < 4; i++) {
            Block block1 = new Block(25 + (blockWidth * i), 240, blockWidth, 20, Color.ORANGE, 1);
            blocks.add(block1);
        }
        for (int i = 4; i < 6; i++) {
            Block block1 = new Block(25 + (blockWidth * i), 240, blockWidth, 20, new Color(0xFEFF00), 1);
            blocks.add(block1);
        }
        for (int i = 6; i < 9; i++) {
            Block block1 = new Block(25 + (blockWidth * i), 240, blockWidth, 20, Color.green, 1);
            blocks.add(block1);
        }
        for (int i = 9; i < 11; i++) {
            Block block1 = new Block(25 + (blockWidth * i), 240, blockWidth, 20, Color.blue, 1);
            blocks.add(block1);
        }
        for (int i = 11; i < 13; i++) {
            Block block1 = new Block(25 + (blockWidth * i), 240, blockWidth, 20, Color.pink, 1);
            blocks.add(block1);
        }
        for (int i = 13; i < 15; i++) {
            Block block1 = new Block(25 + (blockWidth * i), 240, blockWidth, 20, Color.cyan, 1);
            blocks.add(block1);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
