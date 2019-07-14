/** 
 * [[k0shk0sh, FastHub, issues], k0shk0sh/fastHub/(issues,pulls,commits, etc)]
 */
@Nullable private static Intent getGeneralRepo(@NonNull Context context,@NonNull Uri uri){
  if (getInvitationIntent(uri)) {
    return null;
  }
  boolean isEnterprise=PrefGetter.isEnterprise() && Uri.parse(LinkParserHelper.getEndpoint(PrefGetter.getEnterpriseUrl())).getAuthority().equalsIgnoreCase(uri.getAuthority());
  if (uri.getAuthority().equals(HOST_DEFAULT) || uri.getAuthority().equals(API_AUTHORITY) || isEnterprise) {
    List<String> segments=uri.getPathSegments();
    if (segments == null || segments.isEmpty())     return null;
    if (segments.size() == 1) {
      return getUser(context,uri);
    }
 else     if (segments.size() > 1) {
      if (segments.get(0).equalsIgnoreCase("repos") && segments.size() >= 2) {
        String owner=segments.get(1);
        String repoName=segments.get(2);
        return RepoPagerActivity.createIntent(context,repoName,owner);
      }
 else       if ("orgs".equalsIgnoreCase(segments.get(0))) {
        return null;
      }
 else {
        String owner=segments.get(0);
        String repoName=segments.get(1);
        return RepoPagerActivity.createIntent(context,repoName,owner);
      }
    }
  }
  return null;
}
