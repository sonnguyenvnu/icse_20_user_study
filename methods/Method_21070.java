public static boolean isProjectUpdateCommentsUri(final @NonNull Uri uri,final @NonNull String webEndpoint){
  return isKickstarterUri(uri,webEndpoint) && PROJECT_UPDATE_COMMENTS_PATTERN.matcher(UriUtilsKt.path(uri)).matches();
}
