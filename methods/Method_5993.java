/** 
 * Returns whether it may be possible to load the given URIs based on the network security policy's cleartext traffic permissions.
 * @param uris A list of URIs that will be loaded.
 * @return Whether it may be possible to load the given URIs.
 */
@TargetApi(24) public static boolean checkCleartextTrafficPermitted(Uri... uris){
  if (Util.SDK_INT < 24) {
    return true;
  }
  for (  Uri uri : uris) {
    if ("http".equals(uri.getScheme()) && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(Assertions.checkNotNull(uri.getHost()))) {
      return false;
    }
  }
  return true;
}
