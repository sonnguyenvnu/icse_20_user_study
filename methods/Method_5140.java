@Override public boolean isReady(){
  return loadingFinished || (!isPendingReset() && primarySampleQueue.hasNextSample());
}
