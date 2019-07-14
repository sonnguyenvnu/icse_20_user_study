private void recycle(RecyclerView.Recycler recycler,LayoutState layoutState){
  if (!layoutState.mRecycle || layoutState.mInfinite) {
    return;
  }
  if (layoutState.mAvailable == 0) {
    if (layoutState.mLayoutDirection == LayoutState.LAYOUT_START) {
      recycleFromEnd(recycler,layoutState.mEndLine);
    }
 else {
      recycleFromStart(recycler,layoutState.mStartLine);
    }
  }
 else {
    if (layoutState.mLayoutDirection == LayoutState.LAYOUT_START) {
      int scrolled=layoutState.mStartLine - getMaxStart(layoutState.mStartLine);
      final int line;
      if (scrolled < 0) {
        line=layoutState.mEndLine;
      }
 else {
        line=layoutState.mEndLine - Math.min(scrolled,layoutState.mAvailable);
      }
      recycleFromEnd(recycler,line);
    }
 else {
      int scrolled=getMinEnd(layoutState.mEndLine) - layoutState.mEndLine;
      final int line;
      if (scrolled < 0) {
        line=layoutState.mStartLine;
      }
 else {
        line=layoutState.mStartLine + Math.min(scrolled,layoutState.mAvailable);
      }
      recycleFromStart(recycler,line);
    }
  }
}
