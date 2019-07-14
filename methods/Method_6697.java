private int findTrack(MediaExtractor extractor,boolean audio){
  int numTracks=extractor.getTrackCount();
  for (int i=0; i < numTracks; i++) {
    MediaFormat format=extractor.getTrackFormat(i);
    String mime=format.getString(MediaFormat.KEY_MIME);
    if (audio) {
      if (mime.startsWith("audio/")) {
        return i;
      }
    }
 else {
      if (mime.startsWith("video/")) {
        return i;
      }
    }
  }
  return -5;
}
