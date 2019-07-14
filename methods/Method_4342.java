/** 
 * Calculate target buffer size in bytes based on the selected tracks. The player will try not to exceed this target buffer. Only used when  {@code targetBufferBytes} is {@link C#LENGTH_UNSET}.
 * @param renderers The renderers for which the track were selected.
 * @param trackSelectionArray The selected tracks.
 * @return The target buffer size in bytes.
 */
protected int calculateTargetBufferSize(Renderer[] renderers,TrackSelectionArray trackSelectionArray){
  int targetBufferSize=0;
  for (int i=0; i < renderers.length; i++) {
    if (trackSelectionArray.get(i) != null) {
      targetBufferSize+=Util.getDefaultBufferSize(renderers[i].getTrackType());
    }
  }
  return targetBufferSize;
}
