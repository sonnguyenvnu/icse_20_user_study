public boolean replaceCell(@Nullable BaseCell oldCell,@Nullable BaseCell newCell){
  if (oldCell == null || newCell == null) {
    return false;
  }
  int index=mCells.indexOf(oldCell);
  if (index >= 0) {
    mCells.set(index,newCell);
    newCell.onAdded();
    oldCell.onRemoved();
    return true;
  }
 else {
    return false;
  }
}
