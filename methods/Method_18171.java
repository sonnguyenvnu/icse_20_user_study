@Override public boolean hasTouchEventHandlers(){
  return mClickHandler != null || mLongClickHandler != null || mTouchHandler != null || mInterceptTouchHandler != null;
}
