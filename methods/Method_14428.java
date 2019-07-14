protected File getChangeFile(HistoryEntry historyEntry){
  return new File(getHistoryDir(historyEntry),historyEntry.id + ".change.zip");
}
