private void updateChildDrawingOrderIfNeeded(){
  if (!mIsChildDrawingOrderDirty) {
    return;
  }
  final int childCount=getChildCount();
  if (mChildDrawingOrder.length < childCount) {
    mChildDrawingOrder=new int[childCount + 5];
  }
  int index=0;
  final int viewMountItemCount=mViewMountItems == null ? 0 : mViewMountItems.size();
  for (int i=0; i < viewMountItemCount; i++) {
    final View child=(View)mViewMountItems.valueAt(i).getContent();
    mChildDrawingOrder[index++]=indexOfChild(child);
  }
  for (int i=0, size=mDisappearingItems == null ? 0 : mDisappearingItems.size(); i < size; i++) {
    final Object child=mDisappearingItems.get(i).getContent();
    if (child instanceof View) {
      mChildDrawingOrder[index++]=indexOfChild((View)child);
    }
  }
  mIsChildDrawingOrderDirty=false;
}
