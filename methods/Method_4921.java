/** 
 * Applies a  {@link TrackSelectorResult} to the period.
 * @param newTrackSelectorResult The {@link TrackSelectorResult} to apply.
 * @param positionUs The position relative to the start of the period at which to apply the newtrack selections, in microseconds.
 * @param forceRecreateStreams Whether all streams are forced to be recreated.
 * @param streamResetFlags Will be populated to indicate which streams have been reset or werenewly created.
 * @return The actual position relative to the start of the period at which the new trackselections are applied.
 */
public long applyTrackSelection(TrackSelectorResult newTrackSelectorResult,long positionUs,boolean forceRecreateStreams,boolean[] streamResetFlags){
  for (int i=0; i < newTrackSelectorResult.length; i++) {
    mayRetainStreamFlags[i]=!forceRecreateStreams && newTrackSelectorResult.isEquivalent(trackSelectorResult,i);
  }
  disassociateNoSampleRenderersWithEmptySampleStream(sampleStreams);
  disableTrackSelectionsInResult();
  trackSelectorResult=newTrackSelectorResult;
  enableTrackSelectionsInResult();
  TrackSelectionArray trackSelections=newTrackSelectorResult.selections;
  positionUs=mediaPeriod.selectTracks(trackSelections.getAll(),mayRetainStreamFlags,sampleStreams,streamResetFlags,positionUs);
  associateNoSampleRenderersWithEmptySampleStream(sampleStreams);
  hasEnabledTracks=false;
  for (int i=0; i < sampleStreams.length; i++) {
    if (sampleStreams[i] != null) {
      Assertions.checkState(newTrackSelectorResult.isRendererEnabled(i));
      if (rendererCapabilities[i].getTrackType() != C.TRACK_TYPE_NONE) {
        hasEnabledTracks=true;
      }
    }
 else {
      Assertions.checkState(trackSelections.get(i) == null);
    }
  }
  return positionUs;
}
