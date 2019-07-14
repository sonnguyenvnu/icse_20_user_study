@Deprecated public static long getAlbumId(Context context,String mediaPath){
  long id=-1;
  Cursor cur=context.getContentResolver().query(MediaStore.Files.getContentUri("external"),new String[]{MediaStore.Files.FileColumns.PARENT},MediaStore.Files.FileColumns.DATA + "=?",new String[]{mediaPath},null);
  if (cur != null && cur.moveToFirst()) {
    id=cur.getLong(0);
    cur.close();
  }
  return id;
}
