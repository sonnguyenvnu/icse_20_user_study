/** 
 * Get an uri for this content provider for the given file
 * @param context      a context
 * @param directory    the directory, to with the path is relative
 * @param relativePath the file path
 * @return the uri
 */
@SuppressWarnings("WeakerAccess") @NonNull public static Uri getUriForFile(@NonNull Context context,@NonNull Directory directory,@NonNull String relativePath){
  final Uri.Builder builder=new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT).authority(getAuthority(context)).appendPath(directory.name().toLowerCase());
  for (  String segment : relativePath.split(Pattern.quote(File.separator))) {
    if (segment.length() > 0) {
      builder.appendPath(segment);
    }
  }
  return builder.build();
}
