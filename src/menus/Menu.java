package menus;


import animations.Animation;

/**
 * an interface for menus.
 *
 * @param <T> is a generic type.
 */
public interface Menu<T> extends Animation {
    /**
     * adds an option to the menu.
     *
     * @param key  the key that stops the animation..
     * @param name the message to be displayed.
     * @param task returns a value of type Task to perform.
     */
    void addSelection(String key, String name, T task);

    /**
     * returns the status of the menu screen.
     *
     * @return the status.
     */
    T getStatus();

    /**
     * adds a sub menu to the menu.
     *
     * @param key     the key that stops the animation.
     * @param message the message to be displayed.
     * @param subMenu the sub menu to be added.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
