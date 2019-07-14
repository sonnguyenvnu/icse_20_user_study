@Override public void onGestureUpdate(TransformGestureDetector detector){
  FLog.v(getLogTag(),"onGestureUpdate %s",isAnimating() ? "(ignored)" : "");
  if (isAnimating()) {
    return;
  }
  super.onGestureUpdate(detector);
}
