public static boolean isWebUri(final @NonNull Uri uri,final @NonNull String webEndpoint){
  return isKickstarterUri(uri,webEndpoint) && !isApiUri(uri,webEndpoint);
}
