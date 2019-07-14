public static boolean isHivequeenUri(final @NonNull Uri uri,final @NonNull String webEndpoint){
  return isKickstarterUri(uri,webEndpoint) && Secrets.RegExpPattern.HIVEQUEEN.matcher(UriUtilsKt.host(uri)).matches();
}
