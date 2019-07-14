@Nullable private static Bitmap createThumbnailFromContentProvider(ContentResolver contentResolver,Uri uri){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1) {
    try {
      ParcelFileDescriptor videoFile=contentResolver.openFileDescriptor(uri,"r");
      MediaMetadataRetriever mediaMetadataRetriever=new MediaMetadataRetriever();
      mediaMetadataRetriever.setDataSource(videoFile.getFileDescriptor());
      return mediaMetadataRetriever.getFrameAtTime(-1);
    }
 catch (    FileNotFoundException e) {
      return null;
    }
  }
 else {
    return null;
  }
}
