@Override public View onFocusSearchFailed(View focused,int focusDirection,RecyclerView.Recycler recycler,RecyclerView.State state){
  resolveShouldLayoutReverse();
  if (getChildCount() == 0) {
    return null;
  }
  final int layoutDir=convertFocusDirectionToLayoutDirection(focusDirection);
  if (layoutDir == LayoutState.INVALID_LAYOUT) {
    return null;
  }
  ensureLayoutState();
  final int maxScroll=(int)(MAX_SCROLL_FACTOR * mOrientationHelper.getTotalSpace());
  updateLayoutState(layoutDir,maxScroll,false,state);
  mLayoutState.mScrollingOffset=LayoutState.SCROLLING_OFFSET_NaN;
  mLayoutState.mRecycle=false;
  fill(recycler,mLayoutState,state,true);
  final View nextCandidate;
  if (layoutDir == LayoutState.LAYOUT_START) {
    nextCandidate=findPartiallyOrCompletelyInvisibleChildClosestToStart();
  }
 else {
    nextCandidate=findPartiallyOrCompletelyInvisibleChildClosestToEnd();
  }
  final View nextFocus;
  if (layoutDir == LayoutState.LAYOUT_START) {
    nextFocus=getChildClosestToStart();
  }
 else {
    nextFocus=getChildClosestToEnd();
  }
  if (nextFocus.hasFocusable()) {
    if (nextCandidate == null) {
      return null;
    }
    return nextFocus;
  }
  return nextCandidate;
}
