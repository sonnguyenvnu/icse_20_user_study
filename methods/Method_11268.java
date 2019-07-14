@Override public boolean onSingleTapUp(MotionEvent e){
  playSoundEffect(SoundEffectConstants.CLICK);
  refreshCenter((int)(getScrollX() + e.getX() - mMaxOverScrollDistance));
  autoSettle();
  return true;
}
