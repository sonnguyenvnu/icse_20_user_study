/** 
 * Converts a  {@link SeekPosition} into the corresponding (periodUid, periodPositionUs) for theinternal timeline.
 * @param seekPosition The position to resolve.
 * @param trySubsequentPeriods Whether the position can be resolved to a subsequent matchingperiod if the original period is no longer available.
 * @return The resolved position, or null if resolution was not successful.
 * @throws IllegalSeekPositionException If the window index of the seek position is outside thebounds of the timeline.
 */
private Pair<Object,Long> resolveSeekPosition(SeekPosition seekPosition,boolean trySubsequentPeriods){
  Timeline timeline=playbackInfo.timeline;
  Timeline seekTimeline=seekPosition.timeline;
  if (timeline.isEmpty()) {
    return null;
  }
  if (seekTimeline.isEmpty()) {
    seekTimeline=timeline;
  }
  Pair<Object,Long> periodPosition;
  try {
    periodPosition=seekTimeline.getPeriodPosition(window,period,seekPosition.windowIndex,seekPosition.windowPositionUs);
  }
 catch (  IndexOutOfBoundsException e) {
    throw new IllegalSeekPositionException(timeline,seekPosition.windowIndex,seekPosition.windowPositionUs);
  }
  if (timeline == seekTimeline) {
    return periodPosition;
  }
  int periodIndex=timeline.getIndexOfPeriod(periodPosition.first);
  if (periodIndex != C.INDEX_UNSET) {
    return periodPosition;
  }
  if (trySubsequentPeriods) {
    Object periodUid=resolveSubsequentPeriod(periodPosition.first,seekTimeline,timeline);
    if (periodUid != null) {
      return getPeriodPosition(timeline,timeline.getPeriod(periodIndex,period).windowIndex,C.TIME_UNSET);
    }
  }
  return null;
}
