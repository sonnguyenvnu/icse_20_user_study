public static boolean mkdir(final Context context,final File file) throws IOException {
  if (file.exists()) {
    return file.isDirectory();
  }
  final File tmpFile=new File(file,".MediaWriteTemp");
  final int albumId=getTemporaryAlbumId(context);
  if (albumId == 0) {
    throw new IOException("Failed to create temporary album id.");
  }
  final Uri albumUri=Uri.parse(String.format(Locale.US,ALBUM_ART_URI + "/%d",albumId));
  final ContentValues values=new ContentValues();
  values.put(MediaStore.MediaColumns.DATA,tmpFile.getAbsolutePath());
  final ContentResolver contentResolver=context.getContentResolver();
  if (contentResolver.update(albumUri,values,null,null) == 0) {
    values.put(MediaStore.Audio.AlbumColumns.ALBUM_ID,albumId);
    contentResolver.insert(Uri.parse(ALBUM_ART_URI),values);
  }
  try {
    final ParcelFileDescriptor fd=contentResolver.openFileDescriptor(albumUri,"r");
    fd.close();
  }
  finally {
    delete(context,tmpFile);
  }
  return file.exists();
}
