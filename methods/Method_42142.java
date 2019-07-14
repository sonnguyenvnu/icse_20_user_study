/** 
 * @param uri The Uri to check.
 * @return Whether the Uri authority is ExternalStorageProvider.
 */
private static boolean isExternalStorageDocument(Uri uri){
  return "com.android.externalstorage.documents".equals(uri.getAuthority());
}
