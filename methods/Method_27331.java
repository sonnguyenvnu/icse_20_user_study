@Nullable public static String getGistId(@NonNull Uri uri){
  String gistId=null;
  if (uri.toString().contains("raw/gist")) {
    if (uri.getPathSegments().size() > 5) {
      gistId=uri.getPathSegments().get(5);
    }
  }
 else   if (uri.getPathSegments() != null) {
    if (TextUtils.equals(LinkParserHelper.HOST_GISTS_RAW,uri.getAuthority())) {
      if (uri.getPathSegments().size() > 1) {
        gistId=uri.getPathSegments().get(1);
      }
    }
  }
  return gistId;
}
