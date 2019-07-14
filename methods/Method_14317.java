protected void redo(int times){
  Project project=ProjectManager.singleton.getProject(_projectID);
  while (times > 0 && _futureEntries.size() > 0) {
    HistoryEntry entry=_futureEntries.get(0);
    entry.apply(project);
    setModified();
    times--;
    _pastEntries.add(entry);
    _futureEntries.remove(0);
  }
}
