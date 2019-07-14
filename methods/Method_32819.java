/** 
 * Hook system menu to show context menu instead. 
 */
@Override public boolean onCreateOptionsMenu(Menu menu){
  mTerminalView.showContextMenu();
  return false;
}
