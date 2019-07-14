/** 
 * Updates the  {@link DashManifest} and the index of this period in the manifest.
 * @param manifest The updated manifest.
 * @param periodIndex the new index of this period in the updated manifest.
 */
public void updateManifest(DashManifest manifest,int periodIndex){
  this.manifest=manifest;
  this.periodIndex=periodIndex;
  playerEmsgHandler.updateManifest(manifest);
  if (sampleStreams != null) {
    for (    ChunkSampleStream<DashChunkSource> sampleStream : sampleStreams) {
      sampleStream.getChunkSource().updateManifest(manifest,periodIndex);
    }
    callback.onContinueLoadingRequested(this);
  }
  eventStreams=manifest.getPeriod(periodIndex).eventStreams;
  for (  EventSampleStream eventSampleStream : eventSampleStreams) {
    for (    EventStream eventStream : eventStreams) {
      if (eventStream.id().equals(eventSampleStream.eventStreamId())) {
        int lastPeriodIndex=manifest.getPeriodCount() - 1;
        eventSampleStream.updateEventStream(eventStream,manifest.dynamic && periodIndex == lastPeriodIndex);
        break;
      }
    }
  }
}
