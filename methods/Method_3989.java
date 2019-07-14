void offsetPositionRecordsForRemove(int positionStart,int itemCount,boolean applyToPreLayout){
  final int positionEnd=positionStart + itemCount;
  final int childCount=mChildHelper.getUnfilteredChildCount();
  for (int i=0; i < childCount; i++) {
    final ViewHolder holder=getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
    if (holder != null && !holder.shouldIgnore()) {
      if (holder.mPosition >= positionEnd) {
        if (DEBUG) {
          Log.d(TAG,"offsetPositionRecordsForRemove attached child " + i + " holder " + holder + " now at position " + (holder.mPosition - itemCount));
        }
        holder.offsetPosition(-itemCount,applyToPreLayout);
        mState.mStructureChanged=true;
      }
 else       if (holder.mPosition >= positionStart) {
        if (DEBUG) {
          Log.d(TAG,"offsetPositionRecordsForRemove attached child " + i + " holder " + holder + " now REMOVED");
        }
        holder.flagRemovedAndOffsetPosition(positionStart - 1,-itemCount,applyToPreLayout);
        mState.mStructureChanged=true;
      }
    }
  }
  mRecycler.offsetPositionRecordsForRemove(positionStart,itemCount,applyToPreLayout);
  requestLayout();
}
