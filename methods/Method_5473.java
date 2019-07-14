@Override public long selectTracks(TrackSelection[] selections,boolean[] mayRetainStreamFlags,SampleStream[] streams,boolean[] streamResetFlags,long positionUs){
  for (int i=0; i < selections.length; i++) {
    if (streams[i] != null && (selections[i] == null || !mayRetainStreamFlags[i])) {
      sampleStreams.remove(streams[i]);
      streams[i]=null;
    }
    if (streams[i] == null && selections[i] != null) {
      SampleStreamImpl stream=new SampleStreamImpl();
      sampleStreams.add(stream);
      streams[i]=stream;
      streamResetFlags[i]=true;
    }
  }
  return positionUs;
}
