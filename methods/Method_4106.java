@Nullable @Override public View onFocusSearchFailed(View focused,int direction,RecyclerView.Recycler recycler,RecyclerView.State state){
  if (getChildCount() == 0) {
    return null;
  }
  final View directChild=findContainingItemView(focused);
  if (directChild == null) {
    return null;
  }
  resolveShouldLayoutReverse();
  final int layoutDir=convertFocusDirectionToLayoutDirection(direction);
  if (layoutDir == LayoutState.INVALID_LAYOUT) {
    return null;
  }
  LayoutParams prevFocusLayoutParams=(LayoutParams)directChild.getLayoutParams();
  boolean prevFocusFullSpan=prevFocusLayoutParams.mFullSpan;
  final Span prevFocusSpan=prevFocusLayoutParams.mSpan;
  final int referenceChildPosition;
  if (layoutDir == LayoutState.LAYOUT_END) {
    referenceChildPosition=getLastChildPosition();
  }
 else {
    referenceChildPosition=getFirstChildPosition();
  }
  updateLayoutState(referenceChildPosition,state);
  setLayoutStateDirection(layoutDir);
  mLayoutState.mCurrentPosition=referenceChildPosition + mLayoutState.mItemDirection;
  mLayoutState.mAvailable=(int)(MAX_SCROLL_FACTOR * mPrimaryOrientation.getTotalSpace());
  mLayoutState.mStopInFocusable=true;
  mLayoutState.mRecycle=false;
  fill(recycler,mLayoutState,state);
  mLastLayoutFromEnd=mShouldReverseLayout;
  if (!prevFocusFullSpan) {
    View view=prevFocusSpan.getFocusableViewAfter(referenceChildPosition,layoutDir);
    if (view != null && view != directChild) {
      return view;
    }
  }
  if (preferLastSpan(layoutDir)) {
    for (int i=mSpanCount - 1; i >= 0; i--) {
      View view=mSpans[i].getFocusableViewAfter(referenceChildPosition,layoutDir);
      if (view != null && view != directChild) {
        return view;
      }
    }
  }
 else {
    for (int i=0; i < mSpanCount; i++) {
      View view=mSpans[i].getFocusableViewAfter(referenceChildPosition,layoutDir);
      if (view != null && view != directChild) {
        return view;
      }
    }
  }
  boolean shouldSearchFromStart=!mReverseLayout == (layoutDir == LayoutState.LAYOUT_START);
  View unfocusableCandidate=null;
  if (!prevFocusFullSpan) {
    unfocusableCandidate=findViewByPosition(shouldSearchFromStart ? prevFocusSpan.findFirstPartiallyVisibleItemPosition() : prevFocusSpan.findLastPartiallyVisibleItemPosition());
    if (unfocusableCandidate != null && unfocusableCandidate != directChild) {
      return unfocusableCandidate;
    }
  }
  if (preferLastSpan(layoutDir)) {
    for (int i=mSpanCount - 1; i >= 0; i--) {
      if (i == prevFocusSpan.mIndex) {
        continue;
      }
      unfocusableCandidate=findViewByPosition(shouldSearchFromStart ? mSpans[i].findFirstPartiallyVisibleItemPosition() : mSpans[i].findLastPartiallyVisibleItemPosition());
      if (unfocusableCandidate != null && unfocusableCandidate != directChild) {
        return unfocusableCandidate;
      }
    }
  }
 else {
    for (int i=0; i < mSpanCount; i++) {
      unfocusableCandidate=findViewByPosition(shouldSearchFromStart ? mSpans[i].findFirstPartiallyVisibleItemPosition() : mSpans[i].findLastPartiallyVisibleItemPosition());
      if (unfocusableCandidate != null && unfocusableCandidate != directChild) {
        return unfocusableCandidate;
      }
    }
  }
  return null;
}
