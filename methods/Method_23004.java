/** 
 * As setMenuMnemonics(JMenuItem...).
 */
static public void setMenuMnemonics(JPopupMenu menu){
  ArrayList<JMenuItem> items=new ArrayList<>();
  for (  Component c : menu.getComponents()) {
    if (c instanceof JMenuItem)     items.add((JMenuItem)c);
  }
  setMenuMnemonics(items.toArray(new JMenuItem[items.size()]));
}
