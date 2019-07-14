public void setSketchbookFolder(File folder){
  sketchbookFolder=folder;
  Preferences.setSketchbookPath(folder.getAbsolutePath());
  rebuildSketchbookMenus();
  makeSketchbookSubfolders();
}
