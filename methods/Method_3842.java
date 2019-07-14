int scrollBy(int delta,RecyclerView.Recycler recycler,RecyclerView.State state){
  if (getChildCount() == 0 || delta == 0) {
    return 0;
  }
  ensureLayoutState();
  mLayoutState.mRecycle=true;
  final int layoutDirection=delta > 0 ? LayoutState.LAYOUT_END : LayoutState.LAYOUT_START;
  final int absDelta=Math.abs(delta);
  updateLayoutState(layoutDirection,absDelta,true,state);
  final int consumed=mLayoutState.mScrollingOffset + fill(recycler,mLayoutState,state,false);
  if (consumed < 0) {
    if (DEBUG) {
      Log.d(TAG,"Don't have any more elements to scroll");
    }
    return 0;
  }
  final int scrolled=absDelta > consumed ? layoutDirection * consumed : delta;
  mOrientationHelper.offsetChildren(-scrolled);
  if (DEBUG) {
    Log.d(TAG,"scroll req: " + delta + " scrolled: " + scrolled);
  }
  mLayoutState.mLastScrollDelta=scrolled;
  return scrolled;
}
