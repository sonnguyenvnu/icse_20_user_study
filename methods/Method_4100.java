int scrollBy(int dt,RecyclerView.Recycler recycler,RecyclerView.State state){
  if (getChildCount() == 0 || dt == 0) {
    return 0;
  }
  prepareLayoutStateForDelta(dt,state);
  int consumed=fill(recycler,mLayoutState,state);
  final int available=mLayoutState.mAvailable;
  final int totalScroll;
  if (available < consumed) {
    totalScroll=dt;
  }
 else   if (dt < 0) {
    totalScroll=-consumed;
  }
 else {
    totalScroll=consumed;
  }
  if (DEBUG) {
    Log.d(TAG,"asked " + dt + " scrolled" + totalScroll);
  }
  mPrimaryOrientation.offsetChildren(-totalScroll);
  mLastLayoutFromEnd=mShouldReverseLayout;
  mLayoutState.mAvailable=0;
  recycle(recycler,mLayoutState);
  return totalScroll;
}
