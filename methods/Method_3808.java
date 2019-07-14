private ViewHolder findSwipedView(MotionEvent motionEvent){
  final RecyclerView.LayoutManager lm=mRecyclerView.getLayoutManager();
  if (mActivePointerId == ACTIVE_POINTER_ID_NONE) {
    return null;
  }
  final int pointerIndex=motionEvent.findPointerIndex(mActivePointerId);
  final float dx=motionEvent.getX(pointerIndex) - mInitialTouchX;
  final float dy=motionEvent.getY(pointerIndex) - mInitialTouchY;
  final float absDx=Math.abs(dx);
  final float absDy=Math.abs(dy);
  if (absDx < mSlop && absDy < mSlop) {
    return null;
  }
  if (absDx > absDy && lm.canScrollHorizontally()) {
    return null;
  }
 else   if (absDy > absDx && lm.canScrollVertically()) {
    return null;
  }
  View child=findChildView(motionEvent);
  if (child == null) {
    return null;
  }
  return mRecyclerView.getChildViewHolder(child);
}
