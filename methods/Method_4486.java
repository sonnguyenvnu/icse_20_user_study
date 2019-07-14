private void maybeContinueLoading(){
  MediaPeriodHolder loadingPeriodHolder=queue.getLoadingPeriod();
  long nextLoadPositionUs=loadingPeriodHolder.getNextLoadPositionUs();
  if (nextLoadPositionUs == C.TIME_END_OF_SOURCE) {
    setIsLoading(false);
    return;
  }
  long bufferedDurationUs=getTotalBufferedDurationUs(nextLoadPositionUs);
  boolean continueLoading=loadControl.shouldContinueLoading(bufferedDurationUs,mediaClock.getPlaybackParameters().speed);
  setIsLoading(continueLoading);
  if (continueLoading) {
    loadingPeriodHolder.continueLoading(rendererPositionUs);
  }
}
