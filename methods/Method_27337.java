@Nullable private static Intent getPullRequestIntent(@NonNull Context context,@NonNull Uri uri,boolean showRepoBtn){
  List<String> segments=uri.getPathSegments();
  if (segments == null || segments.size() < 3)   return null;
  String owner=null;
  String repo=null;
  String number=null;
  String fragment=uri.getEncodedFragment();
  Long commentId=null;
  if (!InputHelper.isEmpty(fragment) && fragment.split("-").length > 1) {
    fragment=fragment.split("-")[1];
    if (!InputHelper.isEmpty(fragment)) {
      try {
        commentId=Long.parseLong(fragment);
      }
 catch (      Exception ignored) {
      }
    }
  }
  if (segments.size() > 3) {
    if (("pull".equals(segments.get(2)) || "pulls".equals(segments.get(2)))) {
      owner=segments.get(0);
      repo=segments.get(1);
      number=segments.get(3);
    }
 else     if (("pull".equals(segments.get(3)) || "pulls".equals(segments.get(3))) && segments.size() > 4) {
      owner=segments.get(1);
      repo=segments.get(2);
      number=segments.get(4);
    }
 else {
      return null;
    }
  }
  if (InputHelper.isEmpty(number))   return null;
  int issueNumber;
  try {
    issueNumber=Integer.parseInt(number);
  }
 catch (  NumberFormatException nfe) {
    return null;
  }
  if (issueNumber < 1)   return null;
  return PullRequestPagerActivity.createIntent(context,repo,owner,issueNumber,showRepoBtn,LinkParserHelper.isEnterprise(uri.toString()),commentId == null ? 0 : commentId);
}
