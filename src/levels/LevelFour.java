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
import java.util.Random;

/**
 * level four of the game.
 */
public class LevelFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        for (int i = 1; i < 6; i = i + 2) {
            Velocity velocity = Velocity.fromAngleAndSpeed(i * 47, 300);
            ballsVelocity.add(velocity);
        }
        return ballsVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 200;
    }

    @Override
    public int paddleWidth() {
        return 70;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Random rand = new Random();
        SpriteCollection sprites = new SpriteCollection();
        //BackgroundCreator background = new BackgroundCreator();
        float[] hsb = {204, 61, 96};
        float[] hsb1 = Color.RGBtoHSB(95, 185, 245, hsb);
        sprites.addSprite(new Block(0, 25, 800, 600,
                Color.getHSBColor(hsb1[0], hsb1[1], hsb1[2]), -1));
        for (int j = 1; j < 141; j = j + 10) {
            Line rain1 = new Line(new Point(60 + j, 370), new Point(20 + j, 600));
            rain1.setColor(Color.WHITE);
            sprites.addSprite(rain1);
            Line rain2 = new Line(new Point(530 + j, 370), new Point(470 + j, 600));
            rain2.setColor(Color.WHITE);
            sprites.addSprite(rain2);
        }
        for (int i = 0; i < 3; i++) {
            Ball cloud1 = new Ball(85 + (50 * i), 355, 45, Color.lightGray);
            sprites.addSprite(cloud1);
            Ball cloud2 = new Ball(555 + (50 * i), 355, 45, Color.lightGray);
            sprites.addSprite(cloud2);
        }
        for (int i = 0; i < 3; i++) {
            Ball cloud1 = new Ball(120 + (50 * i), 315, 45, Color.lightGray);
            sprites.addSprite(cloud1);
            Ball cloud2 = new Ball(600 + (50 * i), 315, 45, Color.lightGray);
            sprites.addSprite(cloud2);
        }

        return new BackgroundCreator(sprites);
    }

    @Override
    public List<Block> blocks() {
        int blockWidth = 50;
        int blockHeight = 20;
        List<Block> blocks = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            for (int j = 0; j < 15; j++) {
                Color color;
                switch (i) {
                    case 6:
                        color = Color.gray;
                        break;
                    case 5:
                        color = Color.red;
                        break;
                    case 4:
                        color = Color.yellow;
                        break;
                    case 3:
                        color = Color.green;
                        break;
                    case 2:
                        color = Color.white;
                        break;
                    case 1:
                        color = Color.pink;
                        break;
                    case 0:
                        color = Color.cyan;
                        break;
                    default:
                        color = Color.black;
                }
                Block block = new Block(25 + (blockWidth * j), 100 + (blockHeight * i),
                        blockWidth, blockHeight, color, 7 - i);
                blocks.add(block);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
