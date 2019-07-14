public static boolean isProjectUri(final @NonNull Uri uri,final @NonNull String webEndpoint){
  return isKickstarterUri(uri,webEndpoint) && PROJECT_PATTERN.matcher(UriUtilsKt.path(uri)).matches();
}
