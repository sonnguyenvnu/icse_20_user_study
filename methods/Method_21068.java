public static boolean isModalUri(final @NonNull Uri uri,final @NonNull String webEndpoint){
  return isKickstarterUri(uri,webEndpoint) && uri.getQueryParameter("modal") != null && uri.getQueryParameter("modal").equals("true");
}
