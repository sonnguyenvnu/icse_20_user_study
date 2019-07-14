public void addCells(Card parent,int index,@Nullable List<BaseCell> cells){
  if (cells != null) {
    int i=0;
    for (    BaseCell cell : cells) {
      addCellInternal(parent,index + i,cell,false);
      i++;
    }
  }
  adjustPendingCells(false);
  if (mPlaceholderCell != null && this.mCells.contains(mPlaceholderCell))   this.mCells.remove(mPlaceholderCell);
  if (requirePlaceholderCell()) {
    this.mCells.add(mPlaceholderCell);
  }
}
