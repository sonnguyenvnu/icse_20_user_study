/** 
 * Calls  {@link RendererCapabilities#supportsFormat} for each track in the specified{@link TrackGroup}, returning the results in an array.
 * @param rendererCapabilities The {@link RendererCapabilities} of the renderer.
 * @param group The track group to evaluate.
 * @return An array containing the result of calling{@link RendererCapabilities#supportsFormat} for each track in the group.
 * @throws ExoPlaybackException If an error occurs determining the format support.
 */
private static int[] getFormatSupport(RendererCapabilities rendererCapabilities,TrackGroup group) throws ExoPlaybackException {
  int[] formatSupport=new int[group.length];
  for (int i=0; i < group.length; i++) {
    formatSupport[i]=rendererCapabilities.supportsFormat(group.getFormat(i));
  }
  return formatSupport;
}
