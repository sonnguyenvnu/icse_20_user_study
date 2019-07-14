@Override public long selectTracks(TrackSelection[] selections,boolean[] mayRetainStreamFlags,SampleStream[] streams,boolean[] streamResetFlags,long positionUs){
  int[] streamIndexToTrackGroupIndex=getStreamIndexToTrackGroupIndex(selections);
  releaseDisabledStreams(selections,mayRetainStreamFlags,streams);
  releaseOrphanEmbeddedStreams(selections,streams,streamIndexToTrackGroupIndex);
  selectNewStreams(selections,streams,streamResetFlags,positionUs,streamIndexToTrackGroupIndex);
  ArrayList<ChunkSampleStream<DashChunkSource>> sampleStreamList=new ArrayList<>();
  ArrayList<EventSampleStream> eventSampleStreamList=new ArrayList<>();
  for (  SampleStream sampleStream : streams) {
    if (sampleStream instanceof ChunkSampleStream) {
      @SuppressWarnings("unchecked") ChunkSampleStream<DashChunkSource> stream=(ChunkSampleStream<DashChunkSource>)sampleStream;
      sampleStreamList.add(stream);
    }
 else     if (sampleStream instanceof EventSampleStream) {
      eventSampleStreamList.add((EventSampleStream)sampleStream);
    }
  }
  sampleStreams=newSampleStreamArray(sampleStreamList.size());
  sampleStreamList.toArray(sampleStreams);
  eventSampleStreams=new EventSampleStream[eventSampleStreamList.size()];
  eventSampleStreamList.toArray(eventSampleStreams);
  compositeSequenceableLoader=compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(sampleStreams);
  return positionUs;
}
