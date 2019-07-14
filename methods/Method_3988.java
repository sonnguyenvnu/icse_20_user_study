void offsetPositionRecordsForInsert(int positionStart,int itemCount){
  final int childCount=mChildHelper.getUnfilteredChildCount();
  for (int i=0; i < childCount; i++) {
    final ViewHolder holder=getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
    if (holder != null && !holder.shouldIgnore() && holder.mPosition >= positionStart) {
      if (DEBUG) {
        Log.d(TAG,"offsetPositionRecordsForInsert attached child " + i + " holder " + holder + " now at position " + (holder.mPosition + itemCount));
      }
      holder.offsetPosition(itemCount,false);
      mState.mStructureChanged=true;
    }
  }
  mRecycler.offsetPositionRecordsForInsert(positionStart,itemCount);
  requestLayout();
}
