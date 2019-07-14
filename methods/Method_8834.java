@Override public boolean onInterceptTouchEvent(MotionEvent ev){
  return ev.getPointerCount() == 2 && delegate.shouldReceiveTouches();
}
