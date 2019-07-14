/** 
 * @param uri The Uri to check.
 * @return Whether the Uri authority is DownloadsProvider.
 */
private static boolean isDownloadsDocument(Uri uri){
  return "com.android.providers.downloads.documents".equals(uri.getAuthority());
}
