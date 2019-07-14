public static boolean isStagingUri(final @NonNull Uri uri,final @NonNull String webEndpoint){
  return isKickstarterUri(uri,webEndpoint) && Secrets.RegExpPattern.STAGING.matcher(UriUtilsKt.host(uri)).matches();
}
