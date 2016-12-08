package menus;

import animations.AnimationRunner;
import biuoop.KeyboardSensor;
import gameopperating.GameFlow;
import levels.LevelSpecificationReader;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;


/**
 * a class that return a task with the run method.
 * this method will be created from a file and will activate gameflow.
 */
public class RunFromFileTask implements Task<Void> {
    //private LineNumberReader lineNumberReader;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private File scoresFile;
    private String levelInfo;

    /**
     * constructor.
     *
     * @param ar        the animation runner.
     * @param ks        the keyboard sensor.
     * @param scores    the scores file.
     * @param levelinfo the level info as a string(the path for the level info).
     */
    public RunFromFileTask(AnimationRunner ar, KeyboardSensor ks, File scores, String levelinfo) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.scoresFile = scores;
        this.levelInfo = levelinfo;
    }

    /**
     * runs the task.
     *
     * @return void.
     */
    @Override
    public Void run() {
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.levelInfo);
            GameFlow game = new GameFlow(this.animationRunner, this.keyboardSensor, this.scoresFile);
            LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
            game.runLevels(levelSpecificationReader.fromReader(new InputStreamReader(is)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("there has been a problem with gameflow/addscore/save");
        }
        return null;
    }
}
