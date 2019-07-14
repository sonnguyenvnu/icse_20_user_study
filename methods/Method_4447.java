private PlaybackInfo getResetPlaybackInfo(boolean resetPosition,boolean resetState,int playbackState){
  if (resetPosition) {
    maskingWindowIndex=0;
    maskingPeriodIndex=0;
    maskingWindowPositionMs=0;
  }
 else {
    maskingWindowIndex=getCurrentWindowIndex();
    maskingPeriodIndex=getCurrentPeriodIndex();
    maskingWindowPositionMs=getCurrentPosition();
  }
  MediaPeriodId mediaPeriodId=resetPosition ? playbackInfo.getDummyFirstMediaPeriodId(shuffleModeEnabled,window) : playbackInfo.periodId;
  long startPositionUs=resetPosition ? 0 : playbackInfo.positionUs;
  long contentPositionUs=resetPosition ? C.TIME_UNSET : playbackInfo.contentPositionUs;
  return new PlaybackInfo(resetState ? Timeline.EMPTY : playbackInfo.timeline,resetState ? null : playbackInfo.manifest,mediaPeriodId,startPositionUs,contentPositionUs,playbackState,false,resetState ? TrackGroupArray.EMPTY : playbackInfo.trackGroups,resetState ? emptyTrackSelectorResult : playbackInfo.trackSelectorResult,mediaPeriodId,startPositionUs,0,startPositionUs);
}
