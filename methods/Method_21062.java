public static boolean isKSFavIcon(final Uri uri,final String webEndpoint){
  return isKickstarterUri(uri,webEndpoint) && UriUtilsKt.lastPathSegment(uri).equals("favicon.ico");
}
