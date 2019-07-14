private HlsMediaPlaylist getLatestPlaylistSnapshot(HlsMediaPlaylist oldPlaylist,HlsMediaPlaylist loadedPlaylist){
  if (!loadedPlaylist.isNewerThan(oldPlaylist)) {
    if (loadedPlaylist.hasEndTag) {
      return oldPlaylist.copyWithEndTag();
    }
 else {
      return oldPlaylist;
    }
  }
  long startTimeUs=getLoadedPlaylistStartTimeUs(oldPlaylist,loadedPlaylist);
  int discontinuitySequence=getLoadedPlaylistDiscontinuitySequence(oldPlaylist,loadedPlaylist);
  return loadedPlaylist.copyWith(startTimeUs,discontinuitySequence);
}
