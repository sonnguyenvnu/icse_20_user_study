public void setVideoPath(String path){
  destroy();
  mediaMetadataRetriever=new MediaMetadataRetriever();
  progressLeft=0.0f;
  progressRight=1.0f;
  try {
    mediaMetadataRetriever.setDataSource(path);
    String duration=mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
    videoLength=Long.parseLong(duration);
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  invalidate();
}
