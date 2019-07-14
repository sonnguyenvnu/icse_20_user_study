@Override public boolean onSingleTapUp(MotionEvent e){
  if (!canZoom && !doubleTapEnabled) {
    return onSingleTapConfirmed(e);
  }
  return false;
}
