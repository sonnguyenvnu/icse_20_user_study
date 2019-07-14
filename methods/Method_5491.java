@Override protected StreamKey toStreamKey(int periodIndex,int trackGroupIndex,int trackIndexInTrackGroup){
  return new StreamKey(trackGroupIndex,trackIndexInTrackGroup);
}
