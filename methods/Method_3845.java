/** 
 * Recycles views that went out of bounds after scrolling towards the end of the layout. <p> Checks both layout position and visible position to guarantee that the view is not visible.
 * @param recycler Recycler instance of {@link RecyclerView}
 * @param scrollingOffset This can be used to add additional padding to the visible area. Thisis used to detect children that will go out of bounds after scrolling, without actually moving them.
 * @param noRecycleSpace Extra space that should be excluded from recycling. This is the spacefrom  {@code extraLayoutSpace[0]}, calculated in  {@link #calculateExtraLayoutSpace}.
 */
protected void recycleViewsFromStart(RecyclerView.Recycler recycler,int scrollingOffset,int noRecycleSpace){
  if (scrollingOffset < 0) {
    if (DEBUG) {
      Log.d(TAG,"Called recycle from start with a negative value. This might happen" + " during layout changes but may be sign of a bug");
    }
    return;
  }
  final int limit=scrollingOffset - noRecycleSpace;
  final int childCount=getChildCount();
  if (mShouldReverseLayout) {
    for (int i=childCount - 1; i >= 0; i--) {
      View child=getChildAt(i);
      if (mOrientationHelper.getDecoratedEnd(child) > limit || mOrientationHelper.getTransformedEndWithDecoration(child) > limit) {
        recycleChildren(recycler,childCount - 1,i);
        return;
      }
    }
  }
 else {
    for (int i=0; i < childCount; i++) {
      View child=getChildAt(i);
      if (mOrientationHelper.getDecoratedEnd(child) > limit || mOrientationHelper.getTransformedEndWithDecoration(child) > limit) {
        recycleChildren(recycler,0,i);
        return;
      }
    }
  }
}
