private void fireChoreographerCallbacks(){
  int size=mChoreographerCallbacksToStartTimes.size();
  for (int i=0; i < size; i++) {
    final Pair<FrameCallback,Long> entry=mChoreographerCallbacksToStartTimes.get(i);
    if (entry.second <= mCurrentTimeNanos) {
      entry.first.doFrame(mCurrentTimeNanos);
      mChoreographerCallbacksToStartTimes.remove(i);
      i--;
      size--;
    }
  }
}
