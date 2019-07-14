private boolean shouldHandleTouchForClickableSpan(MotionEvent event){
  final int action=event.getActionMasked();
  final boolean isUpOrDown=action == ACTION_UP || action == ACTION_DOWN;
  return (mShouldHandleTouch && isWithinBounds(getBounds(),event) && isUpOrDown) || action == ACTION_CANCEL;
}
