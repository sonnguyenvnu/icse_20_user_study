private static int[] getTracksRemoving(SelectionOverride override,int removedTrack){
  int[] tracks=new int[override.length - 1];
  int trackCount=0;
  for (int i=0; i < tracks.length + 1; i++) {
    int track=override.tracks[i];
    if (track != removedTrack) {
      tracks[trackCount++]=track;
    }
  }
  return tracks;
}
