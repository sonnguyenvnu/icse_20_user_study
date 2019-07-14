public void loadFile(File file){
  if (open)   closeFile();
  this.file=file;
  RecentFiles.add(file.getAbsolutePath());
  mainWindow.mainMenuBar.updateRecentFiles();
  loadTree();
}
