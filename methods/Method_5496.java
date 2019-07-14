@Override public List<StreamKey> getStreamKeys(TrackSelection trackSelection){
  List<StreamKey> streamKeys=new ArrayList<>(trackSelection.length());
  int streamElementIndex=trackGroups.indexOf(trackSelection.getTrackGroup());
  for (int i=0; i < trackSelection.length(); i++) {
    streamKeys.add(new StreamKey(streamElementIndex,trackSelection.getIndexInTrackGroup(i)));
  }
  return streamKeys;
}
