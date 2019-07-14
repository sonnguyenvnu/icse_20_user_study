public static boolean shouldSendImageAsDocument(String path,Uri uri){
  BitmapFactory.Options bmOptions=new BitmapFactory.Options();
  bmOptions.inJustDecodeBounds=true;
  if (path == null && uri != null && uri.getScheme() != null) {
    String imageFilePath=null;
    if (uri.getScheme().contains("file")) {
      path=uri.getPath();
    }
 else {
      try {
        path=AndroidUtilities.getPath(uri);
      }
 catch (      Throwable e) {
        FileLog.e(e);
      }
    }
  }
  if (path != null) {
    BitmapFactory.decodeFile(path,bmOptions);
  }
 else   if (uri != null) {
    boolean error=false;
    try {
      InputStream inputStream=ApplicationLoader.applicationContext.getContentResolver().openInputStream(uri);
      BitmapFactory.decodeStream(inputStream,null,bmOptions);
      inputStream.close();
    }
 catch (    Throwable e) {
      FileLog.e(e);
      return false;
    }
  }
  float photoW=bmOptions.outWidth;
  float photoH=bmOptions.outHeight;
  return photoW / photoH > 10.0f || photoH / photoW > 10.0f;
}
