@Override public long selectTracks(TrackSelection[] selections,boolean[] mayRetainStreamFlags,SampleStream[] streams,boolean[] streamResetFlags,long positionUs){
  ArrayList<ChunkSampleStream<SsChunkSource>> sampleStreamsList=new ArrayList<>();
  for (int i=0; i < selections.length; i++) {
    if (streams[i] != null) {
      @SuppressWarnings("unchecked") ChunkSampleStream<SsChunkSource> stream=(ChunkSampleStream<SsChunkSource>)streams[i];
      if (selections[i] == null || !mayRetainStreamFlags[i]) {
        stream.release();
        streams[i]=null;
      }
 else {
        sampleStreamsList.add(stream);
      }
    }
    if (streams[i] == null && selections[i] != null) {
      ChunkSampleStream<SsChunkSource> stream=buildSampleStream(selections[i],positionUs);
      sampleStreamsList.add(stream);
      streams[i]=stream;
      streamResetFlags[i]=true;
    }
  }
  sampleStreams=newSampleStreamArray(sampleStreamsList.size());
  sampleStreamsList.toArray(sampleStreams);
  compositeSequenceableLoader=compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(sampleStreams);
  return positionUs;
}
