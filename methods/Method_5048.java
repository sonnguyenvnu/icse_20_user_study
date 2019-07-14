/** 
 * Copies playback info with new playback state.
 * @param playbackState New playback state. See {@link #playbackState}.
 * @return Copied playback info with new playback state.
 */
@CheckResult public PlaybackInfo copyWithPlaybackState(int playbackState){
  return new PlaybackInfo(timeline,manifest,periodId,startPositionUs,contentPositionUs,playbackState,isLoading,trackGroups,trackSelectorResult,loadingMediaPeriodId,bufferedPositionUs,totalBufferedDurationUs,positionUs);
}
