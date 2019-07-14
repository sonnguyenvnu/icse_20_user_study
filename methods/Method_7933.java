@Override protected void onLongPress(){
  if (delegate != null) {
    delegate.didLongPress(this,lastTouchX,lastTouchY);
  }
}
