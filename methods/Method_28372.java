@Override public void onModuleChanged(@NonNull FragmentManager fragmentManager,@RepoPagerMvp.RepoNavigationType int type){
  Fragment currentVisible=getVisibleFragment(fragmentManager);
  RepoCodePagerFragment codePagerView=(RepoCodePagerFragment)AppHelper.getFragmentByTag(fragmentManager,RepoCodePagerFragment.TAG);
  RepoIssuesPagerFragment repoIssuesPagerView=(RepoIssuesPagerFragment)AppHelper.getFragmentByTag(fragmentManager,RepoIssuesPagerFragment.TAG);
  RepoPullRequestPagerFragment pullRequestPagerView=(RepoPullRequestPagerFragment)AppHelper.getFragmentByTag(fragmentManager,RepoPullRequestPagerFragment.TAG);
  RepoProjectsFragmentPager projectsFragmentPager=(RepoProjectsFragmentPager)AppHelper.getFragmentByTag(fragmentManager,RepoProjectsFragmentPager.Companion.getTAG());
  if (getRepo() == null) {
    sendToView(RepoPagerMvp.View::onFinishActivity);
    return;
  }
  if (currentVisible == null)   return;
switch (type) {
case RepoPagerMvp.PROFILE:
    sendToView(RepoPagerMvp.View::openUserProfile);
case RepoPagerMvp.CODE:
  if (codePagerView == null) {
    onAddAndHide(fragmentManager,RepoCodePagerFragment.newInstance(repoId(),login(),getRepo().getHtmlUrl(),getRepo().getUrl(),getRepo().getDefaultBranch()),currentVisible);
  }
 else {
    onShowHideFragment(fragmentManager,codePagerView,currentVisible);
  }
break;
case RepoPagerMvp.ISSUES:
if ((!getRepo().isHasIssues())) {
sendToView(view -> view.showMessage(R.string.error,R.string.repo_issues_is_disabled));
break;
}
if (repoIssuesPagerView == null) {
onAddAndHide(fragmentManager,RepoIssuesPagerFragment.newInstance(repoId(),login()),currentVisible);
}
 else {
onShowHideFragment(fragmentManager,repoIssuesPagerView,currentVisible);
}
break;
case RepoPagerMvp.PULL_REQUEST:
if (pullRequestPagerView == null) {
onAddAndHide(fragmentManager,RepoPullRequestPagerFragment.newInstance(repoId(),login()),currentVisible);
}
 else {
onShowHideFragment(fragmentManager,pullRequestPagerView,currentVisible);
}
break;
case RepoPagerMvp.PROJECTS:
if (projectsFragmentPager == null) {
onAddAndHide(fragmentManager,RepoProjectsFragmentPager.Companion.newInstance(login(),repoId()),currentVisible);
}
 else {
onShowHideFragment(fragmentManager,projectsFragmentPager,currentVisible);
}
break;
}
}
