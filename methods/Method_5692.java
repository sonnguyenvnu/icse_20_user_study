/** 
 * Called by  {@link #selectAllTracks(MappedTrackInfo,int[][][],int[],Parameters)} to create a{@link TrackSelection} for a text renderer.
 * @param groups The {@link TrackGroupArray} mapped to the renderer.
 * @param formatSupport The result of {@link RendererCapabilities#supportsFormat} for each mappedtrack, indexed by track group index and track index (in that order).
 * @param params The selector's current constraint parameters.
 * @return The {@link TrackSelection.Definition} and corresponding track score, or null if noselection was made.
 * @throws ExoPlaybackException If an error occurs while selecting the tracks.
 */
@Nullable protected Pair<TrackSelection.Definition,Integer> selectTextTrack(TrackGroupArray groups,int[][] formatSupport,Parameters params) throws ExoPlaybackException {
  TrackGroup selectedGroup=null;
  int selectedTrackIndex=0;
  int selectedTrackScore=0;
  for (int groupIndex=0; groupIndex < groups.length; groupIndex++) {
    TrackGroup trackGroup=groups.get(groupIndex);
    int[] trackFormatSupport=formatSupport[groupIndex];
    for (int trackIndex=0; trackIndex < trackGroup.length; trackIndex++) {
      if (isSupported(trackFormatSupport[trackIndex],params.exceedRendererCapabilitiesIfNecessary)) {
        Format format=trackGroup.getFormat(trackIndex);
        int maskedSelectionFlags=format.selectionFlags & ~params.disabledTextTrackSelectionFlags;
        boolean isDefault=(maskedSelectionFlags & C.SELECTION_FLAG_DEFAULT) != 0;
        boolean isForced=(maskedSelectionFlags & C.SELECTION_FLAG_FORCED) != 0;
        int trackScore;
        boolean preferredLanguageFound=formatHasLanguage(format,params.preferredTextLanguage);
        if (preferredLanguageFound || (params.selectUndeterminedTextLanguage && formatHasNoLanguage(format))) {
          if (isDefault) {
            trackScore=8;
          }
 else           if (!isForced) {
            trackScore=6;
          }
 else {
            trackScore=4;
          }
          trackScore+=preferredLanguageFound ? 1 : 0;
        }
 else         if (isDefault) {
          trackScore=3;
        }
 else         if (isForced) {
          if (formatHasLanguage(format,params.preferredAudioLanguage)) {
            trackScore=2;
          }
 else {
            trackScore=1;
          }
        }
 else {
          continue;
        }
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
  return selectedGroup == null ? null : Pair.create(new TrackSelection.Definition(selectedGroup,selectedTrackIndex),selectedTrackScore);
}
