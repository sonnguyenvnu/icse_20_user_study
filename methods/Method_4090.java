private void recycleFromEnd(RecyclerView.Recycler recycler,int line){
  final int childCount=getChildCount();
  int i;
  for (i=childCount - 1; i >= 0; i--) {
    View child=getChildAt(i);
    if (mPrimaryOrientation.getDecoratedStart(child) >= line && mPrimaryOrientation.getTransformedStartWithDecoration(child) >= line) {
      LayoutParams lp=(LayoutParams)child.getLayoutParams();
      if (lp.mFullSpan) {
        for (int j=0; j < mSpanCount; j++) {
          if (mSpans[j].mViews.size() == 1) {
            return;
          }
        }
        for (int j=0; j < mSpanCount; j++) {
          mSpans[j].popEnd();
        }
      }
 else {
        if (lp.mSpan.mViews.size() == 1) {
          return;
        }
        lp.mSpan.popEnd();
      }
      removeAndRecycleView(child,recycler);
    }
 else {
      return;
    }
  }
}
