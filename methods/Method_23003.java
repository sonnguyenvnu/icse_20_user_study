/** 
 * As setMenuMnemonics(JMenuItem...).
 */
static public void setMenuMnemonics(JMenuBar menubar){
  JMenuItem[] items=new JMenuItem[menubar.getMenuCount()];
  for (int i=0; i < items.length; i++) {
    items[i]=menubar.getMenu(i);
  }
  setMenuMnemonics(items);
}
