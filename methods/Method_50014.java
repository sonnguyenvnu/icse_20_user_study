/** 
 * This method expects uri in the following format content://media/<table_name>/<row_index> (or) file://sdcard/test.mp4 http://test.com/test.mp4 <p> Here <table_name> shall be "video" or "audio" or "images" <row_index> the index of the content in given table
 */
static public String convertUriToPath(Context context,Uri uri){
  String path=null;
  if (null != uri) {
    String scheme=uri.getScheme();
    if (null == scheme || scheme.equals("") || scheme.equals(ContentResolver.SCHEME_FILE)) {
      path=uri.getPath();
    }
 else     if (scheme.equals("http")) {
      path=uri.toString();
    }
 else     if (scheme.equals(ContentResolver.SCHEME_CONTENT)) {
      String[] projection=new String[]{MediaStore.MediaColumns.DATA};
      Cursor cursor=null;
      try {
        cursor=context.getContentResolver().query(uri,projection,null,null,null);
        if (null == cursor || 0 == cursor.getCount() || !cursor.moveToFirst()) {
          throw new IllegalArgumentException("Given Uri could not be found" + " in media store");
        }
        int pathIndex=cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        path=cursor.getString(pathIndex);
      }
 catch (      SQLiteException e) {
        throw new IllegalArgumentException("Given Uri is not formatted in a way " + "so that it can be found in media store.");
      }
 finally {
        if (null != cursor) {
          cursor.close();
        }
      }
    }
 else {
      throw new IllegalArgumentException("Given Uri scheme is not supported");
    }
  }
  return path;
}
