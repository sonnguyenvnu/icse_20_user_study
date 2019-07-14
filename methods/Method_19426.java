private boolean shouldHandleTextOffsetOnTouch(MotionEvent event){
  return mTextOffsetOnTouchListener != null && event.getActionMasked() == ACTION_DOWN && getBounds().contains((int)event.getX(),(int)event.getY());
}
