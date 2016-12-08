package gameopperating;


import animations.Animation;
import animations.AnimationRunner;
import animations.HighScoresTable;
import animations.KeyPressStoppableAnimation;
import animations.EndScreen;
import animations.HighScoresAnimation;
import animations.ScoreInfo;
import biuoop.DialogManager;
import levels.LevelInformation;
import biuoop.KeyboardSensor;

import java.io.File;
import java.util.List;


/**
 * a class that creates a flow to the game.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private HighScoresTable highScoresTable;
    private File file;

    /**
     * constructor.
     *
     * @param ar the animation runner.
     * @param ks the keyboard sensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, File file) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.file = file;
    }

    /**
     * creates a game and runs all levels of a given list.
     *
     * @param levels is the list of levels.
     * @throws Exception in case there is a problem in saving/loading the scores file.
     */
    public void runLevels(List<LevelInformation> levels) throws Exception {
        Counter scoreCounter = new Counter(0);
        Counter livesCounter = new Counter(7);
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, livesCounter, scoreCounter);
            level.initialize();
            //while there are lives and blocks left play a turn.
            while (livesCounter.getValue() > 0 && level.getBlockCounter().getValue() > 0) {
                level.playOneTurn();
            }
            //if there are no lives left.
            if (livesCounter.getValue() == 0) {
                break;
            }
        }
        String message;
        String message2;
        if (livesCounter.getValue() == 0) {
            message = "Game Over. Your score is " + scoreCounter.getValue();
            message2 = " ";
        } else {
            message = "You Win! Your score is " + scoreCounter.getValue();
            message2 = "press space to exit";
        }
        Animation endScreen = new KeyPressStoppableAnimation(keyboardSensor, "space",
                new EndScreen(message, message2));
        this.animationRunner.run(endScreen);
        this.addScore(scoreCounter);
        Animation scoreTable = new KeyPressStoppableAnimation(keyboardSensor, "space",
                new HighScoresAnimation(this.highScoresTable));
        this.animationRunner.run(scoreTable);
    }

    /**
     * the method adding score to the table score.
     *
     * @param score the score we want to add.
     * @throws Exception in case exception been thrown on the loading/saving of the file that holds the score table.
     */
    private void addScore(Counter score) throws Exception {
        if (!this.file.exists()) {
            this.highScoresTable = new HighScoresTable(8);
        } else {
            this.highScoresTable = HighScoresTable.loadFromFile(this.file);
        }
        DialogManager dialog = animationRunner.getGui().getDialogManager();
        String name = dialog.showQuestionDialog("Name", "What is your name?", "");
        ScoreInfo scoreInfo = new ScoreInfo(name, score.getValue());
        this.highScoresTable.add(scoreInfo);
        this.highScoresTable.save(this.file);
    }
}
