@Override public long getPositionUs(){
  if (getState() == STATE_STARTED) {
    updateCurrentPosition();
  }
  return currentPositionUs;
}
