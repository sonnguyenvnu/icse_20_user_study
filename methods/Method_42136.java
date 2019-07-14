/** 
 * Get an Uri from an file path.
 * @param path The file path.
 * @return The Uri.
 */
private static Uri getUriFromFile(Context context,final String path){
  ContentResolver resolver=context.getContentResolver();
  Cursor filecursor=resolver.query(MediaStore.Files.getContentUri("external"),new String[]{BaseColumns._ID},MediaStore.MediaColumns.DATA + " = ?",new String[]{path},MediaStore.MediaColumns.DATE_ADDED + " desc");
  if (filecursor == null) {
    return null;
  }
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
