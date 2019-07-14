public void addRecentFile(File file){
  recentFiles.remove(file);
  recentFiles.add(0,file);
  if (recentFiles.size() > Constants.MAX_RECENT_FILES) {
    recentFiles.remove(Constants.MAX_RECENT_FILES);
  }
}
