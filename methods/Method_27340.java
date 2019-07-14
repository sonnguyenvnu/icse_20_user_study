@Nullable private static Intent getWiki(@NonNull Context context,@NonNull Uri uri){
  List<String> segments=uri.getPathSegments();
  if (segments == null || segments.size() < 3)   return null;
  if ("wiki".equalsIgnoreCase(segments.get(2))) {
    String owner=segments.get(0);
    String repoName=segments.get(1);
    return WikiActivity.Companion.getWiki(context,repoName,owner,"wiki".equalsIgnoreCase(uri.getLastPathSegment()) ? null : uri.getLastPathSegment());
  }
  return null;
}
