public static void checkGallery(){
  if (Build.VERSION.SDK_INT < 24 || allPhotosAlbumEntry == null) {
    return;
  }
  final int prevSize=allPhotosAlbumEntry.photos.size();
  Utilities.globalQueue.postRunnable(() -> {
    int count=0;
    Cursor cursor=null;
    try {
      if (ApplicationLoader.applicationContext.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        cursor=MediaStore.Images.Media.query(ApplicationLoader.applicationContext.getContentResolver(),MediaStore.Images.Media.EXTERNAL_CONTENT_URI,new String[]{"COUNT(_id)"},null,null,null);
        if (cursor != null) {
          if (cursor.moveToNext()) {
            count+=cursor.getInt(0);
          }
        }
      }
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
 finally {
      if (cursor != null) {
        cursor.close();
      }
    }
    try {
      if (ApplicationLoader.applicationContext.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        cursor=MediaStore.Images.Media.query(ApplicationLoader.applicationContext.getContentResolver(),MediaStore.Video.Media.EXTERNAL_CONTENT_URI,new String[]{"COUNT(_id)"},null,null,null);
        if (cursor != null) {
          if (cursor.moveToNext()) {
            count+=cursor.getInt(0);
          }
        }
      }
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
 finally {
      if (cursor != null) {
        cursor.close();
      }
    }
    if (prevSize != count) {
      if (refreshGalleryRunnable != null) {
        AndroidUtilities.cancelRunOnUIThread(refreshGalleryRunnable);
        refreshGalleryRunnable=null;
      }
      loadGalleryPhotosAlbums(0);
    }
  }
,2000);
}
