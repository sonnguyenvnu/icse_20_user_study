private void finishRecordingVideo(){
  MediaMetadataRetriever mediaMetadataRetriever=null;
  long duration=0;
  try {
    mediaMetadataRetriever=new MediaMetadataRetriever();
    mediaMetadataRetriever.setDataSource(recordedFile);
    String d=mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
    if (d != null) {
      duration=(int)Math.ceil(Long.parseLong(d) / 1000.0f);
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
 finally {
    try {
      if (mediaMetadataRetriever != null) {
        mediaMetadataRetriever.release();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
  final Bitmap bitmap=ThumbnailUtils.createVideoThumbnail(recordedFile,MediaStore.Video.Thumbnails.MINI_KIND);
  String fileName=Integer.MIN_VALUE + "_" + SharedConfig.getLastLocalId() + ".jpg";
  final File cacheFile=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),fileName);
  try {
    FileOutputStream stream=new FileOutputStream(cacheFile);
    bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
  }
 catch (  Throwable e) {
    FileLog.e(e);
  }
  SharedConfig.saveConfig();
  final long durationFinal=duration;
  AndroidUtilities.runOnUIThread(() -> {
    if (onVideoTakeCallback != null) {
      String path=cacheFile.getAbsolutePath();
      if (bitmap != null) {
        ImageLoader.getInstance().putImageToCache(new BitmapDrawable(bitmap),Utilities.MD5(path));
      }
      onVideoTakeCallback.onFinishVideoRecording(path,durationFinal);
      onVideoTakeCallback=null;
    }
  }
);
}
