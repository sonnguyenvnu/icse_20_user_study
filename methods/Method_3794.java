@Override protected void recycleViewsFromStart(RecyclerView.Recycler recycler,int scrollingOffset,int noRecycleSpace){
  if (scrollingOffset < 0) {
    return;
  }
  final int childCount=getChildCount();
  if (mShouldReverseLayout) {
    for (int i=childCount - 1; i >= 0; i--) {
      View child=getChildAt(i);
      final RecyclerView.LayoutParams params=(RecyclerView.LayoutParams)child.getLayoutParams();
      if (child.getBottom() + params.bottomMargin > scrollingOffset || child.getTop() + child.getHeight() > scrollingOffset) {
        recycleChildren(recycler,childCount - 1,i);
        return;
      }
    }
  }
 else {
    for (int i=0; i < childCount; i++) {
      View child=getChildAt(i);
      if (mOrientationHelper.getDecoratedEnd(child) > scrollingOffset || mOrientationHelper.getTransformedEndWithDecoration(child) > scrollingOffset) {
        recycleChildren(recycler,0,i);
        return;
      }
    }
  }
}
