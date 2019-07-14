private static int[] getTracksAdding(SelectionOverride override,int addedTrack){
  int[] tracks=override.tracks;
  tracks=Arrays.copyOf(tracks,tracks.length + 1);
  tracks[tracks.length - 1]=addedTrack;
  return tracks;
}
