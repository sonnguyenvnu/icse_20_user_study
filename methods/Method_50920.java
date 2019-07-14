@Override public void addedFile(int fileCount,File file){
  tokenizingFilesBar.setMaximum(fileCount);
  tokenizingFilesBar.setValue(tokenizingFilesBar.getValue() + 1);
}
