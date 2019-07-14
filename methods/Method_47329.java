public static Uri fileToContentUri(Context context,File file){
  final String normalizedPath=normalizeMediaPath(file.getAbsolutePath());
  Uri uri=fileToContentUri(context,normalizedPath,file.isDirectory(),EXTERNAL_VOLUME);
  if (uri != null) {
    return uri;
  }
  uri=fileToContentUri(context,normalizedPath,file.isDirectory(),INTERNAL_VOLUME);
  if (uri != null) {
    return uri;
  }
  return null;
}
