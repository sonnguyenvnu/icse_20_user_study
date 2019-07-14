@Override public long getContentBufferedPosition(){
  if (shouldMaskPosition()) {
    return maskingWindowPositionMs;
  }
  if (playbackInfo.loadingMediaPeriodId.windowSequenceNumber != playbackInfo.periodId.windowSequenceNumber) {
    return playbackInfo.timeline.getWindow(getCurrentWindowIndex(),window).getDurationMs();
  }
  long contentBufferedPositionUs=playbackInfo.bufferedPositionUs;
  if (playbackInfo.loadingMediaPeriodId.isAd()) {
    Timeline.Period loadingPeriod=playbackInfo.timeline.getPeriodByUid(playbackInfo.loadingMediaPeriodId.periodUid,period);
    contentBufferedPositionUs=loadingPeriod.getAdGroupTimeUs(playbackInfo.loadingMediaPeriodId.adGroupIndex);
    if (contentBufferedPositionUs == C.TIME_END_OF_SOURCE) {
      contentBufferedPositionUs=loadingPeriod.durationUs;
    }
  }
  return periodPositionUsToWindowPositionMs(playbackInfo.loadingMediaPeriodId,contentBufferedPositionUs);
}
