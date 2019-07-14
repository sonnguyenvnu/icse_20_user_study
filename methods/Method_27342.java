@Nullable private static Intent getCommits(@NonNull Context context,@NonNull Uri uri,boolean showRepoBtn){
  List<String> segments=Stream.of(uri.getPathSegments()).filter(value -> !value.equalsIgnoreCase("api") || !value.equalsIgnoreCase("v3")).toList();
  if (segments == null || segments.isEmpty() || segments.size() < 3)   return null;
  String login=null;
  String repoId=null;
  String sha=null;
  if (segments.size() > 3 && segments.get(3).equals("commits")) {
    login=segments.get(1);
    repoId=segments.get(2);
    sha=segments.get(4);
  }
 else   if (segments.size() > 2 && segments.get(2).equals("commits")) {
    login=segments.get(0);
    repoId=segments.get(1);
    sha=uri.getLastPathSegment();
  }
  if (login != null && sha != null && repoId != null) {
    return CommitPagerActivity.createIntent(context,repoId,login,sha,showRepoBtn);
  }
  return null;
}
