private void recycleFromStart(RecyclerView.Recycler recycler,int line){
  while (getChildCount() > 0) {
    View child=getChildAt(0);
    if (mPrimaryOrientation.getDecoratedEnd(child) <= line && mPrimaryOrientation.getTransformedEndWithDecoration(child) <= line) {
      LayoutParams lp=(LayoutParams)child.getLayoutParams();
      if (lp.mFullSpan) {
        for (int j=0; j < mSpanCount; j++) {
          if (mSpans[j].mViews.size() == 1) {
            return;
          }
        }
        for (int j=0; j < mSpanCount; j++) {
          mSpans[j].popStart();
        }
      }
 else {
        if (lp.mSpan.mViews.size() == 1) {
          return;
        }
        lp.mSpan.popStart();
      }
      removeAndRecycleView(child,recycler);
    }
 else {
      return;
    }
  }
}
