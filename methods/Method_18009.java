synchronized void doFrame(long frameTimeNanos){
  if (mIsDirty) {
    regenerateSortedNodes();
  }
  propagate(frameTimeNanos);
  updateFinishedStates();
}
