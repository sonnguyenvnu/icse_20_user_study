@Override public boolean onSingleTapConfirmed(MotionEvent event){
  if (event.getX() < mHalfScreenWidth) {
    onAction(Action.A);
  }
 else {
    onAction(Action.B);
  }
  return true;
}
