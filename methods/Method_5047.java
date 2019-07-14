/** 
 * Copied playback info with new playing position.
 * @param periodId New playing media period. See {@link #periodId}.
 * @param positionUs New position. See {@link #positionUs}.
 * @param contentPositionUs New content position. See {@link #contentPositionUs}. Value is ignored if  {@code periodId.isAd()} is true.
 * @param totalBufferedDurationUs New buffered duration. See {@link #totalBufferedDurationUs}.
 * @return Copied playback info with new playing position.
 */
@CheckResult public PlaybackInfo copyWithNewPosition(MediaPeriodId periodId,long positionUs,long contentPositionUs,long totalBufferedDurationUs){
  return new PlaybackInfo(timeline,manifest,periodId,positionUs,periodId.isAd() ? contentPositionUs : C.TIME_UNSET,playbackState,isLoading,trackGroups,trackSelectorResult,loadingMediaPeriodId,bufferedPositionUs,totalBufferedDurationUs,positionUs);
}
