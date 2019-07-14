/** 
 * Replaces a selection of tracks to be downloaded. Must not be called until after preparation completes.
 * @param periodIndex The period index for which the track selection is replaced.
 * @param trackSelectorParameters The {@link DefaultTrackSelector.Parameters} to obtain the newselection of tracks.
 */
public final void replaceTrackSelections(int periodIndex,DefaultTrackSelector.Parameters trackSelectorParameters){
  clearTrackSelections(periodIndex);
  addTrackSelection(periodIndex,trackSelectorParameters);
}
