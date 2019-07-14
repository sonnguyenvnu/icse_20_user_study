@Nullable private static Intent getLabel(@NonNull Context context,@NonNull Uri uri){
  List<String> segments=uri.getPathSegments();
  if (segments == null || segments.size() < 3)   return null;
  String owner=segments.get(0);
  String repoName=segments.get(1);
  String lastPath=segments.get(2);
  if ("labels".equalsIgnoreCase(lastPath)) {
    return FilterIssuesActivity.getIntent(context,owner,repoName,"label:\"" + segments.get(3) + "\"");
  }
  return null;
}
