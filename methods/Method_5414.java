private int getLoadedPlaylistDiscontinuitySequence(HlsMediaPlaylist oldPlaylist,HlsMediaPlaylist loadedPlaylist){
  if (loadedPlaylist.hasDiscontinuitySequence) {
    return loadedPlaylist.discontinuitySequence;
  }
  int primaryUrlDiscontinuitySequence=primaryUrlSnapshot != null ? primaryUrlSnapshot.discontinuitySequence : 0;
  if (oldPlaylist == null) {
    return primaryUrlDiscontinuitySequence;
  }
  Segment firstOldOverlappingSegment=getFirstOldOverlappingSegment(oldPlaylist,loadedPlaylist);
  if (firstOldOverlappingSegment != null) {
    return oldPlaylist.discontinuitySequence + firstOldOverlappingSegment.relativeDiscontinuitySequence - loadedPlaylist.segments.get(0).relativeDiscontinuitySequence;
  }
  return primaryUrlDiscontinuitySequence;
}
