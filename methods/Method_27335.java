@Nullable private static Intent getIntentForURI(@NonNull Context context,@NonNull Uri data,boolean showRepoBtn){
  String authority=data.getAuthority();
  boolean isEnterprise=PrefGetter.isEnterprise() && LinkParserHelper.isEnterprise(authority == null ? data.toString() : authority);
  if (HOST_GISTS.equals(data.getHost()) || "gist".equalsIgnoreCase(data.getPathSegments().get(0))) {
    String extension=MimeTypeMap.getFileExtensionFromUrl(data.toString());
    if (!InputHelper.isEmpty(extension) && !MarkDownProvider.isArchive(data.getLastPathSegment())) {
      String url=data.toString();
      return CodeViewerActivity.createIntent(context,url,url);
    }
    String gist=getGistId(data);
    if (gist != null) {
      return GistActivity.createIntent(context,gist,isEnterprise);
    }
  }
 else   if (HOST_GISTS_RAW.equalsIgnoreCase(data.getHost())) {
    return getGistFile(context,data);
  }
 else {
    if (MarkDownProvider.isArchive(data.toString()))     return null;
    if (TextUtils.equals(authority,HOST_DEFAULT) || TextUtils.equals(authority,RAW_AUTHORITY) || TextUtils.equals(authority,API_AUTHORITY) || isEnterprise) {
      Intent trending=getTrending(context,data);
      Intent projects=getRepoProject(context,data);
      Intent userIntent=getUser(context,data);
      Intent repoIssues=getRepoIssueIntent(context,data);
      Intent repoPulls=getRepoPullRequestIntent(context,data);
      Intent createIssueIntent=getCreateIssueIntent(context,data);
      Intent pullRequestIntent=getPullRequestIntent(context,data,showRepoBtn);
      Intent issueIntent=getIssueIntent(context,data,showRepoBtn);
      Intent releasesIntent=getReleases(context,data,isEnterprise);
      Intent repoIntent=getRepo(context,data);
      Intent repoWikiIntent=getWiki(context,data);
      Intent commit=getCommit(context,data,showRepoBtn);
      Intent commits=getCommits(context,data,showRepoBtn);
      Intent blob=getBlob(context,data);
      Intent label=getLabel(context,data);
      Intent search=getSearchIntent(context,data);
      Optional<Intent> intentOptional=returnNonNull(trending,projects,search,userIntent,repoIssues,repoPulls,pullRequestIntent,label,commit,commits,createIssueIntent,issueIntent,releasesIntent,repoIntent,repoWikiIntent,blob);
      Optional<Intent> empty=Optional.empty();
      if (intentOptional != null && intentOptional.isPresent() && intentOptional != empty) {
        Intent intent=intentOptional.get();
        if (isEnterprise) {
          if (intent.getExtras() != null) {
            Bundle bundle=intent.getExtras();
            bundle.putBoolean(BundleConstant.IS_ENTERPRISE,true);
            intent.putExtras(bundle);
          }
 else {
            intent.putExtra(BundleConstant.IS_ENTERPRISE,true);
          }
        }
        return intent;
      }
 else {
        Intent intent=getGeneralRepo(context,data);
        if (isEnterprise) {
          if (intent != null && intent.getExtras() != null) {
            Bundle bundle=intent.getExtras();
            bundle.putBoolean(BundleConstant.IS_ENTERPRISE,true);
            intent.putExtras(bundle);
          }
 else           if (intent != null) {
            intent.putExtra(BundleConstant.IS_ENTERPRISE,true);
          }
        }
        return intent;
      }
    }
  }
  return null;
}
