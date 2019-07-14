@Override protected TrackGroupArray[] getTrackGroupArrays(HlsPlaylist playlist){
  Assertions.checkNotNull(playlist);
  if (playlist instanceof HlsMediaPlaylist) {
    renditionGroups=new int[0];
    return new TrackGroupArray[]{TrackGroupArray.EMPTY};
  }
  HlsMasterPlaylist masterPlaylist=(HlsMasterPlaylist)playlist;
  TrackGroup[] trackGroups=new TrackGroup[3];
  renditionGroups=new int[3];
  int trackGroupIndex=0;
  if (!masterPlaylist.variants.isEmpty()) {
    renditionGroups[trackGroupIndex]=HlsMasterPlaylist.GROUP_INDEX_VARIANT;
    trackGroups[trackGroupIndex++]=new TrackGroup(toFormats(masterPlaylist.variants));
  }
  if (!masterPlaylist.audios.isEmpty()) {
    renditionGroups[trackGroupIndex]=HlsMasterPlaylist.GROUP_INDEX_AUDIO;
    trackGroups[trackGroupIndex++]=new TrackGroup(toFormats(masterPlaylist.audios));
  }
  if (!masterPlaylist.subtitles.isEmpty()) {
    renditionGroups[trackGroupIndex]=HlsMasterPlaylist.GROUP_INDEX_SUBTITLE;
    trackGroups[trackGroupIndex++]=new TrackGroup(toFormats(masterPlaylist.subtitles));
  }
  return new TrackGroupArray[]{new TrackGroupArray(Arrays.copyOf(trackGroups,trackGroupIndex))};
}
