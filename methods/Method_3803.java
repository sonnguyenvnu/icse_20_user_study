/** 
 * If user drags the view to the edge, trigger a scroll if necessary.
 */
@SuppressWarnings("WeakerAccess") boolean scrollIfNecessary(){
  if (mSelected == null) {
    mDragScrollStartTimeInMs=Long.MIN_VALUE;
    return false;
  }
  final long now=System.currentTimeMillis();
  final long scrollDuration=mDragScrollStartTimeInMs == Long.MIN_VALUE ? 0 : now - mDragScrollStartTimeInMs;
  RecyclerView.LayoutManager lm=mRecyclerView.getLayoutManager();
  if (mTmpRect == null) {
    mTmpRect=new Rect();
  }
  int scrollX=0;
  int scrollY=0;
  lm.calculateItemDecorationsForChild(mSelected.itemView,mTmpRect);
  if (lm.canScrollHorizontally()) {
    int curX=(int)(mSelectedStartX + mDx);
    final int leftDiff=curX - mTmpRect.left - mRecyclerView.getPaddingLeft();
    if (mDx < 0 && leftDiff < 0) {
      scrollX=leftDiff;
    }
 else     if (mDx > 0) {
      final int rightDiff=curX + mSelected.itemView.getWidth() + mTmpRect.right - (mRecyclerView.getWidth() - mRecyclerView.getPaddingRight());
      if (rightDiff > 0) {
        scrollX=rightDiff;
      }
    }
  }
  if (lm.canScrollVertically()) {
    int curY=(int)(mSelectedStartY + mDy);
    final int topDiff=curY - mTmpRect.top - mRecyclerView.getPaddingTop();
    if (mDy < 0 && topDiff < 0) {
      scrollY=topDiff;
    }
 else     if (mDy > 0) {
      final int bottomDiff=curY + mSelected.itemView.getHeight() + mTmpRect.bottom - (mRecyclerView.getHeight() - mRecyclerView.getPaddingBottom());
      if (bottomDiff > 0) {
        scrollY=bottomDiff;
      }
    }
  }
  if (scrollX != 0) {
    scrollX=mCallback.interpolateOutOfBoundsScroll(mRecyclerView,mSelected.itemView.getWidth(),scrollX,mRecyclerView.getWidth(),scrollDuration);
  }
  if (scrollY != 0) {
    scrollY=mCallback.interpolateOutOfBoundsScroll(mRecyclerView,mSelected.itemView.getHeight(),scrollY,mRecyclerView.getHeight(),scrollDuration);
  }
  if (scrollX != 0 || scrollY != 0) {
    if (mDragScrollStartTimeInMs == Long.MIN_VALUE) {
      mDragScrollStartTimeInMs=now;
    }
    mRecyclerView.scrollBy(scrollX,scrollY);
    return true;
  }
  mDragScrollStartTimeInMs=Long.MIN_VALUE;
  return false;
}
