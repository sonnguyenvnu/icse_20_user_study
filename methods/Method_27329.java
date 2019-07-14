public static String stripScheme(@NonNull String url){
  try {
    Uri uri=Uri.parse(url);
    return !InputHelper.isEmpty(uri.getAuthority()) ? uri.getAuthority() : url;
  }
 catch (  Exception ignored) {
  }
  return url;
}
