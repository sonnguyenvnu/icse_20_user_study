/** 
 * Adds a selection of tracks to be downloaded. Must not be called until after preparation completes.
 * @param periodIndex The period index this track selection is added for.
 * @param trackSelectorParameters The {@link DefaultTrackSelector.Parameters} to obtain the newselection of tracks.
 */
public final void addTrackSelection(int periodIndex,DefaultTrackSelector.Parameters trackSelectorParameters){
  Assertions.checkNotNull(trackGroupArrays);
  Assertions.checkNotNull(trackSelectionsByPeriodAndRenderer);
  trackSelector.setParameters(trackSelectorParameters);
  runTrackSelection(periodIndex);
}
