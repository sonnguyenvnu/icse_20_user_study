/** 
 * Finds the renderer to which the provided  {@link TrackGroup} should be mapped.<p> A  {@link TrackGroup} is mapped to the renderer that reports the highest of (listed indecreasing order of support)  {@link RendererCapabilities#FORMAT_HANDLED}, {@link RendererCapabilities#FORMAT_EXCEEDS_CAPABILITIES}, {@link RendererCapabilities#FORMAT_UNSUPPORTED_DRM} and{@link RendererCapabilities#FORMAT_UNSUPPORTED_SUBTYPE}. In the case that two or more renderers report the same level of support, the renderer with the lowest index is associated. <p> If all renderers report  {@link RendererCapabilities#FORMAT_UNSUPPORTED_TYPE} for all of thetracks in the group, then  {@code renderers.length} is returned to indicate that the group wasnot mapped to any renderer.
 * @param rendererCapabilities The {@link RendererCapabilities} of the renderers.
 * @param group The track group to map to a renderer.
 * @return The index of the renderer to which the track group was mapped, or{@code renderers.length} if it was not mapped to any renderer.
 * @throws ExoPlaybackException If an error occurs finding a renderer.
 */
private static int findRenderer(RendererCapabilities[] rendererCapabilities,TrackGroup group) throws ExoPlaybackException {
  int bestRendererIndex=rendererCapabilities.length;
  int bestFormatSupportLevel=RendererCapabilities.FORMAT_UNSUPPORTED_TYPE;
  for (int rendererIndex=0; rendererIndex < rendererCapabilities.length; rendererIndex++) {
    RendererCapabilities rendererCapability=rendererCapabilities[rendererIndex];
    for (int trackIndex=0; trackIndex < group.length; trackIndex++) {
      int formatSupportLevel=rendererCapability.supportsFormat(group.getFormat(trackIndex)) & RendererCapabilities.FORMAT_SUPPORT_MASK;
      if (formatSupportLevel > bestFormatSupportLevel) {
        bestRendererIndex=rendererIndex;
        bestFormatSupportLevel=formatSupportLevel;
        if (bestFormatSupportLevel == RendererCapabilities.FORMAT_HANDLED) {
          return bestRendererIndex;
        }
      }
    }
  }
  return bestRendererIndex;
}
