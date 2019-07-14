public static long getSize(Uri uri,ContentResolver contentResolver){
  try (Cursor cursor=contentResolver.query(uri,null,null,null,null)){
    if (cursor != null && cursor.getCount() > 0) {
      int columnIndex=cursor.getColumnIndex(OpenableColumns.SIZE);
      if (columnIndex != -1) {
        cursor.moveToFirst();
        if (!cursor.isNull(columnIndex)) {
          return cursor.getLong(columnIndex);
        }
      }
    }
  }
   return -1;
}
