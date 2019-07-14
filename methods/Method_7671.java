@Override public void requestDisallowInterceptTouchEvent(boolean disallowIntercept){
  if (maybeStartTracking && !startedTracking) {
    onTouchEvent(null);
  }
  super.requestDisallowInterceptTouchEvent(disallowIntercept);
}
