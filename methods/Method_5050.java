/** 
 * Copies playback info with new track information.
 * @param trackGroups New track groups. See {@link #trackGroups}.
 * @param trackSelectorResult New track selector result. See {@link #trackSelectorResult}.
 * @return Copied playback info with new track information.
 */
@CheckResult public PlaybackInfo copyWithTrackInfo(TrackGroupArray trackGroups,TrackSelectorResult trackSelectorResult){
  return new PlaybackInfo(timeline,manifest,periodId,startPositionUs,contentPositionUs,playbackState,isLoading,trackGroups,trackSelectorResult,loadingMediaPeriodId,bufferedPositionUs,totalBufferedDurationUs,positionUs);
}
