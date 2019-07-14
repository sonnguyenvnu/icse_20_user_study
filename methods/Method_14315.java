synchronized public void undoRedo(long lastDoneEntryID){
  if (lastDoneEntryID == 0) {
    undo(_pastEntries.size());
  }
 else {
    for (int i=0; i < _pastEntries.size(); i++) {
      if (_pastEntries.get(i).id == lastDoneEntryID) {
        undo(_pastEntries.size() - i - 1);
        return;
      }
    }
    for (int i=0; i < _futureEntries.size(); i++) {
      if (_futureEntries.get(i).id == lastDoneEntryID) {
        redo(i + 1);
        return;
      }
    }
  }
}
