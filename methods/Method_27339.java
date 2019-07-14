@Nullable private static Intent getRepoProject(@NonNull Context context,@NonNull Uri uri){
  List<String> segments=uri.getPathSegments();
  if (segments == null || segments.size() < 3)   return null;
  String owner=segments.get(0);
  String repoName=segments.get(1);
  if (segments.size() == 3 && "projects".equalsIgnoreCase(segments.get(2))) {
    return RepoPagerActivity.createIntent(context,repoName,owner,RepoPagerMvp.PROJECTS);
  }
 else   if (segments.size() == 4 && "projects".equalsIgnoreCase(segments.get(2))) {
    try {
      int projectId=Integer.parseInt(segments.get(segments.size() - 1));
      if (projectId > 0) {
        return ProjectPagerActivity.Companion.getIntent(context,owner,repoName,projectId,LinkParserHelper.isEnterprise(uri.toString()));
      }
    }
 catch (    Exception ignored) {
    }
  }
  return null;
}
