package menus;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * a class for the starting menu.
 *
 * @param <T> a general type.
 */
public class MenuAnimation<T> implements Menu<T> {
    private T status;
    private String name;
    private KeyboardSensor keyboardSensor;
    private boolean stop;
    private boolean isPressed;
    private List<TaskInfo<T>> tasks = new ArrayList<>();
    private List<SubMenuInfo<T>> subMenus = new ArrayList<>();

    /**
     * constructor.
     *
     * @param n  the name.
     * @param ks the keyboard sensor.
     */
    public MenuAnimation(String n, KeyboardSensor ks) {
        this.name = n;
        this.keyboardSensor = ks;
    }

    /**
     * adds an option to the menu.
     *
     * @param key  the key that stops.
     * @param n    the message to be displayed.
     * @param task returns a value of type Task to perform.
     */
    @Override
    public void addSelection(String key, String n, T task) {
        TaskInfo<T> selection = new TaskInfo<>(key, n, task);
        this.tasks.add(selection);
    }

    /**
     * adds a sub menu to the menu.
     *
     * @param key     the key that stops the animation.
     * @param message the message to be displayed.
     * @param subMenu the sub menu to be added.
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        SubMenuInfo<T> s = new SubMenuInfo<>(key, message, subMenu);
        this.subMenus.add(s);
    }

    /**
     * runs a frame of the animation.
     *
     * @param d  a given surface to run on.
     * @param dt the time that takes to do the operation.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.stop = false;
        //the name of the menu
        d.drawText(250, 80, this.name, 40);
        d.setColor(Color.cyan);
        d.drawText(250, 82, this.name, 40);
        int lineCount = 0;
        //writes down all options
        for (TaskInfo taskInfo : this.tasks) {
            d.drawText(250, 130 + lineCount, taskInfo.toString(), 30);
            lineCount += 50;
        }
        //finds out what key has been pressed
        for (TaskInfo<T> taskInfo : this.tasks) {
            if (keyboardSensor.isPressed(taskInfo.getKey())) {
                //updates the status accordingly
                this.status = taskInfo.getTask();
                this.stop = true;
                break;
            }
        }
    }

    /**
     * getter.
     *
     * @return the status.
     */
    @Override
    public T getStatus() {
        return this.status;
    }

    /**
     * a method deciding whether the loop should stop or not.
     *
     * @return true if should stop, otherwise false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
