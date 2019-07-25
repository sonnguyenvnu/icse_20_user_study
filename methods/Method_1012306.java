public void reset(){
  mDragCenter.x=-1000;
  mDragCenter.y=-1000;
  mDragQuadrant=4;
  screenFromWindow(false);
  getParent().requestDisallowInterceptTouchEvent(false);
  invalidate();
}
