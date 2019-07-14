private boolean isTimelineReady(){
  MediaPeriodHolder playingPeriodHolder=queue.getPlayingPeriod();
  MediaPeriodHolder nextPeriodHolder=playingPeriodHolder.getNext();
  long playingPeriodDurationUs=playingPeriodHolder.info.durationUs;
  return playingPeriodDurationUs == C.TIME_UNSET || playbackInfo.positionUs < playingPeriodDurationUs || (nextPeriodHolder != null && (nextPeriodHolder.prepared || nextPeriodHolder.info.id.isAd()));
}
