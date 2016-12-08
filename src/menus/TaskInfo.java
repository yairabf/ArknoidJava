package menus;

import java.nio.file.Path;

/**
 * a class that holds all information for menu returnMessages.
 *
 * @param <T> a general type.
 */
public class TaskInfo<T> {
    private String key;
    private String name;
    private T task;
    private Path taskPath;

    /**
     * constructor.
     *
     * @param k the key to start the task.
     * @param m the message to be displayed.
     * @param t the task.
     */
    public TaskInfo(String k, String m, T t) {
        this.key = k;
        this.name = m;
        this.task = t;
    }

    /**
     * constructor.
     *
     * @param k the key to start the task.
     * @param m the message to be displayed.
     * @param t the task.
     * @param p the path of the task.
     */
    public TaskInfo(String k, String m, T t, Path p) {
        this.key = k;
        this.name = m;
        this.task = t;
        this.taskPath = p;
    }

    /**
     * getter.
     *
     * @return the key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * getter.
     *
     * @return the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * turns the task in to a string for example (s)start game.
     *
     * @return the name to be displayed as a string.
     */
    public String toString() {
        return "(" + this.key + ")" + this.name;
    }

    /**
     * getter.
     *
     * @return the task.
     */
    public T getTask() {
        return this.task;
    }
}
