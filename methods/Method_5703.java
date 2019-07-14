/** 
 * Calls  {@link RendererCapabilities#supportsMixedMimeTypeAdaptation()} for each renderer,returning the results in an array.
 * @param rendererCapabilities The {@link RendererCapabilities} of the renderers.
 * @return An array containing the result of calling {@link RendererCapabilities#supportsMixedMimeTypeAdaptation()} for each renderer.
 * @throws ExoPlaybackException If an error occurs determining the adaptation support.
 */
private static int[] getMixedMimeTypeAdaptationSupports(RendererCapabilities[] rendererCapabilities) throws ExoPlaybackException {
  int[] mixedMimeTypeAdaptationSupport=new int[rendererCapabilities.length];
  for (int i=0; i < mixedMimeTypeAdaptationSupport.length; i++) {
    mixedMimeTypeAdaptationSupport[i]=rendererCapabilities[i].supportsMixedMimeTypeAdaptation();
  }
  return mixedMimeTypeAdaptationSupport;
}
