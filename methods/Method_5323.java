private TrackOutput prepareTrackOutput(TrackId id){
  int trackCount=sampleQueues.length;
  for (int i=0; i < trackCount; i++) {
    if (id.equals(sampleQueueTrackIds[i])) {
      return sampleQueues[i];
    }
  }
  SampleQueue trackOutput=new SampleQueue(allocator);
  trackOutput.setUpstreamFormatChangeListener(this);
  @NullableType TrackId[] sampleQueueTrackIds=Arrays.copyOf(this.sampleQueueTrackIds,trackCount + 1);
  sampleQueueTrackIds[trackCount]=id;
  this.sampleQueueTrackIds=Util.castNonNullTypeArray(sampleQueueTrackIds);
  @NullableType SampleQueue[] sampleQueues=Arrays.copyOf(this.sampleQueues,trackCount + 1);
  sampleQueues[trackCount]=trackOutput;
  this.sampleQueues=Util.castNonNullTypeArray(sampleQueues);
  return trackOutput;
}
