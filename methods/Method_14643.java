@Override public HistoryEntry performImmediate() throws Exception {
  if (_historyEntry == null) {
    _historyEntry=createHistoryEntry(HistoryEntry.allocateID());
  }
  _project.history.addEntry(_historyEntry);
  _done=true;
  return _historyEntry;
}
