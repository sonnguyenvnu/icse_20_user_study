void offsetPositionRecordsForMove(int from,int to){
  final int childCount=mChildHelper.getUnfilteredChildCount();
  final int start, end, inBetweenOffset;
  if (from < to) {
    start=from;
    end=to;
    inBetweenOffset=-1;
  }
 else {
    start=to;
    end=from;
    inBetweenOffset=1;
  }
  for (int i=0; i < childCount; i++) {
    final ViewHolder holder=getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
    if (holder == null || holder.mPosition < start || holder.mPosition > end) {
      continue;
    }
    if (DEBUG) {
      Log.d(TAG,"offsetPositionRecordsForMove attached child " + i + " holder " + holder);
    }
    if (holder.mPosition == from) {
      holder.offsetPosition(to - from,false);
    }
 else {
      holder.offsetPosition(inBetweenOffset,false);
    }
    mState.mStructureChanged=true;
  }
  mRecycler.offsetPositionRecordsForMove(from,to);
  requestLayout();
}
