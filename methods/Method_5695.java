/** 
 * Returns whether a renderer supports tunneling for a  {@link TrackSelection}.
 * @param formatSupports The result of {@link RendererCapabilities#supportsFormat} for each track,indexed by group index and track index (in that order).
 * @param trackGroups The {@link TrackGroupArray}s for the renderer.
 * @param selection The track selection.
 * @return Whether the renderer supports tunneling for the {@link TrackSelection}.
 */
private static boolean rendererSupportsTunneling(int[][] formatSupports,TrackGroupArray trackGroups,TrackSelection selection){
  if (selection == null) {
    return false;
  }
  int trackGroupIndex=trackGroups.indexOf(selection.getTrackGroup());
  for (int i=0; i < selection.length(); i++) {
    int trackFormatSupport=formatSupports[trackGroupIndex][selection.getIndexInTrackGroup(i)];
    if ((trackFormatSupport & RendererCapabilities.TUNNELING_SUPPORT_MASK) != RendererCapabilities.TUNNELING_SUPPORTED) {
      return false;
    }
  }
  return true;
}
