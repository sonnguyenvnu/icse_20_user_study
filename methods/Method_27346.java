@Nullable private static Intent getReleases(@NonNull Context context,@NonNull Uri uri,boolean isEnterprise){
  List<String> segments=uri.getPathSegments();
  if (segments != null && segments.size() > 2) {
    if (uri.getPathSegments().get(2).equals("releases")) {
      String owner=segments.get(0);
      String repo=segments.get(1);
      String tag=uri.getLastPathSegment();
      if (tag != null && !repo.equalsIgnoreCase(tag)) {
        if (TextUtils.isDigitsOnly(tag)) {
          return ReleasesListActivity.getIntent(context,owner,repo,InputHelper.toLong(tag),isEnterprise);
        }
 else {
          return ReleasesListActivity.getIntent(context,owner,repo,tag,isEnterprise);
        }
      }
      return ReleasesListActivity.getIntent(context,owner,repo);
    }
 else     if (segments.size() > 3 && segments.get(3).equalsIgnoreCase("releases")) {
      String owner=segments.get(1);
      String repo=segments.get(2);
      String tag=uri.getLastPathSegment();
      if (tag != null && !repo.equalsIgnoreCase(tag)) {
        if (TextUtils.isDigitsOnly(tag)) {
          return ReleasesListActivity.getIntent(context,owner,repo,InputHelper.toLong(tag),isEnterprise);
        }
 else {
          return ReleasesListActivity.getIntent(context,owner,repo,tag,isEnterprise);
        }
      }
      return ReleasesListActivity.getIntent(context,owner,repo);
    }
    return null;
  }
  return null;
}
