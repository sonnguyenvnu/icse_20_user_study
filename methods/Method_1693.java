public static @Nullable ImageRequest fromUri(@Nullable String uriString){
  return (uriString == null || uriString.length() == 0) ? null : fromUri(Uri.parse(uriString));
}
