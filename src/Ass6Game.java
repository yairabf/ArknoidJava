import animations.AnimationRunner;
import animations.HighScoresTable;
import animations.Animation;
import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import menus.MenuAnimation;
import menus.Task;
import menus.ShowHiScoresTask;
import menus.Menu;
import menus.LineNumberReader;
import menus.RunFromFileTask;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * the class runs the whole game.
 */
public class Ass6Game {
    /**
     * the main method to run the game.
     *
     * @param args the list of levels to run.
     * @throws Exception if only illegal args are given.
     */
    public static void main(String[] args) throws Exception {
        InputStream pathFromUser;
        //the file will be the default one
        //creating gui, animation runner
        GUI gui = new GUI("Arkanoid", 800, 600);
        final KeyboardSensor ks = gui.getKeyboardSensor();
        final AnimationRunner animationRunner = new AnimationRunner(gui);
        //creating a high scores file
        while (true) {
            final File scoresFile = new File("HighScores1.bin");
            HighScoresTable scores = new HighScoresTable(10);
            if (scoresFile.exists()) {
                scores.load(scoresFile);
            } else {
                scores.save(scoresFile);
            }
            Ass6Game ass5Game = new Ass6Game();
            //put levelslist in comment cos might not need it
            if (args.length == 0) {
                pathFromUser = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
                //the file will be the one received from the user in args
            } else {
                pathFromUser = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
            }
            ass5Game.gameMenu(ks, animationRunner, pathFromUser, scores, scoresFile);
        }
    }

    /**
     * this function runs the menu and the tasks accordingly.
     *
     * @param ks              the keyboard sensor.
     * @param animationRunner the animation runner.
     * @param pathFromUser    a path to a file as a string. the file contains instructions for the levels of the game.
     * @param scores          the high scores list.
     * @param scoresFile      the file that holds scores.
     */
    public void gameMenu(final KeyboardSensor ks, final AnimationRunner animationRunner,
                         final InputStream pathFromUser, HighScoresTable scores, File scoresFile) {
        //while (true) {
            //creating the menu animation
            Menu<Task<Void>> menu = new MenuAnimation<>("arkanoid", ks);
            //adding the quit animation
            Task<Void> quit = new Task<Void>() {
                @Override
                public Void run() {
                    System.exit(0);
                    return null;
                }
            };
            menu.addSelection("q", "quit game", quit);
            //adding the high score animation
            Animation scoreTable = new KeyPressStoppableAnimation(ks, "space",
                    new HighScoresAnimation(scores));
            menu.addSelection("h", "high scores", new ShowHiScoresTask(animationRunner, scoreTable));
            Ass6Game ass5Game = new Ass6Game();
            final Menu<Task<Void>> subMenu =
                    ass5Game.createSubMenuFromFile(pathFromUser, ks, scoresFile, animationRunner);
            //adding a sub menu as subMenuInfo
            menu.addSubMenu("s", "select a type of game", subMenu);
            Task<Void> startGame = new Task<Void>() {
                @Override
                public Void run() {
                    animationRunner.run(subMenu);
                    Task<Void> t = subMenu.getStatus();
                    t.run();
                    return null;
                }
            };
            //all start game does is summons the sub menu. but that happens at the menu animation class.
            menu.addSelection("s", "start game", startGame);
                animationRunner.run(menu);
                Task t = menu.getStatus();
                t.run();
       // }
    }

    /**
     * receives a file and creates a sub menu for the game.
     *
     * @param pathFileFromUser a list of what should be in the sub menu.
     * @param ks               the keyboard sensor.
     * @param scoresFile       the high scores file.
     * @param ar               the animation runner.
     * @return a sub menu.
     */
    public Menu<Task<Void>> createSubMenuFromFile(InputStream pathFileFromUser, final KeyboardSensor ks,
                                                  final File scoresFile, final AnimationRunner ar) {
        Menu<Task<Void>> menu = new MenuAnimation<>("select type", ks);
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(pathFileFromUser));
            String line = lineNumberReader.readLine();
            //initializing lists to hold different parts of the options in the sub menu.
            List<String> keysList = new ArrayList<>();
            List<String> messagesList = new ArrayList<>();
            List<Path> filePathsList = new ArrayList<>();
            //while i haven't reached the end of the file
            while (line != null) {
                //if the line consists key and name
                if (lineNumberReader.getLineNumber() % 2 == 0) {
                    String[] parts = line.split(":");
                    //adding the key and name to the matching lists
                    keysList.add(parts[0]);
                    messagesList.add(parts[1]);
                    //the line consists a classpath add it to the path list.
                } else {
                    filePathsList.add(Paths.get(line));
                }
                line = lineNumberReader.readLine();
            }
            //try catch in case the info on the file was incorrect
            try {
                //creating tasks for every option and adding them to the sub menu
                for (int i = 0; i < keysList.size(); i++) {
                    Task<Void> task = new RunFromFileTask(ar, ks, scoresFile, filePathsList.get(i).toString());
                    menu.addSelection(keysList.get(i), messagesList.get(i), task);
                }
            } catch (Exception e) {
                System.out.println("have not received matching number of keys, messages or file paths");
                return null;
            }
        } catch (Exception ioException) {
            System.out.println("the file was not found");
        }
        return menu;
    }
}
