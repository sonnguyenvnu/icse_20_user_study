synchronized public List<HistoryEntry> getLastPastEntries(int count){
  if (count <= 0) {
    return new LinkedList<HistoryEntry>(_pastEntries);
  }
 else {
    return _pastEntries.subList(Math.max(_pastEntries.size() - count,0),_pastEntries.size());
  }
}
