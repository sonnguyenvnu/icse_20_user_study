public static String getRealFilePath(final Context context,final Uri uri){
  if (null == uri) {
    return null;
  }
  final String scheme=uri.getScheme();
  String data=null;
  if (scheme == null) {
    data=uri.getPath();
  }
 else   if (ContentResolver.SCHEME_FILE.equals(scheme)) {
    data=uri.getPath();
  }
 else   if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
    String[] projection={MediaStore.Images.ImageColumns.DATA};
    Cursor cursor=context.getContentResolver().query(uri,projection,null,null,null);
    if (null != cursor) {
      if (cursor.moveToFirst()) {
        int index=cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        if (index > -1) {
          data=cursor.getString(index);
        }
      }
      cursor.close();
    }
  }
  return data;
}
