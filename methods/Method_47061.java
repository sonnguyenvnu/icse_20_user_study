/** 
 * Returns an OutputStream to write to the file. The file will be truncated immediately.
 */
private static int getTemporaryAlbumId(final Context context){
  final File temporaryTrack;
  try {
    temporaryTrack=installTemporaryTrack(context);
  }
 catch (  final IOException ex) {
    Log.w("MediaFile","Error installing tempory track.",ex);
    return 0;
  }
  final Uri filesUri=MediaStore.Files.getContentUri("external");
  final String[] selectionArgs={temporaryTrack.getAbsolutePath()};
  final ContentResolver contentResolver=context.getContentResolver();
  Cursor cursor=contentResolver.query(filesUri,ALBUM_PROJECTION,MediaStore.MediaColumns.DATA + "=?",selectionArgs,null);
  if (cursor == null || !cursor.moveToFirst()) {
    if (cursor != null) {
      cursor.close();
      cursor=null;
    }
    final ContentValues values=new ContentValues();
    values.put(MediaStore.MediaColumns.DATA,temporaryTrack.getAbsolutePath());
    values.put(MediaStore.MediaColumns.TITLE,"{MediaWrite Workaround}");
    values.put(MediaStore.MediaColumns.SIZE,temporaryTrack.length());
    values.put(MediaStore.MediaColumns.MIME_TYPE,"audio/mpeg");
    values.put(MediaStore.Audio.AudioColumns.IS_MUSIC,true);
    contentResolver.insert(filesUri,values);
  }
  cursor=contentResolver.query(filesUri,ALBUM_PROJECTION,MediaStore.MediaColumns.DATA + "=?",selectionArgs,null);
  if (cursor == null) {
    return 0;
  }
  if (!cursor.moveToFirst()) {
    cursor.close();
    return 0;
  }
  final int id=cursor.getInt(0);
  final int albumId=cursor.getInt(1);
  final int mediaType=cursor.getInt(2);
  cursor.close();
  final ContentValues values=new ContentValues();
  boolean updateRequired=false;
  if (albumId == 0) {
    values.put(MediaStore.Audio.AlbumColumns.ALBUM_ID,13371337);
    updateRequired=true;
  }
  if (mediaType != 2) {
    values.put("media_type",2);
    updateRequired=true;
  }
  if (updateRequired) {
    contentResolver.update(filesUri,values,BaseColumns._ID + "=" + id,null);
  }
  cursor=contentResolver.query(filesUri,ALBUM_PROJECTION,MediaStore.MediaColumns.DATA + "=?",selectionArgs,null);
  if (cursor == null) {
    return 0;
  }
  try {
    if (!cursor.moveToFirst()) {
      return 0;
    }
    return cursor.getInt(1);
  }
  finally {
    cursor.close();
  }
}
