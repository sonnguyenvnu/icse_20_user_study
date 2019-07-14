private boolean rendererWaitingForNextStream(Renderer renderer){
  MediaPeriodHolder readingPeriodHolder=queue.getReadingPeriod();
  MediaPeriodHolder nextPeriodHolder=readingPeriodHolder.getNext();
  return nextPeriodHolder != null && nextPeriodHolder.prepared && renderer.hasReadStreamToEnd();
}
