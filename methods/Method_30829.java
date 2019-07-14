@Override public final void onScrolledDown(int dy){
  mDy=mDy > 0 ? mDy + dy : dy;
  if (mDy > mPagingTouchSlop) {
    onScrolledDown();
  }
}
