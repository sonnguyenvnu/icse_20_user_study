public static boolean isKickstarterUri(final @NonNull Uri uri,final @NonNull String webEndpoint){
  return UriUtilsKt.host(uri).equals(Uri.parse(webEndpoint).getHost());
}
