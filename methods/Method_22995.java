/** 
 * A software engineer, somewhere, needs to have their abstraction taken away. Who crafts the sort of API that would require a five-line helper function just to set the shortcut key for a menu item?
 */
static public JMenuItem newJMenuItem(String title,int what){
  JMenuItem menuItem=new JMenuItem(title);
  int modifiers=awtToolkit.getMenuShortcutKeyMask();
  menuItem.setAccelerator(KeyStroke.getKeyStroke(what,modifiers));
  return menuItem;
}
