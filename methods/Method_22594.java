public JMenu getToolbarMenu(){
  if (toolbarMenu == null) {
    rebuildToolbarMenu();
  }
  return toolbarMenu;
}
