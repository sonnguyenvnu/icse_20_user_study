private static Bitmap createVideoThumbnail(String filePath,long time){
  Bitmap bitmap=null;
  MediaMetadataRetriever retriever=new MediaMetadataRetriever();
  try {
    retriever.setDataSource(filePath);
    bitmap=retriever.getFrameAtTime(time,MediaMetadataRetriever.OPTION_NEXT_SYNC);
  }
 catch (  Exception ignore) {
  }
 finally {
    try {
      retriever.release();
    }
 catch (    RuntimeException ex) {
    }
  }
  if (bitmap == null) {
    return null;
  }
  return bitmap;
}
