/** 
 * Get the path of a file from the Uri.
 * @param contentResolver the content resolver which will query for the source file
 * @param srcUri The source uri
 * @return The Path for the file or null if doesn't exists
 */
@Nullable public static String getRealPathFromUri(ContentResolver contentResolver,final Uri srcUri){
  String result=null;
  if (isLocalContentUri(srcUri)) {
    Cursor cursor=null;
    try {
      cursor=contentResolver.query(srcUri,null,null,null,null);
      if (cursor != null && cursor.moveToFirst()) {
        int idx=cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        if (idx != -1) {
          result=cursor.getString(idx);
        }
      }
    }
  finally {
      if (cursor != null) {
        cursor.close();
      }
    }
  }
 else   if (isLocalFileUri(srcUri)) {
    result=srcUri.getPath();
  }
  return result;
}
