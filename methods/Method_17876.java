@Override public boolean onInterceptTouchEvent(MotionEvent ev){
  if (mOnInterceptTouchEventHandler != null) {
    return EventDispatcherUtils.dispatchOnInterceptTouch(mOnInterceptTouchEventHandler,this,ev);
  }
  return super.onInterceptTouchEvent(ev);
}
