/** 
 * ???????????
 * @param context ???
 * @param data    onActivityResult???Intent
 * @return
 */
public static String getChoosedImagePath(Activity context,Intent data){
  if (data == null)   return null;
  String path="";
  ContentResolver resolver=context.getContentResolver();
  Uri originalUri=data.getData();
  if (null == originalUri)   return null;
  String[] projection={MediaStore.Images.Media.DATA};
  Cursor cursor=resolver.query(originalUri,projection,null,null,null);
  if (null != cursor) {
    try {
      int column_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
      cursor.moveToFirst();
      path=cursor.getString(column_index);
    }
 catch (    IllegalArgumentException e) {
      e.printStackTrace();
    }
 finally {
      try {
        if (!cursor.isClosed()) {
          cursor.close();
        }
      }
 catch (      Exception e) {
        e.printStackTrace();
      }
    }
  }
  return RxDataTool.isNullString(path) ? originalUri.getPath() : null;
}
