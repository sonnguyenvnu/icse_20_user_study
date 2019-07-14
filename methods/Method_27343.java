@Nullable private static Intent getCommit(@NonNull Context context,@NonNull Uri uri,boolean showRepoBtn){
  List<String> segments=Stream.of(uri.getPathSegments()).filter(value -> !value.equalsIgnoreCase("api") || !value.equalsIgnoreCase("v3")).toList();
  if (segments.size() < 3 || !"commit".equals(segments.get(2)))   return null;
  String login=segments.get(0);
  String repoId=segments.get(1);
  String sha=segments.get(3);
  return CommitPagerActivity.createIntent(context,repoId,login,sha,showRepoBtn);
}
