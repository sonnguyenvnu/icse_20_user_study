@Override public long selectTracks(@NullableType TrackSelection[] selections,boolean[] mayRetainStreamFlags,@NullableType SampleStream[] streams,boolean[] streamResetFlags,long positionUs){
  PreparedState preparedState=getPreparedState();
  TrackGroupArray tracks=preparedState.tracks;
  boolean[] trackEnabledStates=preparedState.trackEnabledStates;
  int oldEnabledTrackCount=enabledTrackCount;
  for (int i=0; i < selections.length; i++) {
    if (streams[i] != null && (selections[i] == null || !mayRetainStreamFlags[i])) {
      int track=((SampleStreamImpl)streams[i]).track;
      Assertions.checkState(trackEnabledStates[track]);
      enabledTrackCount--;
      trackEnabledStates[track]=false;
      streams[i]=null;
    }
  }
  boolean seekRequired=seenFirstTrackSelection ? oldEnabledTrackCount == 0 : positionUs != 0;
  for (int i=0; i < selections.length; i++) {
    if (streams[i] == null && selections[i] != null) {
      TrackSelection selection=selections[i];
      Assertions.checkState(selection.length() == 1);
      Assertions.checkState(selection.getIndexInTrackGroup(0) == 0);
      int track=tracks.indexOf(selection.getTrackGroup());
      Assertions.checkState(!trackEnabledStates[track]);
      enabledTrackCount++;
      trackEnabledStates[track]=true;
      streams[i]=new SampleStreamImpl(track);
      streamResetFlags[i]=true;
      if (!seekRequired) {
        SampleQueue sampleQueue=sampleQueues[track];
        sampleQueue.rewind();
        seekRequired=sampleQueue.advanceTo(positionUs,true,true) == SampleQueue.ADVANCE_FAILED && sampleQueue.getReadIndex() != 0;
      }
    }
  }
  if (enabledTrackCount == 0) {
    pendingDeferredRetry=false;
    notifyDiscontinuity=false;
    if (loader.isLoading()) {
      for (      SampleQueue sampleQueue : sampleQueues) {
        sampleQueue.discardToEnd();
      }
      loader.cancelLoading();
    }
 else {
      for (      SampleQueue sampleQueue : sampleQueues) {
        sampleQueue.reset();
      }
    }
  }
 else   if (seekRequired) {
    positionUs=seekToUs(positionUs);
    for (int i=0; i < streams.length; i++) {
      if (streams[i] != null) {
        streamResetFlags[i]=true;
      }
    }
  }
  seenFirstTrackSelection=true;
  return positionUs;
}
