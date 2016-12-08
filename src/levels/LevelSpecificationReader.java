package levels;

import ballattributes.Velocity;
import collidables.Block;
import sprites.ImageBackground;
import sprites.SpriteCollection;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.Reader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represent an object that help the game reads level's information from a file.
 */
public class LevelSpecificationReader {
    private List<LevelInformation> levels = new ArrayList<>();


    /**
     * The method reads all the levels informatics from a file.
     *
     * @param reader the file contains all the information about the game's levels.
     * @return list with all the game's level.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        //string that will hold the lines we read from the file.
        String line;
        BufferedReader bf = null;
        BlocksFromSymbolsFactory blocksFromSymbol = null;
        /*trying read from file*/
        try {
            bf = new BufferedReader(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*trying make a level information*/
        try {
            /*while there is lines to read*/
            while ((line = bf.readLine()) != null) {
               //line = bf.readLine();
                //a new object of level information.
                /*until we read this line it means that we still loading information for the level.*/
                if (line.equals("START_LEVEL")) {
                    LevelInformationBySpecification currentLevel = new LevelInformationBySpecification();
                    while (!line.equals("END_LEVEL")) {
                    /*checking the type of the information object we loading*/
                        switch (line.split(":")[0]) {
                            case "level_name":
                                currentLevel.setLevelName(line.split(":")[1]);
                                break;
                            case "ball_velocities":
                                List<Velocity> velocities = new ArrayList<>();
                                String[] velocity = line.split(":")[1].split(" ");
                                for (String vel : velocity) {
                                    int velo = Integer.parseInt(vel.split(",")[0]);
                                    int speed = Integer.parseInt(vel.split(",")[1]);
                                    velocities.add(Velocity.fromAngleAndSpeed(velo, speed));
                                }
                                currentLevel.setBallsVelocity(velocities);
                                break;
                            case "background":
                                SpriteCollection spriteCollection = new SpriteCollection();
                                if (line.split(":")[1].startsWith("color")) {
                                    ColorsParser colorsParser = new ColorsParser();
                                    Color color = colorsParser.colorFromString(line.split(":")[1]);
                                    spriteCollection.addSprite(new Block(0, 0, 800, 600, color, -1));
                                } else {
                                    String image = line.split(":")[1].split("[()]")[1];
                                    Image img = null;
                                    try {
                                        img = ImageIO.read(new File(image));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    ImageBackground imgBack = new ImageBackground(img, 0, 0);
                                    spriteCollection.addSprite(imgBack);
                                }
                                BackgroundCreator background = new BackgroundCreator(spriteCollection);
                                currentLevel.setBackground(background);
                                break;
                            case "paddle_speed":
                                currentLevel.setPaddleSeed(Integer.parseInt(line.split(":")[1]));
                                break;
                            case "paddle_width":
                                currentLevel.setPaddleWidth(Integer.parseInt(line.split(":")[1]));
                                break;
                            case "block_definitions":
                                String file = line.split(":")[1];
                                Reader s = new FileReader(file);
                                blocksFromSymbol = BlocksDefinitionReader.fromReader(s);
                                break;
                            case "blocks_start_x":
                                currentLevel.setStartX(Integer.parseInt(line.split(":")[1]));
                                break;
                            case "blocks_start_y":
                                currentLevel.setStartY(Integer.parseInt(line.split(":")[1]));
                                break;
                            case "row_height":
                                currentLevel.setRowHeight(Integer.parseInt(line.split(":")[1]));
                                break;
                            case "num_blocks":
                                currentLevel.setNumberOfBlocks(Integer.parseInt(line.split(":")[1]));
                                break;
                            case "START_BLOCKS":
                                line = bf.readLine();
                                List<Block> blocks = new ArrayList<>();
                                int startX = currentLevel.getStartX();
                                if (blocksFromSymbol != null) {
                                    while (!line.equals(("END_BLOCKS"))) {
                                        char[] charLine = line.toCharArray();
                                        currentLevel.setStartX(startX);
                                        for (char symbol : charLine) {
                                            String sym = Character.toString(symbol);
                                            if (blocksFromSymbol.isSpaceSymbol(sym)) {
                                                currentLevel.setStartX(currentLevel.getStartX()
                                                        + blocksFromSymbol.getSpaceWidth(sym));
                                            } else if (blocksFromSymbol.isBlockSymbol(sym)) {
                                                Block block = blocksFromSymbol.getBlock(sym, currentLevel.getStartX(),
                                                        currentLevel.getStartY());
                                                blocks.add(block);
                                                currentLevel.setStartX(currentLevel.getStartX()
                                                        + (int) block.getRect().getWidth());
                                            }
                                        }
                                        currentLevel.setStartY(currentLevel.getStartY() + currentLevel.getRowHeight());
                                        line = bf.readLine();
                                    }
                                }
                                currentLevel.setBlocks(blocks);
                                break;
                            default:
                                break;
                        }
                        line = bf.readLine();
                    }
                    this.levels.add(currentLevel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.levels;
    }

}
