public static boolean isSignupUri(final @NonNull Uri uri,final @NonNull String webEndpoint){
  return isKickstarterUri(uri,webEndpoint) && UriUtilsKt.path(uri).equals("/signup");
}
