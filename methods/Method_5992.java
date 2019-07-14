/** 
 * Checks whether it's necessary to request the  {@link permission#READ_EXTERNAL_STORAGE}permission read the specified  {@link Uri}s, requesting the permission if necessary.
 * @param activity The host activity for checking and requesting the permission.
 * @param uris {@link Uri}s that may require  {@link permission#READ_EXTERNAL_STORAGE} to read.
 * @return Whether a permission request was made.
 */
@TargetApi(23) public static boolean maybeRequestReadExternalStoragePermission(Activity activity,Uri... uris){
  if (Util.SDK_INT < 23) {
    return false;
  }
  for (  Uri uri : uris) {
    if (isLocalFileUri(uri)) {
      if (activity.checkSelfPermission(permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        activity.requestPermissions(new String[]{permission.READ_EXTERNAL_STORAGE},0);
        return true;
      }
      break;
    }
  }
  return false;
}
