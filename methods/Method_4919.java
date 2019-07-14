/** 
 * Selects tracks for the period and returns the new result if the selection changed. Must only be called if  {@link #prepared} is {@code true}.
 * @param playbackSpeed The current playback speed.
 * @param timeline The current {@link Timeline}.
 * @return The {@link TrackSelectorResult} if the result changed. Or null if nothing changed.
 * @throws ExoPlaybackException If an error occurs during track selection.
 */
@Nullable public TrackSelectorResult selectTracks(float playbackSpeed,Timeline timeline) throws ExoPlaybackException {
  TrackSelectorResult selectorResult=trackSelector.selectTracks(rendererCapabilities,getTrackGroups(),info.id,timeline);
  if (selectorResult.isEquivalent(trackSelectorResult)) {
    return null;
  }
  for (  TrackSelection trackSelection : selectorResult.selections.getAll()) {
    if (trackSelection != null) {
      trackSelection.onPlaybackSpeed(playbackSpeed);
    }
  }
  return selectorResult;
}
