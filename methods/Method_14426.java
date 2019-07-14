@Override public void loadChange(HistoryEntry historyEntry){
  File changeFile=getChangeFile(historyEntry);
  try {
    loadChange(historyEntry,changeFile);
  }
 catch (  Exception e) {
    throw new RuntimeException("Failed to load change file " + changeFile.getAbsolutePath(),e);
  }
}
