public static boolean isProjectUpdatesUri(final @NonNull Uri uri,final @NonNull String webEndpoint){
  return isKickstarterUri(uri,webEndpoint) && PROJECT_UPDATES_PATTERN.matcher(UriUtilsKt.path(uri)).matches();
}
