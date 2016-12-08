package levels;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.Reader;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class that read block information from text file and saves all the information needed to built the block's game.
 */
public class BlocksDefinitionReader {

    /**
     * @param reader the file with all the details about the levels.
     * @return an object of type "levelInformation" that has all the info we need to built a level.
     */
    public static BlocksFromSymbolsFactory fromReader(Reader reader) {
        //string that will hold the lines we read from the file.
        String line;
        BufferedReader bf = null;
        Map<String, Integer> spacerWidths = new TreeMap<>();
        Map<String, BlockCreator> blockCreators = new TreeMap<>();
        /*trying read from file*/
        try {
            bf = new BufferedReader(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*trying make a level information*/
        try {
            BlockCreatorFromValues block = new BlockCreatorFromValues();
            /*while there is lines to read*/
            while ((line = bf.readLine()) != null) {
                /*set the default values of the blocks.*/
                if (line.startsWith("default")) {
                    String[] values = line.split(" ");
                    block = BlocksDefinitionReader.valueFromLine(values, block);
                    /*set a new type of block*/
                } else if (line.startsWith("bdef")) {
                    String[] values = line.split(" ");
                    BlockCreatorFromValues newBlock = BlocksDefinitionReader.valueFromLine(values, block);
                    blockCreators.put(newBlock.getSymbol(), newBlock);
                    /*set the separator values*/
                } else if (line.startsWith("sdef")) {
                    String[] values = line.split(" ");
                    String symbol = values[1].split(":")[1];
                    int width = Integer.parseInt(values[2].split(":")[1]);
                    spacerWidths.put(symbol, width);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }

    /**
     * the method build an object type of "blackCreator" that will hold all the value we need to build specific block.
     * @param line the line the contain the block's values.
     * @param block the default block that we using to complete missing parameters.
     * @return a new object that hold the values we need to build a block.
     */
    private static BlockCreatorFromValues valueFromLine(String[] line, BlockCreatorFromValues block) {
        //first setting the default values.
        BlockCreatorFromValues block1 = new BlockCreatorFromValues();
        block1.setWidth(block.getWidth());
        block1.setHeight(block.getHeight());
        block1.setHitPoints(block.getHitPoints());
        block1.setStroke(block.getStroke());
        /*reading and setting the distinguish values*/
        for (String value : line) {
            switch (value.split(":")[0]) {
                //setting the block's symbol.
                case "symbol":
                    block1.setSymbol(value.split(":")[1]);
                    break;
                //setting the block's height.
                case "height":
                    block1.setHeight(Integer.parseInt(value.split(":")[1]));
                    break;
                //setting the block's width.
                case "width":
                    block1.setWidth(Integer.parseInt(value.split(":")[1]));
                    break;
                //setting the block's hit point.
                case "hit_points":
                    block1.setHitPoints(Integer.parseInt(value.split(":")[1]));
                    break;
                /*setting the block's color/image.*/
                case "fill":
                    /*setting color*/
                    if (value.split(":")[1].startsWith("color")) {
                        ColorsParser colorsParser1 = new ColorsParser();
                        Color color1 = colorsParser1.colorFromString(value.split(":")[1]);
                        block1.getFillColors().add(color1);
                        break;
                        /*setting image*/
                    } else {
                        String image = value.split(":")[1].split("[()]")[1];
                        Image img = null;
                        try {
                            img = ImageIO.read(new File(image));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        block1.getImages().add(img);
                    }
                    break;
                case "stroke":
                    ColorsParser colorsParser2 = new ColorsParser();
                    Color color2 = colorsParser2.colorFromString(value.split(":")[1]);
                    block1.setStroke(color2);
                    break;
                default:
                    break;
            }
            /*setting the block's changeable color/image.*/
            if (value.startsWith("fill-")) {
                /*in case the color is changing*/
                if (value.split(":")[1].startsWith("color")) {
                    ColorsParser colorsParser = new ColorsParser();
                    Color color = colorsParser.colorFromString(value.split(":")[1]);
                    block1.getFillColors().add(color);
                    /*in case the image is changing*/
                } else {
                    String image = value.split(":")[1].split("[()]")[1];
                    Image img = null;
                    try {
                        img = ImageIO.read(new File(image));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    block1.getImages().add(img);
                }
            }
        }
        return block1;
    }
}
