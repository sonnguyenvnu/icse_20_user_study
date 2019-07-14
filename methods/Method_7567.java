@Override public void requestDisallowInterceptTouchEvent(boolean disallowIntercept){
  onTouchEvent(null);
  super.requestDisallowInterceptTouchEvent(disallowIntercept);
}
