/** 
 * @param action: use an Action, which sets the title, reactionand enabled-ness all by itself.
 */
static public JMenuItem newJMenuItem(Action action,int what){
  JMenuItem menuItem=new JMenuItem(action);
  int modifiers=awtToolkit.getMenuShortcutKeyMask();
  menuItem.setAccelerator(KeyStroke.getKeyStroke(what,modifiers));
  return menuItem;
}
