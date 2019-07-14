/** 
 * Calls setMenuMnemonics(JMenuItem...) on the sub-elements only.
 */
static public void setMenuMnemsInside(JMenu menu){
  JMenuItem[] items=new JMenuItem[menu.getItemCount()];
  for (int i=0; i < items.length; i++) {
    items[i]=menu.getItem(i);
  }
  setMenuMnemonics(items);
}
