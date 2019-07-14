/** 
 * Like newJMenuItem() but adds shift as a modifier for the shortcut.
 */
static public JMenuItem newJMenuItemShift(String title,int what){
  JMenuItem menuItem=new JMenuItem(title);
  int modifiers=awtToolkit.getMenuShortcutKeyMask();
  modifiers|=ActionEvent.SHIFT_MASK;
  menuItem.setAccelerator(KeyStroke.getKeyStroke(what,modifiers));
  return menuItem;
}
