private static TrackGroupArray buildTrackGroups(SsManifest manifest){
  TrackGroup[] trackGroups=new TrackGroup[manifest.streamElements.length];
  for (int i=0; i < manifest.streamElements.length; i++) {
    trackGroups[i]=new TrackGroup(manifest.streamElements[i].formats);
  }
  return new TrackGroupArray(trackGroups);
}
