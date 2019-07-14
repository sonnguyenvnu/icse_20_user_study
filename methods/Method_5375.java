/** 
 * Called by the parent  {@link HlsMediaPeriod} when a track selection occurs.
 * @param selections The renderer track selections.
 * @param mayRetainStreamFlags Flags indicating whether the existing sample stream can be retainedfor each selection. A  {@code true} value indicates that the selection is unchanged, andthat the caller does not require that the sample stream be recreated.
 * @param streams The existing sample streams, which will be updated to reflect the providedselections.
 * @param streamResetFlags Will be updated to indicate new sample streams, and sample streams thathave been retained but with the requirement that the consuming renderer be reset.
 * @param positionUs The current playback position in microseconds.
 * @param forceReset If true then a reset is forced (i.e. a seek will be performed with in-bufferseeking disabled).
 * @return Whether this wrapper requires the parent {@link HlsMediaPeriod} to perform a seek aspart of the track selection.
 */
public boolean selectTracks(TrackSelection[] selections,boolean[] mayRetainStreamFlags,SampleStream[] streams,boolean[] streamResetFlags,long positionUs,boolean forceReset){
  Assertions.checkState(prepared);
  int oldEnabledTrackGroupCount=enabledTrackGroupCount;
  for (int i=0; i < selections.length; i++) {
    if (streams[i] != null && (selections[i] == null || !mayRetainStreamFlags[i])) {
      enabledTrackGroupCount--;
      ((HlsSampleStream)streams[i]).unbindSampleQueue();
      streams[i]=null;
    }
  }
  boolean seekRequired=forceReset || (seenFirstTrackSelection ? oldEnabledTrackGroupCount == 0 : positionUs != lastSeekPositionUs);
  TrackSelection oldPrimaryTrackSelection=chunkSource.getTrackSelection();
  TrackSelection primaryTrackSelection=oldPrimaryTrackSelection;
  for (int i=0; i < selections.length; i++) {
    if (streams[i] == null && selections[i] != null) {
      enabledTrackGroupCount++;
      TrackSelection selection=selections[i];
      int trackGroupIndex=trackGroups.indexOf(selection.getTrackGroup());
      if (trackGroupIndex == primaryTrackGroupIndex) {
        primaryTrackSelection=selection;
        chunkSource.selectTracks(selection);
      }
      streams[i]=new HlsSampleStream(this,trackGroupIndex);
      streamResetFlags[i]=true;
      if (trackGroupToSampleQueueIndex != null) {
        ((HlsSampleStream)streams[i]).bindSampleQueue();
      }
      if (sampleQueuesBuilt && !seekRequired) {
        SampleQueue sampleQueue=sampleQueues[trackGroupToSampleQueueIndex[trackGroupIndex]];
        sampleQueue.rewind();
        seekRequired=sampleQueue.advanceTo(positionUs,true,true) == SampleQueue.ADVANCE_FAILED && sampleQueue.getReadIndex() != 0;
      }
    }
  }
  if (enabledTrackGroupCount == 0) {
    chunkSource.reset();
    downstreamTrackFormat=null;
    mediaChunks.clear();
    if (loader.isLoading()) {
      if (sampleQueuesBuilt) {
        for (        SampleQueue sampleQueue : sampleQueues) {
          sampleQueue.discardToEnd();
        }
      }
      loader.cancelLoading();
    }
 else {
      resetSampleQueues();
    }
  }
 else {
    if (!mediaChunks.isEmpty() && !Util.areEqual(primaryTrackSelection,oldPrimaryTrackSelection)) {
      boolean primarySampleQueueDirty=false;
      if (!seenFirstTrackSelection) {
        long bufferedDurationUs=positionUs < 0 ? -positionUs : 0;
        HlsMediaChunk lastMediaChunk=getLastMediaChunk();
        MediaChunkIterator[] mediaChunkIterators=chunkSource.createMediaChunkIterators(lastMediaChunk,positionUs);
        primaryTrackSelection.updateSelectedTrack(positionUs,bufferedDurationUs,C.TIME_UNSET,readOnlyMediaChunks,mediaChunkIterators);
        int chunkIndex=chunkSource.getTrackGroup().indexOf(lastMediaChunk.trackFormat);
        if (primaryTrackSelection.getSelectedIndexInTrackGroup() != chunkIndex) {
          primarySampleQueueDirty=true;
        }
      }
 else {
        primarySampleQueueDirty=true;
      }
      if (primarySampleQueueDirty) {
        forceReset=true;
        seekRequired=true;
        pendingResetUpstreamFormats=true;
      }
    }
    if (seekRequired) {
      seekToUs(positionUs,forceReset);
      for (int i=0; i < streams.length; i++) {
        if (streams[i] != null) {
          streamResetFlags[i]=true;
        }
      }
    }
  }
  updateSampleStreams(streams);
  seenFirstTrackSelection=true;
  return seekRequired;
}
