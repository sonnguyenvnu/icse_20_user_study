@Override public boolean onSingleTapConfirmed(MotionEvent e){
  if (discardTap) {
    return false;
  }
  toggleActionBar(!isActionBarVisible,true);
  return true;
}
