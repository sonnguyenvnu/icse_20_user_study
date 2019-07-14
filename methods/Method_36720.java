public void showPlaceholderView(boolean shown){
  this.mPlaceholderRequired=shown;
  if (!shown) {
    restoreAspectRatio();
  }
 else {
    storeAspectRatio();
  }
  if (!this.mCells.contains(mPlaceholderCell)) {
    if (requirePlaceholderCell()) {
      this.mCells.add(mPlaceholderCell);
      notifyDataChange();
    }
  }
 else {
    if (!requirePlaceholderCell() && this.mCells.remove(mPlaceholderCell))     notifyDataChange();
  }
}
