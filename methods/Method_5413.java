private long getLoadedPlaylistStartTimeUs(HlsMediaPlaylist oldPlaylist,HlsMediaPlaylist loadedPlaylist){
  if (loadedPlaylist.hasProgramDateTime) {
    return loadedPlaylist.startTimeUs;
  }
  long primarySnapshotStartTimeUs=primaryUrlSnapshot != null ? primaryUrlSnapshot.startTimeUs : 0;
  if (oldPlaylist == null) {
    return primarySnapshotStartTimeUs;
  }
  int oldPlaylistSize=oldPlaylist.segments.size();
  Segment firstOldOverlappingSegment=getFirstOldOverlappingSegment(oldPlaylist,loadedPlaylist);
  if (firstOldOverlappingSegment != null) {
    return oldPlaylist.startTimeUs + firstOldOverlappingSegment.relativeStartTimeUs;
  }
 else   if (oldPlaylistSize == loadedPlaylist.mediaSequence - oldPlaylist.mediaSequence) {
    return oldPlaylist.getEndTimeUs();
  }
 else {
    return primarySnapshotStartTimeUs;
  }
}
