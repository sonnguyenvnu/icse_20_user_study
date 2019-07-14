public static Uri getUriFromFile(final String path,Context context){
  ContentResolver resolver=context.getContentResolver();
  Cursor filecursor=resolver.query(MediaStore.Files.getContentUri("external"),new String[]{BaseColumns._ID},MediaStore.MediaColumns.DATA + " = ?",new String[]{path},MediaStore.MediaColumns.DATE_ADDED + " desc");
  filecursor.moveToFirst();
  if (filecursor.isAfterLast()) {
    filecursor.close();
    ContentValues values=new ContentValues();
    values.put(MediaStore.MediaColumns.DATA,path);
    return resolver.insert(MediaStore.Files.getContentUri("external"),values);
  }
 else {
    int imageId=filecursor.getInt(filecursor.getColumnIndex(BaseColumns._ID));
    Uri uri=MediaStore.Files.getContentUri("external").buildUpon().appendPath(Integer.toString(imageId)).build();
    filecursor.close();
    return uri;
  }
}
