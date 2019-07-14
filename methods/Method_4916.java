/** 
 * Handles period preparation.
 * @param playbackSpeed The current playback speed.
 * @param timeline The current {@link Timeline}.
 * @throws ExoPlaybackException If an error occurs during track selection.
 */
public void handlePrepared(float playbackSpeed,Timeline timeline) throws ExoPlaybackException {
  prepared=true;
  trackGroups=mediaPeriod.getTrackGroups();
  TrackSelectorResult selectorResult=Assertions.checkNotNull(selectTracks(playbackSpeed,timeline));
  long newStartPositionUs=applyTrackSelection(selectorResult,info.startPositionUs,false);
  rendererPositionOffsetUs+=info.startPositionUs - newStartPositionUs;
  info=info.copyWithStartPositionUs(newStartPositionUs);
}
