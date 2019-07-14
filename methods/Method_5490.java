@Override protected TrackGroupArray[] getTrackGroupArrays(SsManifest manifest){
  SsManifest.StreamElement[] streamElements=manifest.streamElements;
  TrackGroup[] trackGroups=new TrackGroup[streamElements.length];
  for (int i=0; i < streamElements.length; i++) {
    trackGroups[i]=new TrackGroup(streamElements[i].formats);
  }
  return new TrackGroupArray[]{new TrackGroupArray(trackGroups)};
}
