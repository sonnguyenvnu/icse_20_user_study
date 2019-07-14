/** 
 * Create a menu item and set its KeyStroke by name (so it can be stored in the language settings or the preferences. Syntax is here: https://docs.oracle.com/javase/8/docs/api/javax/swing/KeyStroke.html#getKeyStroke-java.lang.String-
 * @param sequence the name, as outlined by the KeyStroke API
 * @param fallback what to use if getKeyStroke() comes back null
 */
static public JMenuItem newJMenuItemExt(String base){
  JMenuItem menuItem=new JMenuItem(Language.text(base));
  KeyStroke ks=getKeyStrokeExt(base);
  if (ks != null) {
    menuItem.setAccelerator(ks);
  }
  return menuItem;
}
