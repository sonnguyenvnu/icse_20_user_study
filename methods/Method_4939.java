/** 
 * Updates media periods in the queue to take into account the latest timeline, and returns whether the timeline change has been fully handled. If not, it is necessary to seek to the current playback position. The method assumes that the first media period in the queue is still consistent with the new timeline.
 * @param playingPeriodId The current playing media period identifier.
 * @param rendererPositionUs The current renderer position in microseconds.
 * @return Whether the timeline change has been handled completely.
 */
public boolean updateQueuedPeriods(MediaPeriodId playingPeriodId,long rendererPositionUs){
  int periodIndex=timeline.getIndexOfPeriod(playingPeriodId.periodUid);
  MediaPeriodHolder previousPeriodHolder=null;
  MediaPeriodHolder periodHolder=getFrontPeriod();
  while (periodHolder != null) {
    if (previousPeriodHolder == null) {
      long previousDurationUs=periodHolder.info.durationUs;
      periodHolder.info=getUpdatedMediaPeriodInfo(periodHolder.info);
      if (!canKeepAfterMediaPeriodHolder(periodHolder,previousDurationUs)) {
        return !removeAfter(periodHolder);
      }
    }
 else {
      if (periodIndex == C.INDEX_UNSET || !periodHolder.uid.equals(timeline.getUidOfPeriod(periodIndex))) {
        return !removeAfter(previousPeriodHolder);
      }
      MediaPeriodInfo periodInfo=getFollowingMediaPeriodInfo(previousPeriodHolder,rendererPositionUs);
      if (periodInfo == null) {
        return !removeAfter(previousPeriodHolder);
      }
      periodHolder.info=getUpdatedMediaPeriodInfo(periodHolder.info);
      if (!canKeepMediaPeriodHolder(periodHolder,periodInfo)) {
        return !removeAfter(previousPeriodHolder);
      }
 else       if (!canKeepAfterMediaPeriodHolder(periodHolder,periodInfo.durationUs)) {
        return !removeAfter(periodHolder);
      }
    }
    if (periodHolder.info.isLastInTimelinePeriod) {
      periodIndex=timeline.getNextPeriodIndex(periodIndex,period,window,repeatMode,shuffleModeEnabled);
    }
    previousPeriodHolder=periodHolder;
    periodHolder=periodHolder.getNext();
  }
  return true;
}
