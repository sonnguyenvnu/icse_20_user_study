public static Music valueof(Cursor cursor){
  return new Music(cursor.getLong(cursor.getColumnIndex(MediaStore.Files.FileColumns._ID)),cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)),cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)),cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
}
