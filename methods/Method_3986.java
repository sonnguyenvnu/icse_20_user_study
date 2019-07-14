void saveOldPositions(){
  final int childCount=mChildHelper.getUnfilteredChildCount();
  for (int i=0; i < childCount; i++) {
    final ViewHolder holder=getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
    if (DEBUG && holder.mPosition == -1 && !holder.isRemoved()) {
      throw new IllegalStateException("view holder cannot have position -1 unless it" + " is removed" + exceptionLabel());
    }
    if (!holder.shouldIgnore()) {
      holder.saveOldPosition();
    }
  }
}
