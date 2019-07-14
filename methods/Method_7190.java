private static void fillVideoAttribute(String videoPath,TLRPC.TL_documentAttributeVideo attributeVideo,VideoEditedInfo videoEditedInfo){
  boolean infoObtained=false;
  MediaMetadataRetriever mediaMetadataRetriever=null;
  try {
    mediaMetadataRetriever=new MediaMetadataRetriever();
    mediaMetadataRetriever.setDataSource(videoPath);
    String width=mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
    if (width != null) {
      attributeVideo.w=Integer.parseInt(width);
    }
    String height=mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
    if (height != null) {
      attributeVideo.h=Integer.parseInt(height);
    }
    String duration=mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
    if (duration != null) {
      attributeVideo.duration=(int)Math.ceil(Long.parseLong(duration) / 1000.0f);
    }
    if (Build.VERSION.SDK_INT >= 17) {
      String rotation=mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION);
      if (rotation != null) {
        int val=Utilities.parseInt(rotation);
        if (videoEditedInfo != null) {
          videoEditedInfo.rotationValue=val;
        }
 else         if (val == 90 || val == 270) {
          int temp=attributeVideo.w;
          attributeVideo.w=attributeVideo.h;
          attributeVideo.h=temp;
        }
      }
    }
    infoObtained=true;
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
  if (!infoObtained) {
    try {
      MediaPlayer mp=MediaPlayer.create(ApplicationLoader.applicationContext,Uri.fromFile(new File(videoPath)));
      if (mp != null) {
        attributeVideo.duration=(int)Math.ceil(mp.getDuration() / 1000.0f);
        attributeVideo.w=mp.getVideoWidth();
        attributeVideo.h=mp.getVideoHeight();
        mp.release();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
}
