/** 
 * Checks whether we should select a View for swiping.
 */
@SuppressWarnings("WeakerAccess") void checkSelectForSwipe(int action,MotionEvent motionEvent,int pointerIndex){
  if (mSelected != null || action != MotionEvent.ACTION_MOVE || mActionState == ACTION_STATE_DRAG || !mCallback.isItemViewSwipeEnabled()) {
    return;
  }
  if (mRecyclerView.getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING) {
    return;
  }
  final ViewHolder vh=findSwipedView(motionEvent);
  if (vh == null) {
    return;
  }
  final int movementFlags=mCallback.getAbsoluteMovementFlags(mRecyclerView,vh);
  final int swipeFlags=(movementFlags & ACTION_MODE_SWIPE_MASK) >> (DIRECTION_FLAG_COUNT * ACTION_STATE_SWIPE);
  if (swipeFlags == 0) {
    return;
  }
  final float x=motionEvent.getX(pointerIndex);
  final float y=motionEvent.getY(pointerIndex);
  final float dx=x - mInitialTouchX;
  final float dy=y - mInitialTouchY;
  final float absDx=Math.abs(dx);
  final float absDy=Math.abs(dy);
  if (absDx < mSlop && absDy < mSlop) {
    return;
  }
  if (absDx > absDy) {
    if (dx < 0 && (swipeFlags & LEFT) == 0) {
      return;
    }
    if (dx > 0 && (swipeFlags & RIGHT) == 0) {
      return;
    }
  }
 else {
    if (dy < 0 && (swipeFlags & UP) == 0) {
      return;
    }
    if (dy > 0 && (swipeFlags & DOWN) == 0) {
      return;
    }
  }
  mDx=mDy=0f;
  mActivePointerId=motionEvent.getPointerId(0);
  select(vh,ACTION_STATE_SWIPE);
}
