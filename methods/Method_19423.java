private boolean shouldHandleTouchForLongClickableSpan(MotionEvent event){
  return mShouldHandleTouch && mLongClickHandler != null && event.getAction() != ACTION_DOWN;
}
