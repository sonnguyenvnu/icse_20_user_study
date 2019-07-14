/** 
 * @hide 
 */
@Override @RestrictTo(LIBRARY) public void collectAdjacentPrefetchPositions(int dx,int dy,RecyclerView.State state,LayoutPrefetchRegistry layoutPrefetchRegistry){
  int delta=(mOrientation == HORIZONTAL) ? dx : dy;
  if (getChildCount() == 0 || delta == 0) {
    return;
  }
  prepareLayoutStateForDelta(delta,state);
  if (mPrefetchDistances == null || mPrefetchDistances.length < mSpanCount) {
    mPrefetchDistances=new int[mSpanCount];
  }
  int itemPrefetchCount=0;
  for (int i=0; i < mSpanCount; i++) {
    int distance=mLayoutState.mItemDirection == LayoutState.LAYOUT_START ? mLayoutState.mStartLine - mSpans[i].getStartLine(mLayoutState.mStartLine) : mSpans[i].getEndLine(mLayoutState.mEndLine) - mLayoutState.mEndLine;
    if (distance >= 0) {
      mPrefetchDistances[itemPrefetchCount]=distance;
      itemPrefetchCount++;
    }
  }
  Arrays.sort(mPrefetchDistances,0,itemPrefetchCount);
  for (int i=0; i < itemPrefetchCount && mLayoutState.hasMore(state); i++) {
    layoutPrefetchRegistry.addPosition(mLayoutState.mCurrentPosition,mPrefetchDistances[i]);
    mLayoutState.mCurrentPosition+=mLayoutState.mItemDirection;
  }
}
