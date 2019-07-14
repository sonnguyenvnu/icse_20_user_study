@Override public boolean onInterceptTouchEvent(MotionEvent ev){
  return delegate.allowInteraction(this);
}
