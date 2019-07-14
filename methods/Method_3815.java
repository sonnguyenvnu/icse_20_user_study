private void addChildDrawingOrderCallback(){
  if (Build.VERSION.SDK_INT >= 21) {
    return;
  }
  if (mChildDrawingOrderCallback == null) {
    mChildDrawingOrderCallback=new RecyclerView.ChildDrawingOrderCallback(){
      @Override public int onGetChildDrawingOrder(      int childCount,      int i){
        if (mOverdrawChild == null) {
          return i;
        }
        int childPosition=mOverdrawChildPosition;
        if (childPosition == -1) {
          childPosition=mRecyclerView.indexOfChild(mOverdrawChild);
          mOverdrawChildPosition=childPosition;
        }
        if (i == childCount - 1) {
          return childPosition;
        }
        return i < childPosition ? i : i + 1;
      }
    }
;
  }
  mRecyclerView.setChildDrawingOrderCallback(mChildDrawingOrderCallback);
}
