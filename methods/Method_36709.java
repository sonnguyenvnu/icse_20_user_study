public void setCells(@Nullable List<BaseCell> cells){
  if (mPlaceholderCell != null)   this.mCells.remove(mPlaceholderCell);
  oldMap.clear();
  pendingDeleteMap.clear();
  for (  BaseCell cell : this.mCells) {
    oldMap.put(System.identityHashCode(cell),cell);
  }
  this.mCells.clear();
  if (cells != null) {
    for (    BaseCell c : cells) {
      this.addCellInternal(c,true);
    }
  }
  adjustPendingCells(true);
  newMap.clear();
  for (  BaseCell cell : this.mCells) {
    newMap.put(System.identityHashCode(cell),cell);
  }
  for (int i=0, size=oldMap.size(); i < size; i++) {
    int key=oldMap.keyAt(i);
    if (newMap.get(key) != null) {
      newMap.remove(key);
      pendingDeleteMap.put(key,true);
    }
  }
  for (int i=0, size=pendingDeleteMap.size(); i < size; i++) {
    oldMap.remove(pendingDeleteMap.keyAt(i));
  }
  diffCells(newMap,oldMap);
  newMap.clear();
  oldMap.clear();
  pendingDeleteMap.clear();
  if (requirePlaceholderCell()) {
    this.mCells.add(mPlaceholderCell);
  }
}
