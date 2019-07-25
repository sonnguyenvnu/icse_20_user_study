/** 
 * Uninstalls menu item model change listener from specified menu item.
 * @param listener listener to uninstall
 * @param menuItem menu item to uninstall listener from
 */
public static void uninstall(final MenuItemChangeListener listener,final JMenuItem menuItem){
  menuItem.getModel().removeChangeListener(listener);
}
