public static void addMediaToGallery(Uri uri){
  if (uri == null) {
    return;
  }
  try {
    Intent mediaScanIntent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
    mediaScanIntent.setData(uri);
    ApplicationLoader.applicationContext.sendBroadcast(mediaScanIntent);
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
