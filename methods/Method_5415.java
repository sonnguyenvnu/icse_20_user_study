private static Segment getFirstOldOverlappingSegment(HlsMediaPlaylist oldPlaylist,HlsMediaPlaylist loadedPlaylist){
  int mediaSequenceOffset=(int)(loadedPlaylist.mediaSequence - oldPlaylist.mediaSequence);
  List<Segment> oldSegments=oldPlaylist.segments;
  return mediaSequenceOffset < oldSegments.size() ? oldSegments.get(mediaSequenceOffset) : null;
}
