@Override public boolean shouldHandleTouchEvent(MotionEvent event){
  return shouldHandleTouchForClickableSpan(event) || shouldHandleTouchForLongClickableSpan(event) || shouldHandleTextOffsetOnTouch(event);
}
