synchronized public long getPrecedingEntryID(long entryID){
  if (entryID == 0) {
    return -1;
  }
 else {
    for (int i=0; i < _pastEntries.size(); i++) {
      if (_pastEntries.get(i).id == entryID) {
        return i == 0 ? 0 : _pastEntries.get(i - 1).id;
      }
    }
    for (int i=0; i < _futureEntries.size(); i++) {
      if (_futureEntries.get(i).id == entryID) {
        if (i > 0) {
          return _futureEntries.get(i - 1).id;
        }
 else         if (_pastEntries.size() > 0) {
          return _pastEntries.get(_pastEntries.size() - 1).id;
        }
 else {
          return 0;
        }
      }
    }
  }
  return -1;
}
