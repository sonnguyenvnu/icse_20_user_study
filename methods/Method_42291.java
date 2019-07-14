@Override public boolean onTrackballEvent(MotionEvent ev){
  if (!useController || player == null)   return false;
  maybeShowController(true);
  return true;
}
