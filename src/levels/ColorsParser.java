package levels;



import java.awt.Color;

/**
 * Class that gets a string information an create color from it.
 */
public class ColorsParser {

    /**
     * @param s the name of the color.
     * @return color.
     */
    public java.awt.Color colorFromString(String s) {
        if (s.contains("RGB")) {
            String[] value = s.split("[()]");
            String[] rgb = value[2].split(",");
            float[] a = new float[3];
            a = Color.RGBtoHSB(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]), a);
            return Color.getHSBColor(a[0], a[1], a[2]);
        } else {
            String color = s.split("[()]")[1];
            Color color1;
            switch (color) {
                case "black":
                    color1 = Color.black;
                    break;
                case "cyan":
                    color1 = Color.cyan;
                    break;
                case "blue":
                    color1 = Color.blue;
                    break;
                case "gray":
                    color1 = Color.gray;
                    break;
                case "lightGray":
                    color1 = Color.lightGray;
                    break;
                case "green":
                    color1 = Color.green;
                    break;
                case "orange":
                    color1 = Color.orange;
                    break;
                case "pink":
                    color1 = Color.pink;
                    break;
                case "red":
                    color1 = Color.red;
                    break;
                case "white":
                    color1 = Color.white;
                    break;
                case "yellow":
                    color1 = Color.yellow;
                    break;
                default:
                    color1 = Color.gray;
            }
            return color1;
        }
    }
}
