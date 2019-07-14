protected void onDragEnd(boolean cancelled){
  boolean startedFling=false;
  if (!cancelled) {
    float velocity=getCurrentVelocity();
    if (Math.abs(velocity) > mMinimumFlingVelocity) {
      fling(-velocity);
      startedFling=true;
    }
  }
  if (!startedFling && mScroll > 0 && mScroll < mHeaderView.getScrollExtent()) {
    snapHeaderView();
  }
}
