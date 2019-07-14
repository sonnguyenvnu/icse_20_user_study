public static boolean isApiUri(final @NonNull Uri uri,final @NonNull String webEndpoint){
  return isKickstarterUri(uri,webEndpoint) && Secrets.RegExpPattern.API.matcher(UriUtilsKt.host(uri)).matches();
}
