/** 
 * @param uri The Uri to check.
 * @return Whether the Uri authority is Google Photos.
 */
public static boolean isGooglePhotosUri(Uri uri){
  return "com.google.android.apps.photos.content".equals(uri.getAuthority());
}
