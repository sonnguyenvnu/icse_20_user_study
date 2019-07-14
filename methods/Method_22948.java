void setCollapsed(boolean newState){
  if (collapsed != newState) {
    collapsed=newState;
    editor.footer.setVisible(!newState);
    splitPane.resetToPreferredSizes();
  }
}
