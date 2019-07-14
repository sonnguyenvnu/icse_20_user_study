public void insertToolbarRecentMenu(){
  if (toolbarMenu == null) {
    rebuildToolbarMenu();
  }
 else {
    toolbarMenu.insert(Recent.getToolbarMenu(),1);
  }
}
