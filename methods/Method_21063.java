public static boolean isWebViewUri(final @NonNull Uri uri,final @NonNull String webEndpoint){
  return isKickstarterUri(uri,webEndpoint) && !isKSFavIcon(uri,webEndpoint);
}
