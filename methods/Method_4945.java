/** 
 * Updates the queue for any playback mode change, and returns whether the change was fully handled. If not, it is necessary to seek to the current playback position.
 */
private boolean updateForPlaybackModeChange(){
  MediaPeriodHolder lastValidPeriodHolder=getFrontPeriod();
  if (lastValidPeriodHolder == null) {
    return true;
  }
  int currentPeriodIndex=timeline.getIndexOfPeriod(lastValidPeriodHolder.uid);
  while (true) {
    int nextPeriodIndex=timeline.getNextPeriodIndex(currentPeriodIndex,period,window,repeatMode,shuffleModeEnabled);
    while (lastValidPeriodHolder.getNext() != null && !lastValidPeriodHolder.info.isLastInTimelinePeriod) {
      lastValidPeriodHolder=lastValidPeriodHolder.getNext();
    }
    MediaPeriodHolder nextMediaPeriodHolder=lastValidPeriodHolder.getNext();
    if (nextPeriodIndex == C.INDEX_UNSET || nextMediaPeriodHolder == null) {
      break;
    }
    int nextPeriodHolderPeriodIndex=timeline.getIndexOfPeriod(nextMediaPeriodHolder.uid);
    if (nextPeriodHolderPeriodIndex != nextPeriodIndex) {
      break;
    }
    lastValidPeriodHolder=nextMediaPeriodHolder;
    currentPeriodIndex=nextPeriodIndex;
  }
  boolean readingPeriodRemoved=removeAfter(lastValidPeriodHolder);
  lastValidPeriodHolder.info=getUpdatedMediaPeriodInfo(lastValidPeriodHolder.info);
  return !readingPeriodRemoved || !hasPlayingPeriod();
}
