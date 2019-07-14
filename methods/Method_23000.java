static public JCheckBoxMenuItem newJCheckBoxMenuItem(String title,int what){
  JCheckBoxMenuItem menuItem=new JCheckBoxMenuItem(title);
  int modifiers=awtToolkit.getMenuShortcutKeyMask();
  menuItem.setAccelerator(KeyStroke.getKeyStroke(what,modifiers));
  return menuItem;
}
