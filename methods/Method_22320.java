@NonNull public static String getMimeType(@NonNull Context context,@NonNull Uri uri){
  if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
    final ContentResolver contentResolver=context.getContentResolver();
    String type=contentResolver.getType(uri);
    if (type != null)     return type;
  }
  return AcraContentProvider.guessMimeType(uri);
}
