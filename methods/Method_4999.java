/** 
 * Returns all  {@link TrackSelection track selections} for a period and renderer. Must not becalled until after preparation completes.
 * @param periodIndex The period index.
 * @param rendererIndex The renderer index.
 * @return A list of selected {@link TrackSelection track selections}.
 */
public final List<TrackSelection> getTrackSelections(int periodIndex,int rendererIndex){
  Assertions.checkNotNull(immutableTrackSelectionsByPeriodAndRenderer);
  return immutableTrackSelectionsByPeriodAndRenderer[periodIndex][rendererIndex];
}
