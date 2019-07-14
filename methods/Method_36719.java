public void enablePlaceholderView(View placeholderView,int placeholderHeight){
  if (!TextUtils.isEmpty(load) && placeholderView != null) {
    storeAspectRatio();
    this.mPlaceholderCell=new PlaceholderCell(placeholderHeight,placeholderView);
    if (this.mCells.size() == 0) {
      mCells.add(mPlaceholderCell);
    }
  }
 else {
    this.mCells.remove(mPlaceholderCell);
    this.mPlaceholderCell=null;
  }
}
