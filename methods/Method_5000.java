/** 
 * Clears the selection of tracks for a period. Must not be called until after preparation completes.
 * @param periodIndex The period index for which track selections are cleared.
 */
public final void clearTrackSelections(int periodIndex){
  Assertions.checkNotNull(trackSelectionsByPeriodAndRenderer);
  for (int i=0; i < rendererCapabilities.length; i++) {
    trackSelectionsByPeriodAndRenderer[periodIndex][i].clear();
  }
}
