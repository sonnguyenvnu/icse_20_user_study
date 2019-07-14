/** 
 * Checks if the given URI is for a photo from the device's local media store.
 * @param uri the URI to check
 * @return true if the URI points to a media store photo
 */
public static boolean isLocalCameraUri(Uri uri){
  String uriString=uri.toString();
  return uriString.startsWith(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString()) || uriString.startsWith(MediaStore.Images.Media.INTERNAL_CONTENT_URI.toString());
}
