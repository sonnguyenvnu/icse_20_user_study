/** 
 * Applies a  {@link TrackSelectorResult} to the period.
 * @param trackSelectorResult The {@link TrackSelectorResult} to apply.
 * @param positionUs The position relative to the start of the period at which to apply the newtrack selections, in microseconds.
 * @param forceRecreateStreams Whether all streams are forced to be recreated.
 * @return The actual position relative to the start of the period at which the new trackselections are applied.
 */
public long applyTrackSelection(TrackSelectorResult trackSelectorResult,long positionUs,boolean forceRecreateStreams){
  return applyTrackSelection(trackSelectorResult,positionUs,forceRecreateStreams,new boolean[rendererCapabilities.length]);
}
