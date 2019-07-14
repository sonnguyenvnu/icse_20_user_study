@Override public boolean onFling(MotionEvent e1,MotionEvent e2,float velocityX,float velocityY){
  float scrollX=getScrollX();
  if (scrollX < -mMaxOverScrollDistance + mMinSelectableIndex * mIntervalDis || scrollX > mContentRectF.width() - mMaxOverScrollDistance - (mMarkCount - 1 - mMaxSelectableIndex) * mIntervalDis) {
    return false;
  }
 else {
    mFling=true;
    fling((int)-velocityX,0);
    return true;
  }
}
