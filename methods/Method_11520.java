@Override public boolean dispatchTouchEvent(MotionEvent ev){
  final int action=MotionEventCompat.getActionMasked(ev);
  if (!isEnabled() || !isTouchEnabled() || (mIsUnableToDrag && action != MotionEvent.ACTION_DOWN)) {
    mDragHelper.abort();
    return super.dispatchTouchEvent(ev);
  }
  final float x=ev.getX();
  final float y=ev.getY();
  if (action == MotionEvent.ACTION_DOWN) {
    mIsScrollableViewHandlingTouch=false;
    mPrevMotionX=x;
    mPrevMotionY=y;
  }
 else   if (action == MotionEvent.ACTION_MOVE) {
    float dx=x - mPrevMotionX;
    float dy=y - mPrevMotionY;
    mPrevMotionX=x;
    mPrevMotionY=y;
    if (Math.abs(dx) > Math.abs(dy)) {
      return super.dispatchTouchEvent(ev);
    }
    if (!isViewUnder(mScrollableView,(int)mInitialMotionX,(int)mInitialMotionY)) {
      return super.dispatchTouchEvent(ev);
    }
    if (dy * (mIsSlidingUp ? 1 : -1) > 0) {
      if (mScrollableViewHelper.getScrollableViewScrollPosition(mScrollableView,mIsSlidingUp) > 0) {
        mIsScrollableViewHandlingTouch=true;
        return super.dispatchTouchEvent(ev);
      }
      if (mIsScrollableViewHandlingTouch) {
        MotionEvent up=MotionEvent.obtain(ev);
        up.setAction(MotionEvent.ACTION_CANCEL);
        super.dispatchTouchEvent(up);
        up.recycle();
        ev.setAction(MotionEvent.ACTION_DOWN);
      }
      mIsScrollableViewHandlingTouch=false;
      return this.onTouchEvent(ev);
    }
 else     if (dy * (mIsSlidingUp ? 1 : -1) < 0) {
      if (mSlideOffset < 1.0f) {
        mIsScrollableViewHandlingTouch=false;
        return this.onTouchEvent(ev);
      }
      if (!mIsScrollableViewHandlingTouch && mDragHelper.isDragging()) {
        mDragHelper.cancel();
        ev.setAction(MotionEvent.ACTION_DOWN);
      }
      mIsScrollableViewHandlingTouch=true;
      return super.dispatchTouchEvent(ev);
    }
  }
 else   if (action == MotionEvent.ACTION_UP) {
    if (mIsScrollableViewHandlingTouch) {
      mDragHelper.setDragState(ViewDragHelper.STATE_IDLE);
    }
  }
  return super.dispatchTouchEvent(ev);
}
