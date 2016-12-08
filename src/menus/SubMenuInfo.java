package menus;


/**
 * a class that holds information for a sub menu.
 *
 * @param <T> general type.
 */
public class SubMenuInfo<T> {
    private String key;
    private String name;
    private Menu<T> subMenu;

    /**
     * constructor.
     *
     * @param k the key that summons the sub menu.
     * @param n the name to be displayed at the top of the menu.
     * @param m the submenu class itself.
     */
    public SubMenuInfo(String k, String n, Menu<T> m) {
        this.key = k;
        this.name = n;
        this.subMenu = m;
    }

    /**
     * getter.
     *
     * @return this key
     */
    public String getKey() {
        return key;
    }

    /**
     * getter.
     *
     * @return this name.
     */
    public String getName() {
        return name;
    }

    /**
     * getter.
     *
     * @return the actual sub menu.
     */
    public Menu<T> getSubMenu() {
        return subMenu;
    }
}
