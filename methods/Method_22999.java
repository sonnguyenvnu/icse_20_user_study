/** 
 * Same as newJMenuItem(), but adds the ALT (on Linux and Windows) or OPTION (on Mac OS X) key as a modifier. This function should almost never be used, because it's bad for non-US keyboards that use ALT in strange and wondrous ways.
 */
static public JMenuItem newJMenuItemAlt(String title,int what){
  JMenuItem menuItem=new JMenuItem(title);
  menuItem.setAccelerator(KeyStroke.getKeyStroke(what,SHORTCUT_ALT_KEY_MASK));
  return menuItem;
}
