@Override public boolean onDown(MotionEvent e){
  if (!scroll.isFinished()) {
    scroll.abortAnimation();
  }
  animateToItem=-1;
  return true;
}
