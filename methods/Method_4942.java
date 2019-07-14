/** 
 * Resolves the specified period uid to a corresponding window sequence number. Either by reusing the window sequence number of an existing matching media period or by creating a new window sequence number.
 * @param periodUid The uid of the timeline period.
 * @return A window sequence number for a media period created for this timeline period.
 */
private long resolvePeriodIndexToWindowSequenceNumber(Object periodUid){
  int windowIndex=timeline.getPeriodByUid(periodUid,period).windowIndex;
  if (oldFrontPeriodUid != null) {
    int oldFrontPeriodIndex=timeline.getIndexOfPeriod(oldFrontPeriodUid);
    if (oldFrontPeriodIndex != C.INDEX_UNSET) {
      int oldFrontWindowIndex=timeline.getPeriod(oldFrontPeriodIndex,period).windowIndex;
      if (oldFrontWindowIndex == windowIndex) {
        return oldFrontPeriodWindowSequenceNumber;
      }
    }
  }
  MediaPeriodHolder mediaPeriodHolder=getFrontPeriod();
  while (mediaPeriodHolder != null) {
    if (mediaPeriodHolder.uid.equals(periodUid)) {
      return mediaPeriodHolder.info.id.windowSequenceNumber;
    }
    mediaPeriodHolder=mediaPeriodHolder.getNext();
  }
  mediaPeriodHolder=getFrontPeriod();
  while (mediaPeriodHolder != null) {
    int indexOfHolderInTimeline=timeline.getIndexOfPeriod(mediaPeriodHolder.uid);
    if (indexOfHolderInTimeline != C.INDEX_UNSET) {
      int holderWindowIndex=timeline.getPeriod(indexOfHolderInTimeline,period).windowIndex;
      if (holderWindowIndex == windowIndex) {
        return mediaPeriodHolder.info.id.windowSequenceNumber;
      }
    }
    mediaPeriodHolder=mediaPeriodHolder.getNext();
  }
  return nextWindowSequenceNumber++;
}
