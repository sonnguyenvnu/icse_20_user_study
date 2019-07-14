public static boolean isProjectUpdateUri(final @NonNull Uri uri,final @NonNull String webEndpoint){
  return isKickstarterUri(uri,webEndpoint) && PROJECT_UPDATE_PATTERN.matcher(UriUtilsKt.path(uri)).matches();
}
