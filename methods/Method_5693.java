/** 
 * Called by  {@link #selectAllTracks(MappedTrackInfo,int[][][],int[],Parameters)} to create a{@link TrackSelection} for a renderer whose type is neither video, audio or text.
 * @param trackType The type of the renderer.
 * @param groups The {@link TrackGroupArray} mapped to the renderer.
 * @param formatSupport The result of {@link RendererCapabilities#supportsFormat} for each mappedtrack, indexed by track group index and track index (in that order).
 * @param params The selector's current constraint parameters.
 * @return The {@link TrackSelection} for the renderer, or null if no selection was made.
 * @throws ExoPlaybackException If an error occurs while selecting the tracks.
 */
@Nullable protected TrackSelection.Definition selectOtherTrack(int trackType,TrackGroupArray groups,int[][] formatSupport,Parameters params) throws ExoPlaybackException {
  TrackGroup selectedGroup=null;
  int selectedTrackIndex=0;
  int selectedTrackScore=0;
  for (int groupIndex=0; groupIndex < groups.length; groupIndex++) {
    TrackGroup trackGroup=groups.get(groupIndex);
    int[] trackFormatSupport=formatSupport[groupIndex];
    for (int trackIndex=0; trackIndex < trackGroup.length; trackIndex++) {
      if (isSupported(trackFormatSupport[trackIndex],params.exceedRendererCapabilitiesIfNecessary)) {
        Format format=trackGroup.getFormat(trackIndex);
        boolean isDefault=(format.selectionFlags & C.SELECTION_FLAG_DEFAULT) != 0;
        int trackScore=isDefault ? 2 : 1;
        if (isSupported(trackFormatSupport[trackIndex],false)) {
          trackScore+=WITHIN_RENDERER_CAPABILITIES_BONUS;
        }
        if (trackScore > selectedTrackScore) {
          selectedGroup=trackGroup;
          selectedTrackIndex=trackIndex;
          selectedTrackScore=trackScore;
        }
      }
    }
  }
  return selectedGroup == null ? null : new TrackSelection.Definition(selectedGroup,selectedTrackIndex);
}
