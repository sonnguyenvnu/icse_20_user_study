/** 
 * Copies playback info with new loading state.
 * @param isLoading New loading state. See {@link #isLoading}.
 * @return Copied playback info with new loading state.
 */
@CheckResult public PlaybackInfo copyWithIsLoading(boolean isLoading){
  return new PlaybackInfo(timeline,manifest,periodId,startPositionUs,contentPositionUs,playbackState,isLoading,trackGroups,trackSelectorResult,loadingMediaPeriodId,bufferedPositionUs,totalBufferedDurationUs,positionUs);
}
